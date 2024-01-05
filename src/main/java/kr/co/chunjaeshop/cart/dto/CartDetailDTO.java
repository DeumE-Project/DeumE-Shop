package kr.co.chunjaeshop.cart.dto;

import lombok.Data;

@Data
public class CartDetailDTO {
    private Integer cartDetailIdx;
    private Integer cartIdx;
    private Integer productIdx;
    private Integer productCount;
    private Integer productPrice;
}
