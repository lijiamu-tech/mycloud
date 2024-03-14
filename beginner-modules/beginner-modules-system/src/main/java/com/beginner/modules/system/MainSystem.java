package com.beginner.modules.system;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainSystem {
    public static void main(String[] args) {
        SpringApplication.run(MainSystem.class,args);
        System.out.println("-----------系统模块启动成功-------------");
    }


}