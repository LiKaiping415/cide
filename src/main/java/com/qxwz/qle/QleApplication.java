package com.qxwz.qle;

import com.qxwz.galaxy.api.util.AppEnv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QleApplication {

    public static void main(String[] args) {
        AppEnv.init("qle");
        SpringApplication application = new SpringApplication(QleApplication.class);
        application.run(args);
    }

}
