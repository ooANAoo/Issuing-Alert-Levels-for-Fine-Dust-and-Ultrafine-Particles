package com.example.vga_price_tracker.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

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

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public Alert(Long id, String alertLevel, LocalDateTime alertTime, String stationCode) {
        this.id = id;
        this.alertLevel = alertLevel;
        this.alertTime = alertTime;
        this.stationCode = stationCode;
    }
}
