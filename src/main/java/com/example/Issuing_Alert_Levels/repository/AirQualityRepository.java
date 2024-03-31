package com.example.Issuing_Alert_Levels.repository;

import com.example.Issuing_Alert_Levels.domain.AirQuality;
import com.example.Issuing_Alert_Levels.dto.AirQualityAVGDTO;
import com.example.Issuing_Alert_Levels.dto.AirQualityDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AirQualityRepository extends JpaRepository<AirQuality, Long> {

    // 특정 시간의 구별 미세먼지 평균 값 그룹 조건부 조회
    List<AirQuality> findByStationCode(String stationCode);
    @Query("SELECT new com.example.Issuing_Alert_Levels.dto.AirQualityAVGDTO(a.dateTime, a.stationName, a.stationCode,AVG(a.PM25) as PM25AVG, AVG(a.PM10) as PM10AVG) " +
            "FROM AirQuality a WHERE a.dateTime BETWEEN :startTime AND :endTime " +
            "GROUP BY a.stationCode, a.stationName " +
            "HAVING AVG(a.PM25) >= 75 OR AVG(a.PM10) >= 150")
    List<AirQualityAVGDTO> findAverageValuesByDateTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

    // 최근 데이터만 조회하는 사용자 정의 쿼리
}