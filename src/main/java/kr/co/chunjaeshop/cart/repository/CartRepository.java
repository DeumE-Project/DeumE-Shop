package kr.co.chunjaeshop.cart.repository;

import kr.co.chunjaeshop.cart.dto.AddToCartForm;
import kr.co.chunjaeshop.cart.dto.CartDTO;
import kr.co.chunjaeshop.cart.dto.ChangeCartDetailDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartRepository {
    CartDTO getCartInformation(Integer customerIdx, Integer sellerIdx);

    int updateCartDetailProductInfo(AddToCartForm parameterMap);

    int insertNewCartDetail(AddToCartForm parameterMap);

    int insertNewCart(AddToCartForm parameterMap);

    List<CartDTO> getAllMyCartList(Integer customerIdx);

    int changeCartDetailBuyCount(ChangeCartDetailDTO changeCartDetailDTO);

    CartDTO getSpecificCart(Integer customerIdx, Integer cartIdx);

    void deleteCart(Integer cartIdx);

    void deleteCartDetail(Integer cartIdx);
}
