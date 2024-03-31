package com.example.Issuing_Alert_Levels.dto;

import lombok.Getter;

import java.time.LocalDateTime;

// 각 구별로 미세먼지 2시간 이내 평균 값을 계산하고 저장하기위한 DTO
@Getter
public class AirQualityAVGDTO {

    private LocalDateTime dateTime;
    private String stationName;
    private String stationCode;
    private Double PM25AVG;
    private Double PM10AVG;

    public AirQualityAVGDTO(LocalDateTime dateTime, String stationName, String stationCode, Double PM25AVG, Double PM10AVG) {
        this.dateTime = dateTime;
        this.stationName = stationName;
        this.stationCode = stationCode;
        this.PM25AVG = PM25AVG;
        this.PM10AVG = PM10AVG;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public void setPM25AVG(Double PM25AVG) {
        this.PM25AVG = PM25AVG;
    }

    public void setPM10AVG(Double PM10AVG) {
        this.PM10AVG = PM10AVG;
    }
}
