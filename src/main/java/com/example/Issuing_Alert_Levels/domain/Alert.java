package com.example.Issuing_Alert_Levels.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

//미세먼지 경보 테이블 자동생성
@Entity
@Table(name = "ALERT")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "alert_level")
    private String alertLevel;
    @Column(name = "alert_time")
    private LocalDateTime alertTime;
    @Column(name = "station_name")
    private String stationName;
    @Column(name = "station_code")
    private String stationCode;

    // Getters and Setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public Alert(String alertLevel, LocalDateTime alertTime, String stationName, String stationCode) {
        this.alertLevel = alertLevel;
        this.alertTime = alertTime;
        this.stationName = stationName;
        this.stationCode = stationCode;
    }
}
