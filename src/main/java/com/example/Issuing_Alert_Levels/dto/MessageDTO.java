package com.example.Issuing_Alert_Levels.dto;

//클라이언트에게 알림을 보내기위한 문자 객체
public class MessageDTO {
    private String text;

    public MessageDTO() {
        // 기본 생성자가 필요
    }

    public MessageDTO(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
