package com.canal;

import com.xpand.starter.canal.annotation.EnableCanalClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//未修
@SpringBootApplication
@EnableCanalClient
public class canalApplication {
    public static void main(String[] args) {
        SpringApplication.run(canalApplication.class,args);
    }
}
