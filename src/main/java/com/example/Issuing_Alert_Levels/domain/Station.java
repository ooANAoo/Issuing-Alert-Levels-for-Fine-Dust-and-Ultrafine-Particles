package com.example.Issuing_Alert_Levels.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Stations")
@Getter
@Setter
public class Station {
    @Id
    @Column(name = "station_code", length = 10)
    private String stationCode;
    @Column(name = "station_name", nullable = false, length = 30)
    private String stationName;

    public Station() {

    }


// Getters and setters
}
