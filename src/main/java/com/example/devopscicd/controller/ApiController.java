package com.example.devopscicd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

/**
 * A simple REST Controller that exposes a single API endpoint.
 * The @RestController annotation marks this class as a controller where every method
 * returns a domain object (like a Map) directly as JSON instead of a view.
 */
@RestController
public class ApiController {

    /**
     * HTTP GET Endpoint mapping for the root URL "/".
     * When a user visits http://localhost:8080/, this method will execute.
     * 
     * @return A map containing success message and system details.
     */
    @GetMapping("/")
    public Map<String, Object> getMessage() {
        Map<String, Object> response = new HashMap<>();
        
        // Add beginner-friendly details to the response
        response.put("status", "Success");
        response.put("message", "Welcome to my DevOps CI/CD Pipeline Project!");
        response.put("framework", "Java Spring Boot");
        response.put("tooling", "Maven & Docker");
        response.put("pipeline", "GitHub Actions to Docker Hub");
        response.put("timestamp", new Date());
        
        return response;
    }
}
