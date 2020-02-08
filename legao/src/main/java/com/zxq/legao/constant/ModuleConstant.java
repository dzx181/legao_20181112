package com.zxq.legao.constant;

import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

/**
 * Description:
 * <p>
 * 常量类
 * </p>
 *
 * @author dengzhenxiang
 * @Date 2018/11/11 17:41
 */
public class ModuleConstant {

    /**
     * salt
     */
    public final static String MD5_SALT = "a1b2c3d4e5";

    /**
     * 每页显示的条数
     */
    public final static Integer PAGESIZE = 5;

    /**
     * 学生表默认展示字段名（对应数据库）
     */
    public final static String[] DEFAULT_STUDENT_FIELDS_DB = {"nickName", "name", "parentRelat", "parentName", "telphone", "weChatID", "education"};

    /**
     * 学生表默认展示字段名（对应中文名）
     */
    public final static String[] DEFAULT_STUDENT_FIELDS_ZH = {"昵称", "姓名", "家长关系", "家长姓名", "联系电话", "微信", "学校"};

    /**
     * 学生表全部字段名（对应数据库）
     */
    public final static String[] STUDENT_FIELDS_DB = {"nickName", "name", "parentRelat", "parentName", "telphone", "weChatID", "education", "birthday", "sex", "createDate", "sparePhone", "markPeople", "advisor", "area", "willDate", "baseSituation", "status", "importanceGrade", "source", "teacherName", "schoolAreaID", "followID","stuAge"};

    /**
     * 学生表全部字段名（对应中文名）
     */
    public final static String[] STUDENT_FIELDS_ZH = {"昵称", "姓名", "家长关系", "家长姓名", "联系电话", "微信", "学校", "生日", "性别", "录入日期", "备用电话", "市场人员", "顾问", "学员所属地区", "期望时间", "基本情况", "状态", "重要程度", "来源", "老师", "校区", "跟进","学员年龄"};

    /**
     * 员工表默认展示字段名（对应数据库）
     */
    public final static String[] DEFAULT_EMPLOY_FIELDS_DB = {"name", "telphone", "basicSalary", "fullWork", "EatAllow", "classPay", "allClassTime", "extraPay", "status"};

    /**
     * 员工默认展示字段名（对应中文名）
     */
    public final static String[] DEFAULT_EMPLOY_FIELDS_ZH = {"姓名", "电话", "基本工资", "全勤", "餐补", "课时费", "总课时（h）", "提成", "状态"};

    /**
     * 员工表全部字段名（对应数据库）
     */
    public final static String[] EMPLOY_FIELDS_DB = {"name", "sex", "telphone", "birthday", "entryDate", "jobID", "basicSalary", "fullWork", "EatAllow", "classPay", "allClassTime", "extraPay", "area", "status"};

    /**
     * 员工表全部字段名（对应中文名）
     */
    public final static String[] EMPLOY_FIELDS_ZH = {"姓名", "性别", "联系电话", "生日", "入职日期", "职位", "基本工资", "全勤", "餐补", "课时费", "总课时（h）", "提成", "校区", "状态"};

    /**
     * 合同表默认展示字段名（对应数据库）
     */
    public final static String[] DEFAULT_CONTRACT_FIELDS_DB = {"contractCode", "studentId", "signDate", "price", "actPay", "belongOne", "payType", "discount"};

    /**
     * 合同表默认展示字段名（对应中文名）
     */
    public final static String[] DEFAULT_CONTRACT_FIELDS_ZH = {"合同编号", "学生", "签约日期", "价格", "实际支付", "业绩所属", "支付方式", "折扣"};

    /**
     * 合同表全部字段名（对应数据库）
     */
    public final static String[] CONTRACT_FIELDS_DB = {"contractCode", "studentId", "signDate", "classPackageId", "price", "discount", "depositId", "actPay", "payType", "presentationClassTime", "startTime", "endTime", "belongOne", "memberCarId", "remark","classTime","remainClassTime","totalClassTime","schoolAreaId"};

    /**
     * 合同表全部字段名（对应中文名）
     */
    public final static String[] CONTRACT_FIELDS_ZH = {"合同编号", "学生", "签约日期", "课时包名称", "价格", "折扣", "订金", "实际支付", "支付方式", "赠送课时", "开始时间", "结束时间", "业绩所属", "会员卡编号", "备注","课时(h)","剩余课时(h)","总课时(h)","校区"};

    /**
     * 项目uploadFile相对路径
     */
    public static String UPLOADFILE_PATH;

    static {
        try {
            UPLOADFILE_PATH = ResourceUtils.getURL("classpath:").getPath()+"/static/uploadFile/";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
