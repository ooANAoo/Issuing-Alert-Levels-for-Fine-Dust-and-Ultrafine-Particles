package com.example.vga_price_tracker.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "AIRQUALITY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AirQuality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_time", nullable = false)
    @JsonProperty("날짜")
    private String dateTime;

    @Column(name = "station_name", nullable = false)
    @JsonProperty("측정소명")
    private String stationName;

    @Column(name = "station_code", nullable = false)
    @JsonProperty("측정소코드")
    private String stationCode;

    @Column(name = "pm10", nullable = true)
    @JsonProperty("PM10")
    private Integer PM10 = 0;

    @Column(name = "pm25", nullable = true)
    @JsonProperty("PM2.5")
    private Integer PM25 = 0;

    // Getters and Setters...

    // JSON 파일에서 객체를 생성하기 위한 생성자
    @JsonCreator
    public AirQuality(
            @JsonProperty("id") Long id,
            @JsonProperty("날짜") String dateTime,
            @JsonProperty("측정소명") String stationName,
            @JsonProperty("측정소코드") String stationCode,
            @JsonProperty("PM10") Integer PM10,
            @JsonProperty("PM2.5") Integer PM25) {
        this.id = id;
        this.dateTime = dateTime;
        this.stationName = stationName;
        this.stationCode = stationCode;
        this.PM10 = PM10;
        this.PM25 = PM25;
    }
    // Getters and Setters...


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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

    public Integer getPM10() {
        return PM10;
    }

    public void setPM10(Integer PM10) {
        this.PM10 = PM10;
    }

    public Integer getPM25() {
        return PM25;
    }

    public void setPM25(Integer PM25) {
        this.PM25 = PM25;
    }


}
