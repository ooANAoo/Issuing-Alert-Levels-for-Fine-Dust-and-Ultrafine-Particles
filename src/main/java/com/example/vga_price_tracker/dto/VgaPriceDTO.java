package com.example.vga_price_tracker.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class VgaPriceDTO {   // 그래픽카드 가격 DTO
    private final String vgaName;
    private final double vgaPrice;
    private final String date;

    @Builder
    public VgaPriceDTO(String vgaName, double vgaPrice, String date) {
        this.vgaName = vgaName;
        this.vgaPrice = vgaPrice;
        this.date = date;
    }
}
