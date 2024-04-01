package com.example.Issuing_Alert_Levels.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//미세먼지 경보 테이블 자동생성
@Entity
@Table(name = "ALERT")
@Getter
@Setter
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "alert_level")
    private String alertLevel;
    @Column(name = "alert_time")
    private LocalDateTime alertTime;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "station_code", nullable = false)
    private Station station;
    // Getters and Setters...

    // 기본 생성자 추가
    public Alert() {
    }
    public Alert(String alertLevel, LocalDateTime alertTime, Station station) {
        this.alertLevel = alertLevel;
        this.alertTime = alertTime;
        this.station = station;
    }
}
