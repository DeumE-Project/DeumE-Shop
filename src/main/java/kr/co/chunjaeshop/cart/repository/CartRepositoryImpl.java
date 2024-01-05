package kr.co.chunjaeshop.cart.repository;

import kr.co.chunjaeshop.cart.dto.CartDTO;
import kr.co.mapper_interface.cart.CartMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Log4j2
public class CartRepositoryImpl implements CartRepository {
    private final CartMapper cartMapper;

    @Override
    public CartDTO getCartInformation(Integer customerIdx) {
        return cartMapper.getCartInformation(customerIdx);
    }
}
