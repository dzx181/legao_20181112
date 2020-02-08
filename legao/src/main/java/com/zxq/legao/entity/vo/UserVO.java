package com.zxq.legao.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String status;
    private Date createDate;
    private Boolean ifLoginSuccess;
    private String selectStudentFields;
    private String selectEmployFields;
    private String selectContractFields;


}
