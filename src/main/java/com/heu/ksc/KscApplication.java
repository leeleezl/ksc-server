package com.heu.ksc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class KscApplication {

    public static void main(String[] args) {
        SpringApplication.run(KscApplication.class, args);
    }

}
