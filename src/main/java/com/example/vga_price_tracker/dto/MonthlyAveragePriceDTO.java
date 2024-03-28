package com.example.vga_price_tracker.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MonthlyAveragePriceDTO {   // 매달 평균 가격 DTO
    private final int year;
    private final int month;
    private final int vgaName;
    private final int vgaPrice;
}
