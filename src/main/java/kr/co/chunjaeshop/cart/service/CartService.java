package kr.co.chunjaeshop.cart.service;

import kr.co.chunjaeshop.cart.dto.AddToCartForm;
import kr.co.chunjaeshop.cart.dto.CartDTO;
import kr.co.chunjaeshop.cart.dto.CartResult;

import java.util.List;

public interface CartService {
    CartResult addToCart(AddToCartForm addToCartForm);
    List<CartDTO> getAllMyCartList(Integer customerIdx);
}
