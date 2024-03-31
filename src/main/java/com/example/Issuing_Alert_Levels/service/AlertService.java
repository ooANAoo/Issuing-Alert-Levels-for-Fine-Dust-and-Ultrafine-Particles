package com.example.Issuing_Alert_Levels.service;

import com.example.Issuing_Alert_Levels.domain.Alert;
import com.example.Issuing_Alert_Levels.dto.AirQualityAVGDTO;
import com.example.Issuing_Alert_Levels.dto.AirQualityDTO;
import com.example.Issuing_Alert_Levels.dto.AlertDTO;
import com.example.Issuing_Alert_Levels.repository.AirQualityRepository;
import com.example.Issuing_Alert_Levels.repository.AlertRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AlertService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private AlertRepository alertRepository;
    @Autowired
    private AirQualityRepository airQualityRepository;

    // 특정 시간의 구별 미세먼지 평균 값 그룹 조건부 조회후 리스트로 저장 후 해당 리스트 ALERT테이블에 저장 후 반환,
    @Transactional
    public List<AlertDTO> generateAndSaveAlerts(LocalDateTime current) {
        LocalDateTime endTime = LocalDateTime.of(current.getYear(), current.getMonth(),current.getDayOfMonth(), current.getHour(), 0);
        LocalDateTime startTime = endTime.minusHours(2);
        //조회 쿼리
        List<AirQualityAVGDTO> airQualityDataList = airQualityRepository.findAverageValuesByDateTimeBetween(startTime, endTime);
        List<AlertDTO> alerts = new ArrayList<>();
        for (AirQualityAVGDTO data : airQualityDataList) {
            String alertLevel = determineAlertLevel(data.getPM25AVG(), data.getPM10AVG());
            LocalDateTime alertTime = LocalDateTime.now();
            Alert alert = new Alert(alertLevel, alertTime, data.getStationName(),data.getStationCode());
            AlertDTO tempDTO = convertToDTO(alert);
            //삽입 쿼리
            alertRepository.save(alert);
            alerts.add(tempDTO);
        }
        return alerts;
    }

    //모든 경보 검색
    public List<AlertDTO> getAllAlerts() {
        List<Alert> alerts = alertRepository.findAll();

        if (alerts.isEmpty()) {
            throw new NoAlertsFoundException("No alerts available");
        }

        return alerts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    //도메인 -> dto 변환
    private AlertDTO convertToDTO(Alert alert) {
        if (alert == null) {
            throw new IllegalArgumentException("Alert entity cannot be null");
        }

        AlertDTO dto = new AlertDTO();
        dto.setAlertLevel(alert.getAlertLevel());
        dto.setAlertTime(alert.getAlertTime());
        dto.setStationCode(alert.getStationCode());
        dto.setStationName(alert.getStationName());
        return dto;
    }

    // 예외 클래스
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class NoAlertsFoundException extends RuntimeException {
        public NoAlertsFoundException(String message) {
            super(message);
        }
    }
    //  경보 단계 설정
    private String determineAlertLevel(double avgPm25, double avgPm10) {
        if (avgPm25 >= 150 ) {
            return "1";
        } else if (avgPm10 >= 300) {
            return "2";
        } else if (avgPm25 >= 75) {
            return "3";
        } else {
            return "4";
        }
    }
}