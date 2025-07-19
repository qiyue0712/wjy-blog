package com.wangjiayue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wangjiayue.mapper")
public class WangBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(WangBlogApplication.class, args);
    }
}