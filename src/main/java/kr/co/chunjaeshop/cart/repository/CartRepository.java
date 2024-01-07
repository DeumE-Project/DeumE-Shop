package kr.co.chunjaeshop.cart.repository;

import kr.co.chunjaeshop.cart.dto.CartDTO;

import java.util.Map;

public interface CartRepository {
    CartDTO getCartInformation(Integer customerIdx);

    void updateCartDetailProductInfo(Map<String, Object> parameterMap);
}
