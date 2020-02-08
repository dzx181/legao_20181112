package com.zxq.legao.util;

import com.zxq.legao.constant.ModuleEnum;
import com.zxq.legao.entity.po.ClassRoomPO;
import com.zxq.legao.entity.po.ClasstimepackPO;
import com.zxq.legao.entity.po.DepositPO;
import com.zxq.legao.entity.po.FollowPO;
import com.zxq.legao.entity.vo.*;
import com.zxq.legao.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Description:
 * <p>
 * 导出工具
 * </p>
 *
 * @author dzx
 * @date 2020/2/5 11:12
 */
@Slf4j
@Component
public class ExportUtil {
    private final UserService userService;

    public ExportUtil(UserService userService) {
        this.userService = userService;
    }

    public static ResponseEntity<byte[]> download(String downloadPath, String filename) throws IOException {
        File file = new File(downloadPath);
        // 中文名 乱码问题
        String encodingFilename = new String(filename.getBytes(StandardCharsets.UTF_8.name()), StandardCharsets.ISO_8859_1.name());
        // 设置头部attachment(下载方式)，application/octet-stream
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", encodingFilename);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // 返回ResponseEntity对象,CREATED(201状态码)请求已经被实现，而且有一个新的资源已经依据请求的需要而建立，且其 URI
        // 已经随Location 头信息返回。
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

    /**
     * 导出文件
     *
     * @param thTitle    表头
     * @param tableTitle 表格标题
     * @param path       存储路径
     * @return 是够导出成功
     */
    public static boolean exportFile(String[] thTitle, String tableTitle, Object listValue, String path, ModuleEnum moduleType) {
        // 创建Excelworkbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 创建一个工作表sheet
        XSSFSheet sheet = workbook.createSheet();

        // 创建表头样式
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        // 字体
        XSSFFont font = workbook.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 14);
        // 居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 字体
        cellStyle.setFont(font);
        // 合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, thTitle.length - 1));

        // 创建表格样式
        XSSFCellStyle cellStyle2 = workbook.createCellStyle();
        // 居中
        cellStyle2.setAlignment(HorizontalAlignment.CENTER);

        // 创建第一行
        XSSFRow row0 = sheet.createRow(0);
        XSSFCell cell = null;
        cell = row0.createCell(0);
        // 设置标题值
        cell.setCellValue(tableTitle);
        cell.setCellStyle(cellStyle);
        // 设置行高
        row0.setHeightInPoints((short) 20);

        // 创建第二行
        XSSFRow row2 = sheet.createRow(1);
        // 插入第二行的数据
        for (int i = 0; i < thTitle.length; i++) {
            cell = row2.createCell(i);
            cell.setCellValue(thTitle[i]);
            cell.setCellStyle(cellStyle2);
        }
        // 第三行,追加数据
        setCellValue(listValue, moduleType, sheet, cellStyle2);

        // 创建一个文件
        File file = new File(path);
        try {
            file.createNewFile();
            // 将excel的内容写入到流中
            FileOutputStream stream = FileUtils.openOutputStream(file);
            workbook.write(stream);
            stream.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 设置具体的值
     *
     * @param moduleType
     */
    public static void setCellValue(Object listValues, ModuleEnum moduleType, XSSFSheet sheet, XSSFCellStyle cellStyle2) {
        //格式化日期
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        switch (moduleType) {
            case SCHEDULE:
                selectSchedule(listValues, sheet, cellStyle2,simpleFormatter);
                break;
            case DATE:
                selectDate(listValues, sheet, cellStyle2);
                break;
            case SIGN:
                selectSign(listValues, sheet, cellStyle2, simpleFormatter);
                break;
            case STUDENT:
                selectStudent(listValues, sheet, cellStyle2, simpleFormatter);
                break;
            case FOLLOW:
                selectFollow(listValues, sheet, cellStyle2, simpleFormatter);
                break;
            case CLASSROOM:
                selectClassRoom(listValues, sheet, cellStyle2);
                break;
            case COURSE:
                selectCourse(listValues, sheet, cellStyle2);
                break;
            case SERIES:
                selectSeries(listValues, sheet, cellStyle2);
                break;
            case SCHOOL_AREA:
                selectSchoolArea(listValues, sheet, cellStyle2);
                break;
            case JOB:
                selectJob(listValues, sheet, cellStyle2, simpleFormatter);
                break;
            case EMPLOY:
                selectEmploy(listValues, sheet, cellStyle2, simpleFormatter);
                break;
            case DEPOSIT:
                selectDeposit(listValues, sheet, cellStyle2);
                break;
            case CLASS_TIME_PACK:
                selectClassTimePack(listValues, sheet, cellStyle2);
                break;
            case CONTRACT:
                selectContract(listValues, sheet, cellStyle2, simpleFormatter);
                break;
            case USER:
                selectUser(listValues, sheet, cellStyle2, simpleFormatter);
                break;
            default:
                log.error("no match type....");

        }

    }

    private static void selectSchedule(Object listValues, XSSFSheet sheet, XSSFCellStyle cellStyle2, SimpleDateFormat simpleFormatter) {
        List<ScheduleExportVO> employPOS = (List<ScheduleExportVO>) listValues;
        for (int i = 0; i < employPOS.size(); i++) {
            XSSFRow nextRow = sheet.createRow(i + 2);
            XSSFCell cell2 = nextRow.createCell(0);
            cell2.setCellValue(employPOS.get(i).getStudentName());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(1);
            cell2.setCellValue(employPOS.get(i).getCourseName());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(2);
            cell2.setCellValue(employPOS.get(i).getAgeArea());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(3);
            cell2.setCellValue(employPOS.get(i).getCourseTime());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(4);
            cell2.setCellValue(employPOS.get(i).getWeek());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(5);
            cell2.setCellValue(employPOS.get(i).getSex());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(6);
            cell2.setCellValue(employPOS.get(i).getTeacherName());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(7);
            cell2.setCellValue(employPOS.get(i).getSignStatus());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(8);
            cell2.setCellValue(employPOS.get(i).getSchoolArea());
            cell2.setCellStyle(cellStyle2);

            // 设置列高,(第几列，像素)
            sheet.setColumnWidth((short) i, (short) 4000);
        }
    }

    private static void selectDate(Object listValues, XSSFSheet sheet, XSSFCellStyle cellStyle2) {
        List<DateVO> employPOS = (List<DateVO>) listValues;
        for (int i = 0; i < employPOS.size(); i++) {
            XSSFRow nextRow = sheet.createRow(i + 2);
            XSSFCell cell2 = nextRow.createCell(0);
            cell2.setCellValue(employPOS.get(i).getId().toString());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(1);
            cell2.setCellValue(employPOS.get(i).getTimeSection());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(2);
            cell2.setCellValue("1".equals(employPOS.get(i).getStatus()) ? "启用" : "禁用");
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(3);
            cell2.setCellValue(employPOS.get(i).getPriority());
            cell2.setCellStyle(cellStyle2);

            // 设置列高,(第几列，像素)
            sheet.setColumnWidth((short) i, (short) 4000);
        }
    }

    private static void selectSign(Object listValues, XSSFSheet sheet, XSSFCellStyle cellStyle2, SimpleDateFormat simpleFormatter) {
        List<RelationVO> employPOS = (List<RelationVO>) listValues;
        for (int i = 0; i < employPOS.size(); i++) {
            XSSFRow nextRow = sheet.createRow(i + 2);
            XSSFCell cell2 = nextRow.createCell(0);
            cell2.setCellValue(employPOS.get(i).getId().toString());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(1);
            if (employPOS.get(i).getSchedule() != null) {
                if (employPOS.get(i).getSchedule().getCourse() == null) {
                    cell2.setCellValue("没有课程信息");
                } else {
                    cell2.setCellValue(employPOS.get(i).getSchedule().getCourse().getName());
                }
            } else {
                cell2.setCellValue("没有课程信息");
            }
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(2);
            if (employPOS.get(i).getSchedule() != null) {
                if (employPOS.get(i).getSchedule().getTeacherVO() == null) {
                    cell2.setCellValue("没有老师信息");
                } else {
                    cell2.setCellValue(employPOS.get(i).getSchedule().getTeacherVO().getName());
                }
            } else {
                cell2.setCellValue("没有老师信息");
            }
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(3);
            if (employPOS.get(i).getStudent() == null) {
                cell2.setCellValue("没有该学员信息");
            } else {
                cell2.setCellValue(employPOS.get(i).getStudent().getName());
            }
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(4);
            if (employPOS.get(i).getStudent() == null) {
                cell2.setCellValue("没有该学员电话");
            } else {
                cell2.setCellValue(employPOS.get(i).getStudent().getTelphone());
            }
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(5);
            if (employPOS.get(i).getStudent() == null) {
                cell2.setCellValue("没有学员生日");
            } else {
                cell2.setCellValue(simpleFormatter.format(employPOS.get(i).getStudent().getBirthday()));
            }
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(6);
            if (employPOS.get(i).getStudent() == null) {
                cell2.setCellValue("没有学员性别");
            } else {
                cell2.setCellValue(employPOS.get(i).getStudent().getSex());
            }
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(7);
            if (employPOS.get(i).getSchedule() == null) {
                cell2.setCellValue("没有周数");
            } else {
                cell2.setCellValue(employPOS.get(i).getSchedule().getWeekOfYear());
            }
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(8);
            if (employPOS.get(i).getSchedule() == null) {
                cell2.setCellValue("没有该时间段");
            } else {
                if (employPOS.get(i).getSchedule().getDate() == null) {
                    cell2.setCellValue("没有该时间段");
                } else {
                    cell2.setCellValue(employPOS.get(i).getSchedule().getDate().getTimeSection());
                }
            }
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(9);
            if (employPOS.get(i).getSignInStatus().equals(1)) {
                cell2.setCellValue("准时");
            } else if (employPOS.get(i).getSignInStatus().equals(2)) {
                cell2.setCellValue("未签到");
            } else {
                cell2.setCellValue("请假");
            }
            cell2.setCellStyle(cellStyle2);

            // 设置列高,(第几列，像素)
            sheet.setColumnWidth((short) i, (short) 4000);
        }
    }

    private static void selectStudent(Object listValues, XSSFSheet sheet, XSSFCellStyle cellStyle2, SimpleDateFormat simpleFormatter) {
        List<StudentVO> employPOS = (List<StudentVO>) listValues;
        for (int i = 0; i < employPOS.size(); i++) {
            XSSFRow nextRow = sheet.createRow(i + 2);
            XSSFCell cell2 = nextRow.createCell(0);
            cell2.setCellValue(employPOS.get(i).getId().toString());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(1);
            cell2.setCellValue(employPOS.get(i).getNickName());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(2);
            cell2.setCellValue(employPOS.get(i).getName());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(3);
            cell2.setCellValue(employPOS.get(i).getParentRelat());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(4);
            cell2.setCellValue(employPOS.get(i).getParentName());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(5);
            cell2.setCellValue(employPOS.get(i).getTelphone());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(6);
            cell2.setCellValue(employPOS.get(i).getWeChatID());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(7);
            cell2.setCellValue(employPOS.get(i).getEducation());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(8);
            cell2.setCellValue(simpleFormatter.format(employPOS.get(i).getBirthday()));
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(9);
            cell2.setCellValue(employPOS.get(i).getSex());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(10);
            cell2.setCellValue(simpleFormatter.format(employPOS.get(i).getCreateDate()));
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(11);
            cell2.setCellValue(employPOS.get(i).getSparePhone());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(12);
            cell2.setCellValue(employPOS.get(i).getMarkPeople());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(13);
            cell2.setCellValue(employPOS.get(i).getAdvisor());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(14);
            cell2.setCellValue(employPOS.get(i).getArea());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(15);
            cell2.setCellValue(simpleFormatter.format(employPOS.get(i).getWillDate()));
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(16);
            cell2.setCellValue(employPOS.get(i).getBaseSituation());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(17);
            //学生状态：1代表新客户，2代表试听客户，3代表号码无效客户，4其他
            if (employPOS.get(i).getStatus().equals(1)){
                cell2.setCellValue("新客户");
            }else if(employPOS.get(i).getStatus().equals(2)){
                cell2.setCellValue("试听客户");
            }else if (employPOS.get(i).getStatus().equals(3)){
                cell2.setCellValue("号码无效客户");
            }else {
                cell2.setCellValue("4其他");
            }
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(18);
            //重要程度：1代表重点，2代表优质，3会员，4一般，5放弃，6未知
            if (employPOS.get(i).getImportanceGrade().equals(1)) {
                cell2.setCellValue("重点");
            }else if(employPOS.get(i).getImportanceGrade().equals(2)) {
                cell2.setCellValue("优质");
            }else if(employPOS.get(i).getImportanceGrade().equals(3)) {
                cell2.setCellValue("会员");
            }else if(employPOS.get(i).getImportanceGrade().equals(4)) {
                cell2.setCellValue("一般");
            }else if(employPOS.get(i).getImportanceGrade().equals(5)) {
                cell2.setCellValue("放弃");
            }else{
                cell2.setCellValue("未知");
            }
                cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(19);
            cell2.setCellValue(employPOS.get(i).getSource());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(20);
            cell2.setCellValue(employPOS.get(i).getTeacherName());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(21);
            if (employPOS.get(i).getSchoolAreaID() == null) {
                cell2.setCellValue("没有该校区");
            }else {
                cell2.setCellValue(employPOS.get(i).getSchoolAreaID().getName());
            }
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(22);
            if (employPOS.get(i).getFollowID() == null) {
                cell2.setCellValue("没有跟进人");
            } else {
                cell2.setCellValue(employPOS.get(i).getFollowID().getName());
            }
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(23);
            cell2.setCellValue(employPOS.get(i).getStuAge());
            cell2.setCellStyle(cellStyle2);

            // 设置列高,(第几列，像素)
            sheet.setColumnWidth((short) i, (short) 4000);
        }
    }

    private static void selectFollow(Object listValues, XSSFSheet sheet, XSSFCellStyle cellStyle2, SimpleDateFormat simpleFormatter) {
        List<FollowPO> employPOS = (List<FollowPO>) listValues;
        for (int i = 0; i < employPOS.size(); i++) {
            XSSFRow nextRow = sheet.createRow(i + 2);
            XSSFCell cell2 = nextRow.createCell(0);
            cell2.setCellValue(employPOS.get(i).getId().toString());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(1);
            cell2.setCellValue(employPOS.get(i).getStudentName());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(2);
            cell2.setCellValue(employPOS.get(i).getAdvisor());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(3);
            cell2.setCellValue(employPOS.get(i).getMode());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(4);
            cell2.setCellValue(employPOS.get(i).getContent());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(5);
            cell2.setCellValue(simpleFormatter.format(employPOS.get(i).getDate()));
            cell2.setCellStyle(cellStyle2);

            // 设置列高,(第几列，像素)
            sheet.setColumnWidth((short) i, (short) 4000);
        }
    }

    private static void selectClassRoom(Object listValues, XSSFSheet sheet, XSSFCellStyle cellStyle2) {
        List<ClassRoomPO> employPOS = (List<ClassRoomPO>) listValues;
        for (int i = 0; i < employPOS.size(); i++) {
            XSSFRow nextRow = sheet.createRow(i + 2);
            XSSFCell cell2 = nextRow.createCell(0);
            cell2.setCellValue(employPOS.get(i).getId().toString());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(1);
            cell2.setCellValue(employPOS.get(i).getName());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(2);
            cell2.setCellValue(employPOS.get(i).getAgeArea());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(3);
            cell2.setCellValue(employPOS.get(i).getResponsePersonName());
            cell2.setCellStyle(cellStyle2);

            // 设置列高,(第几列，像素)
            sheet.setColumnWidth((short) i, (short) 4000);
        }
    }

    private static void selectCourse(Object listValues, XSSFSheet sheet, XSSFCellStyle cellStyle2) {
        List<CourseVO> employPOS = (List<CourseVO>) listValues;
        for (int i = 0; i < employPOS.size(); i++) {
            XSSFRow nextRow = sheet.createRow(i + 2);
            XSSFCell cell2 = nextRow.createCell(0);
            cell2.setCellValue(employPOS.get(i).getId().toString());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(1);
            cell2.setCellValue(employPOS.get(i).getName());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(2);
            cell2.setCellValue(employPOS.get(i).getAgeArea());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(3);
            cell2.setCellValue(employPOS.get(i).getTeacherTools());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(4);
            cell2.setCellValue(employPOS.get(i).getCourseTime());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(5);
            if (employPOS.get(i).getSeries() == null) {
                cell2.setCellValue("没有该课程系列");
            } else {
                cell2.setCellValue(employPOS.get(i).getSeries().getName());
            }
            cell2.setCellStyle(cellStyle2);

            // 设置列高,(第几列，像素)
            sheet.setColumnWidth((short) i, (short) 4000);
        }
    }

    private static void selectSeries(Object listValues, XSSFSheet sheet, XSSFCellStyle cellStyle2) {
        List<SeriesVO> employPOS = (List<SeriesVO>) listValues;
        for (int i = 0; i < employPOS.size(); i++) {
            XSSFRow nextRow = sheet.createRow(i + 2);
            XSSFCell cell2 = nextRow.createCell(0);
            cell2.setCellValue(employPOS.get(i).getId().toString());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(1);
            cell2.setCellValue(employPOS.get(i).getName());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(2);
            cell2.setCellValue(employPOS.get(i).getTeacherTools());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(3);
            cell2.setCellValue(employPOS.get(i).getCourseTime());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(4);
            cell2.setCellValue(employPOS.get(i).getAgeArea());
            cell2.setCellStyle(cellStyle2);

            // 设置列高,(第几列，像素)
            sheet.setColumnWidth((short) i, (short) 4000);
        }
    }

    private static void selectSchoolArea(Object listValues, XSSFSheet sheet, XSSFCellStyle cellStyle2) {
        List<SchoolAreaVO> employPOS = (List<SchoolAreaVO>) listValues;
        for (int i = 0; i < employPOS.size(); i++) {
            XSSFRow nextRow = sheet.createRow(i + 2);
            XSSFCell cell2 = nextRow.createCell(0);
            cell2.setCellValue(employPOS.get(i).getId().toString());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(1);
            cell2.setCellValue(employPOS.get(i).getName());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(2);
            cell2.setCellValue(employPOS.get(i).getAddress());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(3);
            cell2.setCellValue(employPOS.get(i).getResponPersonName());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(4);
            cell2.setCellValue(employPOS.get(i).getTelphone());
            cell2.setCellStyle(cellStyle2);

            // 设置列高,(第几列，像素)
            sheet.setColumnWidth((short) i, (short) 4000);
        }
    }

    private static void selectJob(Object listValues, XSSFSheet sheet, XSSFCellStyle cellStyle2, SimpleDateFormat simpleFormatter) {
        List<JobVO> employPOS = (List<JobVO>) listValues;
        for (int i = 0; i < employPOS.size(); i++) {
            XSSFRow nextRow = sheet.createRow(i + 2);
            XSSFCell cell2 = nextRow.createCell(0);
            cell2.setCellValue(employPOS.get(i).getId().toString());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(1);
            cell2.setCellValue(employPOS.get(i).getName());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(2);
            cell2.setCellValue(employPOS.get(i).getRemark());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(3);
            cell2.setCellValue(simpleFormatter.format(employPOS.get(i).getCreateDate()));
            cell2.setCellStyle(cellStyle2);

            // 设置列高,(第几列，像素)
            sheet.setColumnWidth((short) i, (short) 4000);
        }
    }

    private static void selectEmploy(Object listValues, XSSFSheet sheet, XSSFCellStyle cellStyle2, SimpleDateFormat simpleFormatter) {
        List<EmployVO> employPOS = (List<EmployVO>) listValues;
        for (int i = 0; i < employPOS.size(); i++) {
            XSSFRow nextRow = sheet.createRow(i + 2);
            XSSFCell cell2 = nextRow.createCell(0);
            cell2.setCellValue(employPOS.get(i).getId().toString());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(1);
            cell2.setCellValue(employPOS.get(i).getName());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(2);
            cell2.setCellValue(employPOS.get(i).getSex());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(3);
            cell2.setCellValue(employPOS.get(i).getTelphone());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(4);
            cell2.setCellValue(simpleFormatter.format(employPOS.get(i).getBirthday()));
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(5);
            cell2.setCellValue(simpleFormatter.format(employPOS.get(i).getEntryDate()));
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(6);
            if (employPOS.get(i).getJobVO() == null) {
                cell2.setCellValue("没有该职位");
            } else {
                cell2.setCellValue(employPOS.get(i).getJobVO().getName());
            }
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(7);
            cell2.setCellValue(employPOS.get(i).getBasicSalary());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(8);
            cell2.setCellValue(employPOS.get(i).getFullWork());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(9);
            cell2.setCellValue(employPOS.get(i).getEatAllow());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(10);
            cell2.setCellValue(employPOS.get(i).getClassPay());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(11);
            cell2.setCellValue(employPOS.get(i).getAllClassTime());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(12);
            cell2.setCellValue(employPOS.get(i).getExtraPay());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(13);
            cell2.setCellValue(employPOS.get(i).getArea());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(14);
            if (employPOS.get(i).getStatus().equals(1)) {
                cell2.setCellValue("在职");
            } else if (employPOS.get(i).getStatus().equals(2)) {
                cell2.setCellValue("离职");
            } else {
                cell2.setCellValue("其他");
            }
            cell2.setCellStyle(cellStyle2);


            // 设置列高,(第几列，像素)
            sheet.setColumnWidth((short) i, (short) 4000);
        }
    }


    private static void selectDeposit(Object listValues, XSSFSheet sheet, XSSFCellStyle cellStyle2) {
        List<DepositPO> depositPOS = (List<DepositPO>) listValues;
        for (int i = 0; i < depositPOS.size(); i++) {
            XSSFRow nextRow = sheet.createRow(i + 2);
            XSSFCell cell2 = nextRow.createCell(0);
            cell2.setCellValue(depositPOS.get(i).getId().toString());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(1);
            cell2.setCellValue(depositPOS.get(i).getStudentName());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(2);
            cell2.setCellValue(depositPOS.get(i).getAdviser());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(3);
            cell2.setCellValue(depositPOS.get(i).getMoneyAmount());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(4);
            cell2.setCellValue(depositPOS.get(i).getPayType());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(5);
            cell2.setCellValue(depositPOS.get(i).getRemark());
            cell2.setCellStyle(cellStyle2);
            // 设置列高,(第几列，像素)
            sheet.setColumnWidth((short) i, (short) 4000);
        }
    }

    private static void selectClassTimePack(Object listValues, XSSFSheet sheet, XSSFCellStyle cellStyle2) {
        List<ClasstimepackPO> classtimepackPOS = (List<ClasstimepackPO>) listValues;
        for (int i = 0; i < classtimepackPOS.size(); i++) {
            XSSFRow nextRow = sheet.createRow(i + 2);
            XSSFCell cell2 = nextRow.createCell(0);
            cell2.setCellValue(classtimepackPOS.get(i).getId().toString());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(1);
            cell2.setCellValue(classtimepackPOS.get(i).getName());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(2);
            cell2.setCellValue(classtimepackPOS.get(i).getClassTime());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(3);
            cell2.setCellValue(classtimepackPOS.get(i).getPrice());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(4);
            cell2.setCellValue(classtimepackPOS.get(i).getPriority());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(5);
            cell2.setCellValue(classtimepackPOS.get(i).getRemark());
            cell2.setCellStyle(cellStyle2);
            // 设置列高,(第几列，像素)
            sheet.setColumnWidth((short) i, (short) 4000);
        }
    }

    private static void selectContract(Object listValues, XSSFSheet sheet, XSSFCellStyle cellStyle2, SimpleDateFormat simpleFormatter) {
        List<ContractVO> listValue = (List<ContractVO>) listValues;
        for (int i = 0; i < listValue.size(); i++) {
            XSSFRow nextRow = sheet.createRow(i + 2);
            XSSFCell cell2 = nextRow.createCell(0);
            cell2.setCellValue(listValue.get(i).getId().toString());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(1);
            cell2.setCellValue(listValue.get(i).getContractCode());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(2);
            if (listValue.get(i).getStudentVO() != null) {
                cell2.setCellValue(listValue.get(i).getStudentVO().getName());
            } else {
                cell2.setCellValue("该学生被删除");
            }
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(3);
            cell2.setCellValue(simpleFormatter.format(listValue.get(i).getSignDate()));
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(4);
            if (listValue.get(i).getClasstimepackVO() == null) {
                cell2.setCellValue("没有该课时包");
            } else {
                cell2.setCellValue(listValue.get(i).getClasstimepackVO().getName());
            }
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(5);
            cell2.setCellValue(listValue.get(i).getPrice());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(6);
            cell2.setCellValue(listValue.get(i).getDiscount());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(7);
            if (listValue.get(i).getDepositVO() == null) {
                cell2.setCellValue("0");
            } else {
                cell2.setCellValue(listValue.get(i).getDepositVO().getMoneyAmount());
            }
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(8);
            cell2.setCellValue(listValue.get(i).getActPay());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(9);
            cell2.setCellValue(listValue.get(i).getPayType());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(10);
            cell2.setCellValue(listValue.get(i).getPresentationClassTime());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(11);
            cell2.setCellValue(simpleFormatter.format(listValue.get(i).getStartTime()));
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(12);
            cell2.setCellValue(simpleFormatter.format(listValue.get(i).getEndTime()));
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(13);
            cell2.setCellValue(listValue.get(i).getBelongOne());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(14);
            if (listValue.get(i).getMembercardVO() == null) {
                cell2.setCellValue("没有关联会员");
            } else {
                cell2.setCellValue(listValue.get(i).getMembercardVO().getId());
            }
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(15);
            cell2.setCellValue(listValue.get(i).getRemark());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(16);
            cell2.setCellValue(listValue.get(i).getClassTime());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(17);
            cell2.setCellValue(listValue.get(i).getRemainClassTime());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(18);
            cell2.setCellValue(listValue.get(i).getTotalClassTime());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(19);
            if (listValue.get(i).getSchoolArea() == null) {
                cell2.setCellValue("没有该校区信息");
            } else {
                cell2.setCellValue(listValue.get(i).getSchoolArea().getName());
            }
            cell2.setCellStyle(cellStyle2);

            // 设置列高,(第几列，像素)
            sheet.setColumnWidth((short) i, (short) 4000);
        }
    }

    private static void selectUser(Object listValues, XSSFSheet sheet, XSSFCellStyle cellStyle2, SimpleDateFormat simpleFormatter) {
        List<UserVO> userVOList = (List<UserVO>) listValues;
        for (int i = 0; i < userVOList.size(); i++) {
            XSSFRow nextRow = sheet.createRow(i + 2);
            XSSFCell cell2 = nextRow.createCell(0);
            cell2.setCellValue(userVOList.get(i).getId().toString());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(1);
            cell2.setCellValue(userVOList.get(i).getUsername());
            cell2.setCellStyle(cellStyle2);

            cell2 = nextRow.createCell(2);
            if ("1".equals(userVOList.get(i).getStatus())) {
                cell2.setCellValue("超级管理员");
                cell2.setCellStyle(cellStyle2);
            } else {
                cell2.setCellValue("普通用户");
                cell2.setCellStyle(cellStyle2);
            }
            cell2 = nextRow.createCell(3);
            cell2.setCellValue(simpleFormatter.format(userVOList.get(i).getCreateDate()));
            cell2.setCellStyle(cellStyle2);

            // 设置列高,(第几列，像素)
            sheet.setColumnWidth((short) i, (short) 4000);
        }
    }
}
