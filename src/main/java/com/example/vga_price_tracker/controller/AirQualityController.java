package com.example.vga_price_tracker.controller;


import com.example.vga_price_tracker.dto.AirQualityDTO;
import com.example.vga_price_tracker.service.AirQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api/air-quality")
public class AirQualityController {

    @Autowired
    private AirQualityService airQualityService;

    // 모든 미세먼지 데이터 조회
    @GetMapping
    public ResponseEntity<List<AirQualityDTO>> getAllAirQualityData() {
        List<AirQualityDTO> data = airQualityService.getAllAirQualityData();
        System.out.println(data);
        return ResponseEntity.ok(data);
    }

    // 에러 핸들링
    @ExceptionHandler(AirQualityService.NoDataFoundException.class)
    public ResponseEntity<String> handleNoDataFound(AirQualityService.NoDataFoundException e) {
        return ResponseEntity.notFound().build();
    }
}