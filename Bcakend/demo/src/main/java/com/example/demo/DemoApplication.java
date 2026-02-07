package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class DemoApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);

        Environment env = context.getEnvironment();
        String hostNum = env.getProperty("server.port");
        System.out.println("http://localhost:" + hostNum + "/products");
    }

}
