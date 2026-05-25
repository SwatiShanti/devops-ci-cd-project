package com.example.devopscicd;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * A simple JUnit 5 test class.
 * It ensures that the Spring Application Context loads successfully.
 * This is the basic test performed during the CI (Continuous Integration) phase.
 */
@SpringBootTest
class DevopsCiCdApplicationTests {

    @Test
    void contextLoads() {
        // This test passes if the Spring application context loads without throwing exceptions.
        // It acts as a basic check to verify if the project is configured correctly.
    }
}
