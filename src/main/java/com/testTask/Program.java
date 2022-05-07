package com.testTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.testTask"})
public class Program {
    public static void main(String[] args) {
        SpringApplication.run(Program.class, args);
    }

}
