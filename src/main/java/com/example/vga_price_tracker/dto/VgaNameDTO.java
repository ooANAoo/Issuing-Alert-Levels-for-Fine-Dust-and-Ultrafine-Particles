package com.example.vga_price_tracker.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class VgaNameDTO {   // 그래픽카드 종류 DTO
    private final long id;
    private final String vgaName;
}
