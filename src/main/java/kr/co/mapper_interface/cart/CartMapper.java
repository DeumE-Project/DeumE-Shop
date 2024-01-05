package kr.co.mapper_interface.cart;

import kr.co.chunjaeshop.cart.dto.CartDTO;

public interface CartMapper {
    CartDTO getCartInformation(Integer customerIdx);
}
