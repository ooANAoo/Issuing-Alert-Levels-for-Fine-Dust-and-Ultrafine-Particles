package com.example.Issuing_Alert_Levels.dto;

import lombok.Getter;
import lombok.Setter;

//클라이언트에게 알림을 보내기위한 문자 객체
@Getter
@Setter
public class MessageDTO {
    private String text;

    public MessageDTO() {
        // 기본 생성자가 필요
    }

    public MessageDTO(String text) {
        this.text = text;
    }

}
