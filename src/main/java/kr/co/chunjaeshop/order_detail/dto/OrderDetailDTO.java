package kr.co.chunjaeshop.order_detail.dto;

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

}
