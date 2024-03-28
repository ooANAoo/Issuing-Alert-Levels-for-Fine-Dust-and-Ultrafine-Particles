//package com.example.vga_price_tracker.service;
//
//import com.example.vga_price_tracker.domain.air_quality;
//import com.example.vga_price_tracker.domain.VgaPrice;
//import com.example.vga_price_tracker.dto.VgaInfoDTO;
//import com.example.vga_price_tracker.dto.VgaNameDTO;
//import com.example.vga_price_tracker.dto.VgaPriceDTO;
//import com.example.vga_price_tracker.repository.VgaRepository;
//import com.example.vga_price_tracker.repository.VgaPriceRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@RequiredArgsConstructor
//@Service
//public class VgaPriceTrackerService {
//    private final VgaRepository vgaNameRepository;
//    private final VgaPriceRepository vgaPriceRepository;
//
//    /**
//     *
//     * @return 그래픽카드 종류 리스트
//     */
//    public List<VgaNameDTO> getVgaNames() {
//        List<air_quality> vgaNames = vgaNameRepository.findAll();
//
//        return vgaNames.stream()
//                .map(this::convertVgaNameToDTO)
//                .toList();
//    }
//
//    public List<VgaInfoDTO> getVgaInfos(){
//        List<Long> ids = getVgaNames().stream()
//                .map(VgaNameDTO::getId)
//                .toList();
//         return vgaNameRepository.findLatestVgaPricesByIds(ids);
//    }
//
//    /**
//     *
//     * @return 일주일 사이의 그래픽카드 가격 리스트
//     */
//    public List<VgaPriceDTO> getVgaPricesForWeek(long vgaId) {
//        return getVgaPricesForPeriod(vgaId, LocalDate.now().minusWeeks(1));
//    }
//
//    /**
//     *
//     * @return 한달 사이의 그래픽카드 가격 리스트
//     */
//    // 1개월 단위
//    public List<VgaPriceDTO> getVgaPricesForMonth(long vgaId) {
//        return getVgaPricesForPeriod(vgaId, LocalDate.now().minusMonths(1));
//    }
//
//    /**
//     *
//     * @return 한달 사이의 그래픽카드 가격 리스트
//     */
//    // 1년 단위
//    public List<VgaPriceDTO> getVgaPricesForYear(long vgaId) {
//        return getVgaPricesForPeriod(vgaId, LocalDate.now().minusYears(1));
//    }
//
//    /**
//     *
//     * @return 오늘과 특정 기간 사이의 그래픽카드 가격 리스트
//     */
//    private List<VgaPriceDTO> getVgaPricesForPeriod(long vgaId, LocalDate startDate) {
//        air_quality vga = vgaNameRepository.findById(vgaId)
//                .orElseThrow(() -> new IllegalArgumentException("잘못된 그래픽카드 이름"));
//
//        LocalDate endDate = LocalDate.now();    // 오늘
//        List<VgaPrice> vgaPrices = vgaPriceRepository.findByVgaAndCreatedAtBetween(vga, startDate, endDate);
//
//        return vgaPrices.stream()
//                .map(this::convertVgaPriceToDTO)
//                .toList();
//    }
//
//    /**
//     *
//     * @return 일주일 사이의 그래픽카드 평균 가격 리스트
//     */
//    public List<VgaPriceDTO> getVgaPriceAverageForWeek() {
//        return getDailyVgaPriceAveragesForPeriod(LocalDate.now().minusWeeks(1));
//    }
//
//    /**
//     *
//     * @return 한달 사이의 그래픽카드 평균 가격 리스트
//     */
//    // 1개월 단위
//    public List<VgaPriceDTO> getVgaPriceAverageForMonth() {
//        return getDailyVgaPriceAveragesForPeriod(LocalDate.now().minusMonths(1));
//    }
//
//    /**
//     *
//     * @return 한달 사이의 그래픽카드 평균 가격 리스트
//     */
//    // 1년 단위
//    public List<VgaPriceDTO> getVgaPriceAverageForYear() {
//        return getDailyVgaPriceAveragesForPeriod(LocalDate.now().minusYears(1));
//    }
//
//    private List<VgaPriceDTO> getDailyVgaPriceAveragesForPeriod(LocalDate startDate) {
//        LocalDate endDate = LocalDate.now();    // 오늘
//
//        return vgaPriceRepository.getDailyVgaPriceAverage(startDate, endDate);
//    }
//
//
//    // 그래픽카드 가격 데이터를 DTO로 변환
//    private VgaPriceDTO convertVgaPriceToDTO(VgaPrice vgaPrice) {
//        return VgaPriceDTO.builder()
//                .vgaName(vgaPrice.getVga().getVgaName())
//                .vgaPrice(vgaPrice.getVgaPrice())
//                .date(vgaPrice.getCreatedAt().toString())
//                .build();
//    }
//
//    // 그래픽카드 종류 데이터를 DTO로 변환
//    private VgaNameDTO convertVgaNameToDTO(air_quality vgaName) {
//        return new VgaNameDTO(vgaName.getId(), vgaName.getVgaName());
//    }
//}
