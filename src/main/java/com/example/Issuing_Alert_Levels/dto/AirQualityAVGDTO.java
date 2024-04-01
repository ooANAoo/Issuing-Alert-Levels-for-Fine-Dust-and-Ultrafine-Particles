package com.example.Issuing_Alert_Levels.dto;

import com.example.Issuing_Alert_Levels.domain.Station;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

// 각 구별로 미세먼지 2시간 이내 평균 값을 계산하고 저장하기위한 DTO
@Getter
@Setter
public class AirQualityAVGDTO {

    private LocalDateTime dateTime;
    private String stationCode;  // Station 객체 대신 stationCode를 사용
    private Double PM25AVG;
    private Double PM10AVG;

    public AirQualityAVGDTO(LocalDateTime dateTime, String stationCode, Double PM25AVG, Double PM10AVG) {
        this.dateTime = dateTime;
        this.stationCode = stationCode;
        this.PM25AVG = PM25AVG;
        this.PM10AVG = PM10AVG;
    }

}
