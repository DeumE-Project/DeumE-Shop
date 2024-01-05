package kr.co.chunjaeshop.cart.repository;

import kr.co.chunjaeshop.cart.dto.CartDTO;

public interface CartRepository {
    CartDTO getCartInformation(Integer customerIdx);
}
