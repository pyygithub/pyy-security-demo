package com.pyy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.pyy.**.dao")
@SpringBootApplication
public class PyySecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PyySecurityDemoApplication.class, args);
    }
}
