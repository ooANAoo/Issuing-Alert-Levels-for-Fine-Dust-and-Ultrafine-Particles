package com.example.Issuing_Alert_Levels.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DailyVgaPriceAverageDTO {
    private final float vgaPriceAverage;
    private final String date;
}
