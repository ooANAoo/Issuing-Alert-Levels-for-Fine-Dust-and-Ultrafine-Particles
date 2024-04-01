package com.example.Issuing_Alert_Levels.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

//주어진 데이터 ,미세 먼지 테이블 자동 생성
@Entity
@Table(name = "AIRQUALITY", indexes = {
        @Index(name = "idx_date_time", columnList = "date_time")
})
@Getter
@Setter
public class AirQuality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_time", nullable = false)

    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "station_code", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Station station;

    @Column(name = "pm10", nullable = true)

    private Integer PM10 = 0;

    @Column(name = "pm25", nullable = true)

    private Integer PM25 = 0;

    // Getters and Setters...

    public AirQuality() {

    }


    // Getters and Setters...

}
