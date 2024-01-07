package kr.co.mapper_interface.cart;

import kr.co.chunjaeshop.cart.dto.CartDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface CartMapper {
    CartDTO getCartInformation(@Param("customerIdx") Integer customerIdx, @Param("sellerIdx") Integer sellerIdx);

    int updateCartDetailProductInfo(Map<String, Object> parameterMap);

    int insertNewCartDetail(Map<String, Object> parameterMap);

    int insertNewCart(Map<String, Object> parameterMap);
}
