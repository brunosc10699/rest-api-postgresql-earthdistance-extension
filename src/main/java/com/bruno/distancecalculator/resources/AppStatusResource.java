package com.bruno.distancecalculator.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AppStatusResource {

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAppStatus() {
        var status = new HashMap<String, Object>();
        status.put("service", "Distance Calculation API");
        status.put("status", "UP");
        status.put("statusCode", HttpStatus.OK.value());
        return ResponseEntity.ok(status);
    }
}
