package com.example.vga_price_tracker.service;

import com.example.vga_price_tracker.domain.Alert;
import com.example.vga_price_tracker.dto.AlertDTO;
import com.example.vga_price_tracker.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    public List<AlertDTO> getAllAlerts() {
        List<Alert> alerts = alertRepository.findAll();

        if (alerts.isEmpty()) {
            throw new NoAlertsFoundException("No alerts available");
        }

        return alerts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private AlertDTO convertToDTO(Alert alert) {
        if (alert == null) {
            throw new IllegalArgumentException("Alert entity cannot be null");
        }

        AlertDTO dto = new AlertDTO();
        dto.setAlertLevel(alert.getAlertLevel());
        dto.setAlertTime(alert.getAlertTime());
        dto.setStationCode(alert.getStationCode());
        return dto;
    }

    // 예외 클래스
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class NoAlertsFoundException extends RuntimeException {
        public NoAlertsFoundException(String message) {
            super(message);
        }
    }
}