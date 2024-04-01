package com.example.Issuing_Alert_Levels.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Issuing_Alert_Levels.domain.Station;

public interface StationRepository extends JpaRepository<Station, Long> {

    Station findByStationCode(String stationCode);
}