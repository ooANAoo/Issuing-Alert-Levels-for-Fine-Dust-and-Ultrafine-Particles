    package com.example.Issuing_Alert_Levels.controller;


    import com.example.Issuing_Alert_Levels.dto.AirQualityDTO;
    import com.example.Issuing_Alert_Levels.service.AirQualityService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.messaging.Message;
    import org.springframework.messaging.handler.annotation.MessageMapping;
    import org.springframework.messaging.handler.annotation.Payload;
    import org.springframework.messaging.handler.annotation.SendTo;
    import org.springframework.messaging.simp.SimpMessagingTemplate;
    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    import java.util.List;
    @RestController


    @RequestMapping("/api/air-quality")
    public class AirQualityController {
        // 소켓 통신을 하기 위한 api
        @Autowired
        private SimpMessagingTemplate simpMessagingTemplate;
        // AirQuality데이터를 다루기위한 메소드 집합
        @Autowired
        private AirQualityService airQualityService;


        // 모든 미세먼지 데이터 조회
        @GetMapping
        public ResponseEntity<List<AirQualityDTO>> getAllAirQualityData() {
            List<AirQualityDTO> data = airQualityService.getAllAirQualityData();
            return ResponseEntity.ok(data);
        }


        // 에러 핸들링
        @ExceptionHandler(AirQualityService.NoDataFoundException.class)
        public ResponseEntity<String> handleNoDataFound(AirQualityService.NoDataFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
