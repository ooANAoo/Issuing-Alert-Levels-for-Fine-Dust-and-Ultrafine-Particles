package com.example.Issuing_Alert_Levels.service;

import com.example.Issuing_Alert_Levels.domain.Alert;
import com.example.Issuing_Alert_Levels.domain.Station;
import com.example.Issuing_Alert_Levels.dto.AirQualityAVGDTO;
import com.example.Issuing_Alert_Levels.dto.AlertDTO;
import com.example.Issuing_Alert_Levels.repository.AirQualityRepository;
import com.example.Issuing_Alert_Levels.repository.AlertRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private AirQualityRepository airQualityRepository;

    @PersistenceContext
    private EntityManager entityManager;
    //조건에 따른 AirQuality행 조회 이후, 해당 행들을 Alert테이블에 저장 and AlertDTO로 변환 한 이후 MainSystem에 리턴
    @Transactional
    public List<AlertDTO> generateAndSaveAlerts(LocalDateTime current) {
        List<AirQualityAVGDTO> airQualityDataList = getAirQualityData(current);
        // 리스트 전부 Alert테이블에 저장
        return airQualityDataList.stream()
                .map(this::createAndSaveAlert)
                .collect(Collectors.toList());
    }

    //경보 조건에 부합하는 모든 AirQuality테이블의 행 얻어오기
    private List<AirQualityAVGDTO> getAirQualityData(LocalDateTime current) {
        LocalDateTime endTime = LocalDateTime.of(current.getYear(), current.getMonth(), current.getDayOfMonth(), current.getHour(), 0);
        LocalDateTime startTime = endTime.minusHours(2);
        return airQualityRepository.findStationsWithHighPollution(startTime, endTime);
    }
    // Alert테이블에 반환 받은 AirQualityAVGDTO 저장하기
    private AlertDTO createAndSaveAlert(AirQualityAVGDTO data) {
        Station station = entityManager.find(Station.class, data.getStationCode()); // 이미 존재하는지 확인
        if (station == null) {
            station = new Station();
            station.setStationCode(data.getStationCode());
            station.setStationName(convertStationName(data.getStationCode()));
            entityManager.persist(station); // 영속성 컨텍스트에 엔티티를 추가
        }

        // 이미 존재하는 Alert인지 확인
        Alert existingAlert = alertRepository.findByStationStationCodeAndAlertTime(station.getStationCode(), data.getDateTime());
        if (existingAlert != null) {
            // 이미 존재하는 Alert이므로 저장하지 않고 바로 DTO로 변환하여 리턴
            return convertToDTO(existingAlert);
        }

        // 존재하지 않는 경우에만 Alert 생성 및 저장
        Alert alert = new Alert(determineAlertLevel(data.getPM25AVG(), data.getPM10AVG()), data.getDateTime(), station);
        alertRepository.save(alert);
        return convertToDTO(alert);
    }
    //서버에서 반환 받은 지역 코드를 기준으로 어느 구인지 매핑
    public String convertStationName(String stationCode){
        if(stationCode.equals("111212")) return  "강서구";
        if(stationCode.equals("111301")) return  "양천구";
        if(stationCode.equals("111221")) return  "구로구";
        if(stationCode.equals("111201")) return  "마포구";
        if(stationCode.equals("'111281'")) return  "금천구";
        if(stationCode.equals("111231")) return  "영등포구";
        if(stationCode.equals("111191")) return  "양천구";
        if(stationCode.equals("'111301'")) return  "서대문구";
        if(stationCode.equals("111181")) return  "은평구";
        if(stationCode.equals("111251")) return  "관악구";
        if(stationCode.equals("111241")) return  "동작구";
        if(stationCode.equals("111131")) return  "용산구";
        if(stationCode.equals("111121")) return  "중구";
        if(stationCode.equals("111123")) return  "종로구";
        if(stationCode.equals("111161")) return  "성북구";
        if(stationCode.equals("111291")) return  "강북구";
        if(stationCode.equals("111171")) return  "도봉구";
        if(stationCode.equals("111311")) return  "노원구";
        if(stationCode.equals("111151")) return  "중랑구";
        if(stationCode.equals("111152")) return  "동대문구";
        if(stationCode.equals("'111142''")) return  "성동구";
        if(stationCode.equals("111141")) return  "광진구";
        if(stationCode.equals("111262")) return  "서초구";
        if(stationCode.equals("111261")) return  "강남구";
        if(stationCode.equals("111273")) return  "송파구";
        if(stationCode.equals("111274")) return  "강동구";
        else return "알 수 없는 지역";
    }

    //Alert테이블 모든  데이터 조회
    public List<AlertDTO> getAllAlerts() {
        List<Alert> alerts = alertRepository.findAll();
        if (alerts.isEmpty()) {
            throw new NoAlertsFoundException("No alerts available");
        }
        return alerts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    //Alert 클래스 AlertDTO변환
    private AlertDTO convertToDTO(Alert alert) {
        Station sta = new Station();
        sta.setStationCode(alert.getStation().getStationCode());
        sta.setStationName(alert.getStation().getStationName());
        return new AlertDTO(alert.getAlertLevel(), alert.getAlertTime(), sta);
    }
    //각 구별 현재시간에서 2시간 이전 시간 이내의 pm2.5,pm10의 평균값을 기준으로 경보 등급 정의. 특이사항 p25를 기준으로 먼저 등급을 정의 하고, p25가 정의 됐으면 p10은 정의 하지않고 무시한다.
    private String determineAlertLevel(double avgPm25, double avgPm10) {
        if (avgPm25 >= 150) {
            return "1";
        } else if (avgPm10 >= 300) {
            return "2";
        } else if (avgPm25 >= 75) {
            return "3";
        } else {
            return "4";
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class NoAlertsFoundException extends RuntimeException {
        public NoAlertsFoundException(String message) {
            super(message);
        }
    }
}
