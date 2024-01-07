package kr.co.chunjaeshop.cart.repository;

import kr.co.chunjaeshop.cart.dto.CartDTO;

import java.util.Map;

public interface CartRepository {
    CartDTO getCartInformation(Integer customerIdx, Integer sellerIdx);

    int updateCartDetailProductInfo(Map<String, Object> parameterMap);

    int insertNewCartDetail(Map<String, Object> parameterMap);

    int insertNewCart(Map<String, Object> parameterMap);
}
