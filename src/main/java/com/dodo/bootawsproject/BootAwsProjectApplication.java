package com.dodo.bootawsproject;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @EnableJpaAuditing // jpa auditing 활성화 > 테스트문제 때문에 삭제.
@SpringBootApplication
public class BootAwsProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootAwsProjectApplication.class, args);
    }

}
