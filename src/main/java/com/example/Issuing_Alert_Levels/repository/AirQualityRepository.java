package com.example.Issuing_Alert_Levels.repository;

import com.example.Issuing_Alert_Levels.domain.AirQuality;
import com.example.Issuing_Alert_Levels.domain.AirQualityForJson;
import com.example.Issuing_Alert_Levels.dto.AirQualityAVGDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AirQualityRepository extends JpaRepository<AirQuality, Long> {

    // 특정 시간의 구별그룹화 AND 미세먼지 평균 값을 조건부 조회
    @Query("SELECT new com.example.Issuing_Alert_Levels.dto.AirQualityAVGDTO(MAX(a.dateTime),a.station.stationCode, AVG(a.PM25) AS PM25AVG, AVG(a.PM10) AS PM10AVG) " +
            "FROM AirQuality a " +
            "WHERE a.dateTime BETWEEN :startDate AND :endDate " +
            "GROUP BY a.station.stationCode " +
            "HAVING AVG(a.PM25) >= 75 OR AVG(a.PM10) >= 150")
    List<AirQualityAVGDTO> findStationsWithHighPollution(@Param("startDate") LocalDateTime startDate,
                                                             @Param("endDate") LocalDateTime endDate);
    // 최근 데이터만 조회하는 사용자 정의 쿼리
}