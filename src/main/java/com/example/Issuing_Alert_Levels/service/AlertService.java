package com.example.Issuing_Alert_Levels.service;

import com.example.Issuing_Alert_Levels.domain.Alert;
import com.example.Issuing_Alert_Levels.dto.AirQualityAVGDTO;
import com.example.Issuing_Alert_Levels.dto.AlertDTO;
import com.example.Issuing_Alert_Levels.repository.AirQualityRepository;
import com.example.Issuing_Alert_Levels.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private AirQualityRepository airQualityRepository;

    @Transactional
    public List<AlertDTO> generateAndSaveAlerts(LocalDateTime current) {
        List<AirQualityAVGDTO> airQualityDataList = getAirQualityData(current);
        return airQualityDataList.stream()
                .map(this::createAndSaveAlert)
                .collect(Collectors.toList());
    }

    private List<AirQualityAVGDTO> getAirQualityData(LocalDateTime current) {
        LocalDateTime endTime = LocalDateTime.of(current.getYear(), current.getMonth(), current.getDayOfMonth(), current.getHour(), 0);
        LocalDateTime startTime = endTime.minusHours(2);
        return airQualityRepository.findAverageValuesByDateTimeBetween(startTime, endTime);
    }

    private AlertDTO createAndSaveAlert(AirQualityAVGDTO data) {
        Alert alert = new Alert(determineAlertLevel(data.getPM25AVG(), data.getPM10AVG()),
                LocalDateTime.now(),
                data.getStationName(),
                data.getStationCode());
        alertRepository.save(alert);
        return convertToDTO(alert);
    }

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
        return new AlertDTO(alert.getAlertLevel(), alert.getAlertTime(), alert.getStationCode(), alert.getStationName());
    }

    private String determineAlertLevel(double avgPm25, double avgPm10) {
        if (avgPm25 >= 150) {
            return "1";
        } else if (avgPm10 >= 300) {
            return "2";
        } else if (avgPm25 >= 75) {
            return "3";
        } else {
            return "4";
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class NoAlertsFoundException extends RuntimeException {
        public NoAlertsFoundException(String message) {
            super(message);
        }
    }
}
