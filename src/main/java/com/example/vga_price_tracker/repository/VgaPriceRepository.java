//package com.example.vga_price_tracker.repository;
//
//import com.example.vga_price_tracker.domain.air_quality;
//import com.example.vga_price_tracker.domain.VgaPrice;
//import com.example.vga_price_tracker.dto.VgaPriceDTO;
//import com.example.vga_price_tracker.dto.VgaPricePerformanceScoreDTO;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.time.LocalDate;
//import java.util.List;
//
//public interface VgaPriceRepository extends JpaRepository<VgaPrice, Long> {
//    /**
//     *
//     * @return 특정 기간 사이(startDate, endDate)의 그래픽카드 가격 리스트
//     */
//    List<VgaPrice> findByVgaAndCreatedAtBetween(air_quality vga, LocalDate startDate, LocalDate endDate);
//
//    List<VgaPrice> findByCreatedAt(LocalDate today);
//
//    @Query(value =
//            "SELECT new com.example.vga_price_tracker.dto.VgaPricePerformanceScoreDTO(" +
//                    "vp.id, vp.vga.vgaName, vp.vgaPrice, ROUND(vp.vga.vgaScore / vp.vgaPrice, 2), vp.vga.vgaCategory) " +
//            "FROM VgaPrice vp " +
//            "WHERE (vp.vga, vp.createdAt) IN (SELECT vp.vga, max(vp.createdAt) FROM VgaPrice vp GROUP BY vp.vga)" +
//            "ORDER BY ROUND(vp.vga.vgaScore / vp.vgaPrice, 2) DESC"
//    )
//    List<VgaPricePerformanceScoreDTO> getPricePerformanceRanking();
//
//    @Query(value =
//            "SELECT new com.example.vga_price_tracker.dto.VgaPriceDTO('average', ROUND(AVG(vp.vgaPrice), 2), STR(vp.createdAt)) " +
//            "FROM VgaPrice vp " +
//            "WHERE vp.createdAt Between :startDate AND :endDate " +
//            "GROUP BY vp.createdAt"
//    )
//    List<VgaPriceDTO> getDailyVgaPriceAverage(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
//}
