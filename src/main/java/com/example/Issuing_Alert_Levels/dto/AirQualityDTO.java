package com.example.Issuing_Alert_Levels.dto;

import lombok.Getter;

import java.time.LocalDateTime;

//미세먼지 테이블에 데이터를 저장하기위한 D
@Getter
public class AirQualityDTO {

    private long id;
    private LocalDateTime dateTime;
    private String stationName;
    private String stationCode;
    private Integer PM10;
    private Integer PM25;

    // Getters and Setters

    public AirQualityDTO(long id,LocalDateTime dateTime, String stationName, String stationCode, Integer PM10, Integer PM25) {
        this.id = id;
        this.dateTime = dateTime;
        this.stationName = stationName;
        this.stationCode = stationCode;
        this.PM10 = PM10;
        this.PM25 = PM25;
    }
    public AirQualityDTO(){
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

    public void setPM10(Integer PM10) {
        this.PM10 = PM10;
    }

    public void setPM25(Integer PM25) {
        this.PM25 = PM25;
    }
}
