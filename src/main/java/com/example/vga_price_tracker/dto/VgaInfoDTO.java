package com.example.vga_price_tracker.dto;

import lombok.Getter;

@Getter
public class VgaInfoDTO {
    private final String vgaName;
    private final Float vgaPrice;
    private final Integer category; // CAT 필드를 category로 명명
    private final Integer value;    // value 필드 추가

    public VgaInfoDTO(String vgaName, Float vgaPrice, Integer category, Integer value) {
        this.vgaName = vgaName;
        this.vgaPrice = vgaPrice;
        this.category = category;
        this.value = value;
    }
}
