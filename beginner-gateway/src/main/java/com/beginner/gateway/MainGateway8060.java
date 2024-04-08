package com.beginner.gateway;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MainGateway8060 {

    // 启动8080网关
    public static void main(String[] args) {
            SpringApplication.run(MainGateway8060.class,args);
            System.out.println("-----------网关模块启动成功-------------");
        }

}