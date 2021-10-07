package com.lpxz.wechatdevtool;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lpxz.wechatdevtool.mapper")
public class WechatDevToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatDevToolApplication.class, args);
    }

}
