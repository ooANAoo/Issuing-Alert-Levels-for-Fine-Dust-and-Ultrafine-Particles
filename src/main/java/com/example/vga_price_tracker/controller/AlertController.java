package com.example.vga_price_tracker.controller;

import com.example.vga_price_tracker.dto.AlertDTO;
import com.example.vga_price_tracker.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api/alerts")
public class AlertController {
    @Autowired
    private AlertService alertService;

    // 모든 경보 데이터 조회
    @GetMapping
    public ResponseEntity<List<AlertDTO>> getAllAlerts() {
        List<AlertDTO> alerts = alertService.getAllAlerts();
        System.out.println(alerts);
        return ResponseEntity.ok(alerts);
    }

    // 에러 핸들링
    @ExceptionHandler(AlertService.NoAlertsFoundException.class)
    public ResponseEntity<String> handleNoAlertsFound(AlertService.NoAlertsFoundException e) {
        return ResponseEntity.notFound().build();
    }
}
