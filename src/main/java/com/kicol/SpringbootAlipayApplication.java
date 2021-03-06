package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootAlipayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAlipayApplication.class, args);
    }

}

