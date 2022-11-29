package com.qxwz.qle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QleApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(QleApplication.class);
        application.run(args);
    }

}
