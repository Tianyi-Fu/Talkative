package com.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Tianyi Fu
 * @since 2023/7/5
 */
@MapperScan(basePackages = {"com.demo.mapper"})
@SpringBootApplication()
public class TalkativeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TalkativeApplication.class,args);
    }
}
