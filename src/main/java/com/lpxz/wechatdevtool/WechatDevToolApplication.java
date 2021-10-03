package com.lpxz.wechatdevtool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class WechatDevToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatDevToolApplication.class, args);
    }

}
