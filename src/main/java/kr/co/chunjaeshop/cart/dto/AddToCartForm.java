package kr.co.chunjaeshop.cart.dto;

import lombok.Data;

@Data
public class AddToCartForm {
    private Integer productIdx;
    private Integer buyCount;

    private Integer customerIdx;

    private Integer sellerIdx;
    private Integer productPrice;

    private Integer cartIdx;
}
