package kr.co.mapper_interface.order_product;

import kr.co.chunjaeshop.cart.dto.OrderProductForm;
import kr.co.chunjaeshop.order_product.dto.OrderProductDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderProductMapper {

    // 남원우


    // 최경락


    // 이무현


    // 유지호
    List<OrderProductDTO> sellProductManagePaging(Map<String, Object> managePagingParams);

    int orderProductCount(Integer productIdx);

    int orderSearchProductCount(Map<String, Object> params);

    void updateStatus(Map<String, Object> updateParams);

    // 변재혁
    int insertNewOrder(OrderProductForm orderProductForm);

    OrderProductDTO getOrderProductWithOrderDetails(@Param("orderIdx") Integer orderIdx,
                                                    @Param("customerIdx") Integer customerIdx);

    List<OrderProductDTO> selectOrderProductHistoryList(Integer customerIdx);
}
