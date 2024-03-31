package com.example.Issuing_Alert_Levels.controller;

import com.example.Issuing_Alert_Levels.dto.MessageDTO;
import com.example.Issuing_Alert_Levels.service.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class WebController {

    private final SimpMessagingTemplate messagingTemplate;

    public WebController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    // /all/message를 구독하고있는 모든 클라이언트에게 브로드캐스트로 문자 전송
    public void broadcastMessage(String message) {
        System.out.println("broadcastMessage");
        MessageDTO text = new MessageDTO(message);
        String jsonMessage = JsonUtil.convertObjectToJson(text);
        System.out.println(jsonMessage);
        messagingTemplate.convertAndSend("/all/message", jsonMessage);
    }
    @GetMapping("/")// 클라이언트 페이지
        public String home(Model model) {
        model.addAttribute("socketStatus", "연결 대기중");
        return "index";  // Thymeleaf 템플릿 'index'를 반환합니다.
    }



    // 메시지를 모든 사용자에게 보내는 메서드
    @MessageMapping("/application")
    @SendTo("/all/message")
    public Message send(final Message message) throws Exception {
        // 여기서 메시지를 처리하거나 변형할 수 있습니다.
        return message;
    }


}

