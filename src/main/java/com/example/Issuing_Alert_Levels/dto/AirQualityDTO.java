package com.example.Issuing_Alert_Levels.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//미세먼지 테이블에 데이터를 저장하기위한 DTO
@Getter
@Setter
public class AirQualityDTO {

    private long id;
    private LocalDateTime dateTime;
    private String stationCode;
    private Integer PM10;
    private Integer PM25;

    // Getters and Setters

    public AirQualityDTO(long id,LocalDateTime dateTime, String stationCode, Integer PM10, Integer PM25) {
        this.id = id;
        this.dateTime = dateTime;

        this.stationCode = stationCode;
        this.PM10 = PM10;
        this.PM25 = PM25;
    }
    public AirQualityDTO(){
    }

}
