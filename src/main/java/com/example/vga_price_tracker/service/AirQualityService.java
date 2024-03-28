package com.example.vga_price_tracker.service;
import com.fasterxml.jackson.core.type.TypeReference; // 올바른 TypeReference import
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.util.List;

import com.example.vga_price_tracker.domain.AirQuality;
import com.example.vga_price_tracker.dto.AirQualityDTO;
import com.example.vga_price_tracker.repository.AirQualityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AirQualityService {

    @Autowired
    private AirQualityRepository airQualityRepository;


    public void importDataFromJson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource("2023년3월_서울시_미세먼지.json");
        InputStream inputStream = resource.getInputStream();
        List<AirQuality> airQualityList = mapper.readValue(inputStream, new TypeReference<List<AirQuality>>() {});

        airQualityRepository.saveAll(airQualityList);
    }

    public List<AirQualityDTO> getAllAirQualityData() {
        List<AirQuality> airQualityList = airQualityRepository.findAll();

        if (airQualityList.isEmpty()) {
            throw new NoDataFoundException("No air quality data available");
        }

        return airQualityList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private AirQualityDTO convertToDTO(AirQuality airQuality) {
        if (airQuality == null) {
            throw new IllegalArgumentException("AirQuality entity cannot be null");
        }

        AirQualityDTO dto = new AirQualityDTO();
        dto.setDateTime(airQuality.getDateTime());
        dto.setStationName(airQuality.getStationName());
        dto.setStationCode(airQuality.getStationCode());
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
