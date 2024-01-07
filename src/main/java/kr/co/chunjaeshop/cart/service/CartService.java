package kr.co.chunjaeshop.cart.service;

import kr.co.chunjaeshop.cart.dto.AddToCartForm;
import kr.co.chunjaeshop.cart.dto.CartResult;

public interface CartService {
    CartResult addToCart(AddToCartForm addToCartForm);
}
