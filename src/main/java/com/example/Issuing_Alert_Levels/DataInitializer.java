//package com.example.Issuing_Alert_Levels;
//
//
//import com.example.Issuing_Alert_Levels.service.AirQualityService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//
//
////서울시 미세먼지 json 데이터를 db에 적재하기 위한 코드
//@Component
//public class DataInitializer implements CommandLineRunner {
//
//    @Autowired
//    private AirQualityService airQualityService;
//
//    @Override
//    public void run(String... args) throws Exception {
//        airQualityService.importDataFromJson();
//    }
//}