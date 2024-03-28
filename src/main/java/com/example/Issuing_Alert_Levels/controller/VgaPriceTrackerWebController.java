//package com.example.vga_price_tracker.controller;
//
//import com.example.vga_price_tracker.dto.*;
//import com.example.vga_price_tracker.service.VgaPriceTrackerService;
//import com.example.vga_price_tracker.service.VgaRankingService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RequiredArgsConstructor
//@Controller
//public class VgaPriceTrackerWebController {
//    private final VgaPriceTrackerService vgaPriceTrackerService;
//    private final VgaRankingService vgaRankingService;
//    private static final int ENTIRE_VGA_ID = -1;
//
//    // 차트 페이지
//    @GetMapping("")
//    public String getMain(@RequestParam(required = false, defaultValue = "-1") long vgaId, Model model) {
//        // 그래픽카드 이름 목록을 가져옴.
//        List<VgaNameDTO> vgaNames = vgaPriceTrackerService.getVgaNames();
//        List<VgaPriceDTO> vgaPricesForWeek;
//        List<VgaPriceDTO> vgaPricesForMonth;
//        List<VgaPriceDTO> vgaPricesForYear;
//
//        if(vgaId == ENTIRE_VGA_ID) {
//            vgaPricesForWeek = vgaPriceTrackerService.getVgaPriceAverageForWeek();
//            vgaPricesForMonth = vgaPriceTrackerService.getVgaPriceAverageForMonth();
//            vgaPricesForYear = vgaPriceTrackerService.getVgaPriceAverageForYear();
//        } else {
//            // 선택된 그래픽카드의 일주일 사이의 가격 데이터를 가져옴.
//            vgaPricesForWeek = vgaPriceTrackerService.getVgaPricesForWeek(vgaId);
//            // 선택된 그래픽카드의 한달 사이의 가격 데이터를 가져옴.
//            vgaPricesForMonth = vgaPriceTrackerService.getVgaPricesForMonth(vgaId);
//            vgaPricesForYear = vgaPriceTrackerService.getVgaPricesForYear(vgaId);
//        }
//
//        List<VgaInfoDTO> vgaInfos = vgaPriceTrackerService.getVgaInfos();
//        // 나머지 로직
//        // (data.value / data.vgaPrice) 기준으로 정렬
//        vgaInfos.sort(Comparator.comparingDouble(data -> data.getValue() / data.getVgaPrice()));
//        Collections.reverse(vgaInfos);
//
//// 가장 높은 (data.value / data.vgaPrice) 값을 찾음
//        double maxRatio = vgaInfos.stream()
//                .mapToDouble(data -> data.getValue() / data.getVgaPrice())
//                .max()
//                .orElse(1); // 0으로 나누는 것을 방지하기 위해 기본값 설정
//
//
//        // 모델에 데이터를 추가.
//        model.addAttribute("vgaNames", vgaNames);
//        // 모델에 정렬된 데이터와 최대 비율을 추가
//        model.addAttribute("videoCardData", vgaInfos);
//        model.addAttribute("maxRatio", maxRatio);
//        model.addAttribute("vgaPricesForWeek", vgaPricesForWeek);
//        model.addAttribute("vgaPricesForMonth", vgaPricesForMonth);
//        model.addAttribute("vgaPricesForYear", vgaPricesForYear);
//        // "main.html" 템플릿을 반환.
//        return "main.html";
//    }
//
//    private boolean isValidType(String type) {
//        // type 값이 유효한지 확인하는 로직
//
//        return "expectedType1".equals(type) || "expectedType2".equals(type); // 예시
//    }
//
//    @GetMapping("/ranking")
//    public String getRanking(Model model) {
//        List<VgaPricePerformanceScoreDTO> rankingDTO = vgaRankingService.getPricePerformanceRanking();
//
//        model.addAttribute("ranking", rankingDTO);
//
//        // maxScore 계산
//        float maxScore = rankingDTO.stream()
//                .map(VgaPricePerformanceScoreDTO::getScore)
//                .max(Float::compare)
//                .orElse(1.0f); // 기본값 설정
//
//        model.addAttribute("maxScore", maxScore);
//
//        return "ranking.html";
//    }
//}
