package com.example.vga_price_tracker.repository;

import com.example.vga_price_tracker.domain.AirQuality;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AirQualityRepository extends JpaRepository<AirQuality, Long> {

    // 특정 스테이션 코드에 대한 데이터 조회
    List<AirQuality> findByStationCode(String stationCode);

    // 최근 데이터만 조회하는 사용자 정의 쿼리
    @Query("SELECT aq FROM AirQuality aq ORDER BY aq.dateTime DESC")
    List<AirQuality> findLatestData();
}