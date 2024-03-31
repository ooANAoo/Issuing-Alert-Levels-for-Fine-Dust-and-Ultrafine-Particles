package com.example.Issuing_Alert_Levels.dto;

import java.time.LocalDateTime;
//경보테이블에 데이터를 저장하기 위한 
public class AlertDTO {

    private String alertLevel;
    private LocalDateTime alertTime;
    private String stationCode;
    private String stationName;
    // Getters and Setters

    public AlertDTO(String alertLevel, LocalDateTime alertTime, String stationCode,String stationName) {
        this.alertLevel = alertLevel;
        this.alertTime = alertTime;
        this.stationCode = stationCode;
        this.stationName = stationName;
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

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}