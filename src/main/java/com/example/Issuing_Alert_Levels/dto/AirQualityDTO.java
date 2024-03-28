package com.example.Issuing_Alert_Levels.dto;

public class AirQualityDTO {


    private String dateTime;
    private String stationName;
    private String stationCode;
    private Integer PM10;
    private Integer PM25;

    // Getters and Setters

    public AirQualityDTO(String dateTime, String stationName, String stationCode, Integer PM10, Integer PM25) {
        this.dateTime = dateTime;
        this.stationName = stationName;
        this.stationCode = stationCode;
        this.PM10 = PM10;
        this.PM25 = PM25;
    }
    public AirQualityDTO(){
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public Integer getPM10() {
        return PM10;
    }

    public void setPM10(Integer PM10) {
        this.PM10 = PM10;
    }

    public Integer getPM25() {
        return PM25;
    }

    public void setPM25(Integer PM25) {
        this.PM25 = PM25;
    }
}
