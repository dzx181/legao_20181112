package com.zxq.legao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Description:
 * <p>
 * 启动类
 * </p>
 *
 * @author dengzhenxiang
 * @Date 2018/12/29 11:56
 */
@SpringBootApplication
@MapperScan(basePackages = "com.zxq.legao.dao")
@ServletComponentScan
public class LegaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LegaoApplication.class, args);
    }


}
