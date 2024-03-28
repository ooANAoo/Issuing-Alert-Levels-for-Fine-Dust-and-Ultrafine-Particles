package com.example.vga_price_tracker.repository;

import com.example.vga_price_tracker.domain.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    // 특정 경보 레벨의 경보만 조회
    List<Alert> findByAlertLevel(String alertLevel);

    // 최근 경보만 조회하는 사용자 정의 쿼리
    @Query(value = "SELECT * FROM alert ORDER BY alert_time DESC LIMIT 10", nativeQuery = true)
    List<Alert> findRecentAlerts();
}