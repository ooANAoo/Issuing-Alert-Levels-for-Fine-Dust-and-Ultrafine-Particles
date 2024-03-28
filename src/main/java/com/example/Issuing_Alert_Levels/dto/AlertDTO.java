package com.example.Issuing_Alert_Levels.dto;

import java.time.LocalDateTime;

public class AlertDTO {

    private String alertLevel;
    private LocalDateTime alertTime;
    private String stationCode;

    // Getters and Setters

    public AlertDTO(String alertLevel, LocalDateTime alertTime, String stationCode) {
        this.alertLevel = alertLevel;
        this.alertTime = alertTime;
        this.stationCode = stationCode;
    }
    public AlertDTO(){

    }


    public String getAlertLevel() {
        return alertLevel;
    }

    public void setAlertLevel(String alertLevel) {
        this.alertLevel = alertLevel;
    }

    public LocalDateTime getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(LocalDateTime alertTime) {
        this.alertTime = alertTime;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }
}