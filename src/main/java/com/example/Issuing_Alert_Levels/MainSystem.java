package com.example.Issuing_Alert_Levels;

import com.example.Issuing_Alert_Levels.controller.WebController;
import com.example.Issuing_Alert_Levels.dto.AlertDTO;
import com.example.Issuing_Alert_Levels.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

// 메인 프로그램
@Component
public class MainSystem implements CommandLineRunner {

    @Autowired
    private WebController webSocketController;

    @Autowired
    private AlertService alertService; // AlertService를 주입합니다.
    //실제 동작 코드
    @Override
//    public void run(String... args) throws Exception {
//        LocalDateTime dateTime = LocalDateTime.now();
//        while (true) {
//            System.out.println(dateTime);
//            //테스트 코드
//            List<AlertDTO> alerts = alertService.generateAndSaveAlerts(dateTime);
//            // 웹 소켓을 통해 서버에 접속 중인 모든 클라이언트에게 알림 메시지를 브로드캐스트합니다.
//            webSocketController.broadcastMessage(buildMessage(alerts,dateTime));
//            // 1시간(3600000ms) 동안 대기합니다. 1시간 마다 미세먼지 농도 확인 후 알림
//            Thread.sleep(3600000);
//        }
//    }

    //테스트 코드 : 2023-03-14-00시를 기준으로  3초에 하루 씩 일 수를 증가시키면서 미세먼지 경보를 발령함. 3월 19일에 미세먼지가 안 좋았다.
    public void run(String... args) throws Exception {
        LocalDateTime dateTime = LocalDateTime.of(2023, 3, 14, 0, 0, 0);

        while (true) {
            System.out.println(dateTime);
            //조건에 따른 AirQuality행 조회 이후, 해당 행들을 Alert테이블에 저장 and AlertDTO로 변환 한 이후 MainSystem에 리턴
            List<AlertDTO> alerts = alertService.generateAndSaveAlerts(dateTime);
            // 웹 소켓을 통해 서버에 접속 중인 모든 클라이언트에게 알림 메시지를 브로드캐스트합니다.
            webSocketController.broadcastMessage(buildMessage(alerts,dateTime));
            // 1시간(3600000ms) 동안 대기합니다. 1시간 마다 미세먼지 농도 확인 후 알림
            System.out.println(alerts);
            Thread.sleep(3000);
            dateTime = dateTime.plusDays(1);
            if (dateTime.getMonthValue() == 4) {
                System.out.println("3월 데이터까지가 끝입니다.");
                break;
            }

        }
    }

    private String buildMessage(List<AlertDTO> alerts, LocalDateTime dateTime) {
        StringBuilder messageBuilder = new StringBuilder();
        for (AlertDTO alert : alerts) {
            messageBuilder.append(alert.getStation().getStationName())
                    .append(" : ")
                    .append(alert.getAlertLevel())
                    .append(", ");
        }

        // 마지막 콤마 제거
        if (messageBuilder.length() > 0) {
            messageBuilder.setLength(messageBuilder.length() - 2);
        }

        // 메시지에 날짜 추가
        messageBuilder.append(", Time: ").append(dateTime);

        return messageBuilder.toString();
    }
}