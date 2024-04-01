package com.example.Issuing_Alert_Levels.service;
import com.example.Issuing_Alert_Levels.domain.AirQuality;
import com.example.Issuing_Alert_Levels.domain.Station;
import com.example.Issuing_Alert_Levels.repository.StationRepository;
import com.fasterxml.jackson.core.type.TypeReference; // 올바른 TypeReference import
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.util.List;

import com.example.Issuing_Alert_Levels.domain.AirQualityForJson;
import com.example.Issuing_Alert_Levels.dto.AirQualityDTO;
import com.example.Issuing_Alert_Levels.repository.AirQualityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;


@Service
public class AirQualityService {

    @Autowired
    private AirQualityRepository airQualityRepository;
    @Autowired
    private StationRepository stationRepository;
    @PersistenceContext
    private EntityManager entityManager;

    //AirQuality테이블에 삽입
    @Transactional
    public void saveAirQualityDatas(List<AirQualityForJson> airQualityList) {
        for (AirQualityForJson airQuality : airQualityList) {
            Station station = new Station();
            station.setStationName(airQuality.getStationName());
            station.setStationCode(airQuality.getStationCode());
            AirQuality AQ = new AirQuality();
            AQ.setPM25(airQuality.getPM25());
            AQ.setDateTime(airQuality.getDateTime());
            AQ.setPM10(airQuality.getPM10());
            AQ.setStation(station);
            entityManager.persist(AQ); // 영속성 컨텍스트에 엔티티를 추가
        }
    }
    // JSON 파일 DB에 저장
    @Transactional
    public void importDataFromJson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.findAndRegisterModules();

        ClassPathResource resource = new ClassPathResource("data2023.json");
        try (InputStream inputStream = resource.getInputStream()) {
            List<AirQualityForJson> airQualityList = mapper.readValue(inputStream, new TypeReference<List<AirQualityForJson>>() {});
            saveAirQualityDatas(airQualityList);
            entityManager.flush(); // 영속성 컨텍스트를 플러시하여 데이터베이스에 즉시 반영
            entityManager.clear(); // 영속성 컨텍스트를 클리어하여 메모리 부담을 줄임
            }
        }



    // 모든 미세먼지 데이터 조회
    public List<AirQualityDTO> getAllAirQualityData() {
        List<AirQuality> airQualityList = airQualityRepository.findAll();

        if (airQualityList.isEmpty()) {
            throw new NoDataFoundException("No air quality data available");
        }

        return airQualityList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    //미세먼지 데이터 DTO변환
    private AirQualityDTO convertToDTO(AirQuality airQuality) {
        if (airQuality == null) {
            throw new IllegalArgumentException("AirQuality entity cannot be null");
        }

        AirQualityDTO dto = new AirQualityDTO();
        dto.setDateTime(airQuality.getDateTime());
        dto.setStationCode(airQuality.getStation().getStationCode());
        dto.setPM10(airQuality.getPM10());
        dto.setPM25(airQuality.getPM25());
        return dto;
    }

    // 예외 클래스
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class NoDataFoundException extends RuntimeException {
        public NoDataFoundException(String message) {
            super(message);
        }
    }
}
