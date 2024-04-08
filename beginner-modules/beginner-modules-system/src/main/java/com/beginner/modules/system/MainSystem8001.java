package com.beginner.modules.system;


import lombok.extern.slf4j.Slf4j;
//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@Slf4j
@EnableDiscoveryClient
//@MapperScan("com.beginner.modules.system.mapper") //扫描mapper
public class MainSystem8001 {
    public static void main(String[] args) {
        SpringApplication.run(MainSystem8001.class,args);
        System.out.println("-----------系统模块启动成功-------------");
    }

}