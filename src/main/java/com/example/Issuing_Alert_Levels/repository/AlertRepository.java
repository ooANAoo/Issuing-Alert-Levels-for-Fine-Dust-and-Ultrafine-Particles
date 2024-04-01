package com.example.Issuing_Alert_Levels.repository;

import com.example.Issuing_Alert_Levels.domain.Alert;
import com.example.Issuing_Alert_Levels.domain.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {


        // Station과 LocalDateTime으로 Alert 검색

    // Station 코드와 DateTime으로 Alert 검색
    Alert findByStationStationCodeAndAlertTime(String stationCode, LocalDateTime dateTime);

    // 특정 경보 레벨의 경보만 조회
    List<Alert> findByAlertLevel(String alertLevel);
    //특정 시간 사이에 경보 조회
    List<Alert> findByAlertTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

    // 최근 경보만 조회하는 사용자 정의 쿼리
    @Query(value = "SELECT * FROM alert ORDER BY alert_time DESC LIMIT 10", nativeQuery = true)
    List<Alert> findRecentAlerts();
}