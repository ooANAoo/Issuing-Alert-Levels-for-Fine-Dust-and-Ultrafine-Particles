package com.example.Issuing_Alert_Levels.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

//JSON데이터를 읽기위한 클래스 객체

@Getter
@Setter
public class AirQualityForJson {

    @Getter
    @JsonProperty("날짜")
    private LocalDateTime dateTime;

    @Getter
    @JsonProperty("측정소명")
    private String stationName;

    @Getter
    @JsonProperty("측정소코드")
    private String stationCode;

    @Getter
    @JsonProperty("PM10")
    private Integer PM10 = 0;

    @Getter
    @JsonProperty("PM2.5")
    private Integer PM25 = 0;

    // Getters and Setters...

    // JSON 파일에서 객체를 생성하기 위한 생성자
    @JsonCreator
    public AirQualityForJson(
            @JsonProperty("날짜") LocalDateTime dateTime,
            @JsonProperty("측정소명") String stationName,
            @JsonProperty("측정소코드") String stationCode,
            @JsonProperty("PM10") Integer PM10,
            @JsonProperty("PM2.5") Integer PM25) {

        this.dateTime = dateTime;
        this.stationCode = stationCode;
        this.stationName = stationName;
        this.PM10 = PM10;
        this.PM25 = PM25;
    }
    public AirQualityForJson(){

    }
    // Getters and Setters...


}
