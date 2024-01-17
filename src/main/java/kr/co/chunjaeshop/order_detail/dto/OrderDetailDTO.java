package kr.co.chunjaeshop.order_detail.dto;

import kr.co.chunjaeshop.product.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailDTO {
    private Integer orderDetailIdx;
    private Integer orderIdx;
    private Integer productIdx;
    private Integer productPrice;
    private Integer productCount;
    private Integer productTotalPrice;

    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁
    private ProductDTO productDTO;
    private Integer reviewed; // 0은 리뷰 아직 안 함, 1은 이미 리뷰 함
}
