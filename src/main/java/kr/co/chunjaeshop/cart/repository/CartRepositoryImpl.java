package kr.co.chunjaeshop.cart.repository;

import kr.co.chunjaeshop.cart.dto.AddToCartForm;
import kr.co.chunjaeshop.cart.dto.CartDTO;
import kr.co.chunjaeshop.cart.dto.ChangeCartDetailDTO;
import kr.co.mapper_interface.cart.CartMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<CartDTO> getAllMyCartList(Integer customerIdx) {
        return cartMapper.getAllMyCartList(customerIdx);
    }

    @Override
    public int changeCartDetailBuyCount(ChangeCartDetailDTO changeCartDetailDTO) {
        return cartMapper.changeCartDetailBuyCount(changeCartDetailDTO);
    }

    @Override
    public CartDTO getSpecificCart(Integer customerIdx, Integer cartIdx) {
        return cartMapper.getSpecificCart(customerIdx, cartIdx);
    }

    @Override
    public void deleteCart(Integer cartIdx) {
        cartMapper.deleteCart(cartIdx);
    }

    @Override
    public void deleteCartDetail(Integer cartIdx) {
        cartMapper.deleteCartDetail(cartIdx);
    }
}
