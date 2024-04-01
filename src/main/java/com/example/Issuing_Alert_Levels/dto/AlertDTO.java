package com.example.Issuing_Alert_Levels.dto;

import com.example.Issuing_Alert_Levels.domain.Station;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
//경보테이블에 데이터를 저장하기 위한
@Getter
@Setter
public class AlertDTO {

    private String alertLevel;
    private LocalDateTime alertTime;
    private Station station;

    public AlertDTO(String alertLevel, LocalDateTime alertTime, Station station) {
        this.alertLevel = alertLevel;
        this.alertTime = alertTime;
        this.station = station;
    }
    public AlertDTO(){

    }


}