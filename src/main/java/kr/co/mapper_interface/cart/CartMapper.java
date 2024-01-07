package kr.co.mapper_interface.cart;

import kr.co.chunjaeshop.cart.dto.CartDTO;

import java.util.Map;

public interface CartMapper {
    CartDTO getCartInformation(Integer customerIdx);

    void updateCartDetailProductInfo(Map<String, Object> parameterMap);
}
