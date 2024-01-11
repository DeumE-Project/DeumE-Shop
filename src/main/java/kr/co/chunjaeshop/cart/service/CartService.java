package kr.co.chunjaeshop.cart.service;

import kr.co.chunjaeshop.cart.dto.*;

import java.util.List;

public interface CartService {
    CartResult addToCart(AddToCartForm addToCartForm);
    List<CartDTO> getAllMyCartList(Integer customerIdx);
    CartDetailResult changeCartDetailBuyCount(ChangeCartDetailDTO changeCartDetailDTO);
    CartDTO getSpecificCart(Integer customerIdx, Integer cartIdx);
}
