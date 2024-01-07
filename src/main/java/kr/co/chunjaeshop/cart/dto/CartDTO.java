package kr.co.chunjaeshop.cart.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    private Integer cartIdx;
    private Integer customerIdx;
    private Integer sellerIdx;

    private List<CartDetailDTO> cartDetailDTOList;
}
