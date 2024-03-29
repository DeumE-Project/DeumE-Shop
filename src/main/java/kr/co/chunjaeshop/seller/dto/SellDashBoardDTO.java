package kr.co.chunjaeshop.seller.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellDashBoardDTO {
    private String salesDate; // 판매날짜
    private Integer sales; // 매출
    private String category; // 카테고리
    private Integer categorySales; // 카테고리별 판매액
    private  Integer sellCount; // 판매량
    private String productName; // 상품명
    private Integer sellRev; // 누적금액
}
