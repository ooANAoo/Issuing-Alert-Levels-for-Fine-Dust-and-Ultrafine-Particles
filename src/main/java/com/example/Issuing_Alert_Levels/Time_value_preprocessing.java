//package com.example.Issuing_Alert_Levels;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import lombok.Getter;
//
//import java.io.File;
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Arrays;
//// xx-xx-xx 24 이러한 날짜를 xx-xx-xx+1 00 으로 바꾸기 위한 코드. json 날짜 데이터를 localTime 클래스처럼 , 시간 객체로 다루기 위해서.
//public class Time_value_preprocessing {
//    @Getter
//    @JsonProperty("날짜")
//    private String dateTime;
//
//    @JsonProperty("측정소명")
//    private String stationName;
//
//    @JsonProperty("측정소코드")
//    private String stationCode;
//
//    @JsonProperty("PM10")
//    private int pm10;
//
//    @JsonProperty("PM2.5")
//    private int pm25;
//
//    // Getters and Setters...
//
//    public void setDateTime(String dateTime) {
//        this.dateTime = dateTime;
//    }
//
//    // Other getters and setters...
//
//    public static void main(String[] args) {
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            // 파일 주소 넣기
//            File file = new File("src/main/resources/2023년3월_서울시_미세먼지.json");
//            Time_value_preprocessing[] data = mapper.readValue(file, Time_value_preprocessing[].class);
//            Arrays.stream(data).forEach(item -> item.setDateTime(convertDateTime(item.getDateTime())));
//            mapper.enable(SerializationFeature.INDENT_OUTPUT);
//            mapper.writeValue(file, data);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static String convertDateTime(String dateTime) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
//        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
//
//        if (localDateTime.getHour() == 24) {
//            localDateTime = localDateTime.withHour(0).plusDays(1);
//        }
//
//        return localDateTime.format(formatter);
//    }
//}
