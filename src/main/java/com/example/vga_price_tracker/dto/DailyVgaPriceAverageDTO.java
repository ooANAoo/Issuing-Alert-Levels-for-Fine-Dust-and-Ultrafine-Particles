package com.example.vga_price_tracker.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DailyVgaPriceAverageDTO {
    private final float vgaPriceAverage;
    private final String date;
}
