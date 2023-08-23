package com.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author Tianyi Fu
 * @since 2023/7/5
 */
@MapperScan(basePackages = {"com.demo.mapper"})
@ServletComponentScan
@SpringBootApplication()
public class TalkativeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TalkativeApplication.class,args);
    }
}
