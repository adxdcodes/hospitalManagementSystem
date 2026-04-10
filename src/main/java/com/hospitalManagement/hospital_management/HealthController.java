package com.hospitalManagement.hospital_management;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/health")
public class HealthController {

    @GetMapping("/status")
    public ResponseEntity<Map<String, String>> getStatus() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        response.put("message", "Hospital Management API is running");
        response.put("timestamp", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/db-status")
    public ResponseEntity<Map<String, String>> getDbStatus() {
        Map<String, String> response = new HashMap<>();
        response.put("database", "MongoDB");
        response.put("connection", "using-mock-datastore");
        response.put("message", "Currently using in-memory MockDataStore for development");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/test-mongodb-connection")
    public ResponseEntity<Map<String, Object>> testMongoDBConnection(@RequestBody MongoDBConnectionRequest request) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Validate input
            if (request.getUri() == null || request.getUri().trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "MongoDB URI is required");
                response.put("timestamp", System.currentTimeMillis());
                return ResponseEntity.badRequest().body(response);
            }

            // Test connection using MongoDB Java driver
            com.mongodb.client.MongoClient mongoClient = null;
            try {
                mongoClient = com.mongodb.client.MongoClients.create(request.getUri());
                
                // Try to execute a ping command
                mongoClient.getDatabase("admin").runCommand(new org.bson.Document("ping", 1));
                
                response.put("success", true);
                response.put("message", "Successfully connected to MongoDB Atlas");
                response.put("database", "MongoDB Atlas");
                response.put("connectionStatus", "ACTIVE");
                response.put("timestamp", System.currentTimeMillis());
                
                return ResponseEntity.ok(response);
            } finally {
                if (mongoClient != null) {
                    mongoClient.close();
                }
            }
        } catch (com.mongodb.MongoSecurityException e) {
            response.put("success", false);
            response.put("message", "Authentication failed - Invalid credentials");
            response.put("error", e.getMessage());
            response.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.status(401).body(response);
        } catch (com.mongodb.MongoTimeoutException e) {
            response.put("success", false);
            response.put("message", "Connection timeout - MongoDB server unreachable");
            response.put("error", e.getMessage());
            response.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.status(504).body(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Connection failed");
            response.put("error", e.getMessage());
            response.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.status(500).body(response);
        }
    }

    public static class MongoDBConnectionRequest {
        private String uri;

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }
    }
}
