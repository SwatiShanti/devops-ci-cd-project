package com.example.devopscicd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point of the Spring Boot application.
 * The @SpringBootApplication annotation enables auto-configuration,
 * component scanning, and extra configuration on the application class.
 */
@SpringBootApplication
public class DevopsCiCdApplication {

    public static void main(String[] args) {
        // Runs the Spring Application
        SpringApplication.run(DevopsCiCdApplication.class, args);
    }
}
