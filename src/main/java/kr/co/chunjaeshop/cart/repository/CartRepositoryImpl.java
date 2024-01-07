package kr.co.chunjaeshop.cart.repository;

import kr.co.chunjaeshop.cart.dto.AddToCartForm;
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
    public CartDTO getCartInformation(Integer customerIdx, Integer sellerIdx) {
        return cartMapper.getCartInformation(customerIdx, sellerIdx);
    }

    @Override
    public int updateCartDetailProductInfo(AddToCartForm parameterMap) {
        return cartMapper.updateCartDetailProductInfo(parameterMap);
    }

    @Override
    public int insertNewCartDetail(AddToCartForm parameterMap) {
        return cartMapper.insertNewCartDetail(parameterMap);
    }

    @Override
    public int insertNewCart(AddToCartForm parameterMap) {
        return cartMapper.insertNewCart(parameterMap);
    }
}
