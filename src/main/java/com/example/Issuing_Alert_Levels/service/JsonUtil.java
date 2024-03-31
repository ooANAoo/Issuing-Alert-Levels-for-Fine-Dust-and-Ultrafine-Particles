package com.example.Issuing_Alert_Levels.service;

import com.fasterxml.jackson.databind.ObjectMapper;
//json 파싱 message클래스에 db쿼리 결과를 담기 위한 코드
public class JsonUtil {
    public static String convertObjectToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}