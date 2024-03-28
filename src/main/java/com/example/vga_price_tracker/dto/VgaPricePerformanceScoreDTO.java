package com.example.vga_price_tracker.dto;

import com.example.vga_price_tracker.domain.Alert;
import lombok.Getter;

@Getter
public class VgaPricePerformanceScoreDTO {
    private final long id;
    private final String vgaName;
    private final float score;
    private final float vgaPrice;
    private final Alert vgaCategory;

    public VgaPricePerformanceScoreDTO(long id, String vgaName, float vgaPrice, float score, Alert vgaCategory) {
        this.id = id;
        this.vgaName = vgaName;
        this.vgaPrice = vgaPrice;
        this.score = score;
        this.vgaCategory = vgaCategory;
    }
}
