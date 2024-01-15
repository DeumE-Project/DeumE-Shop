package kr.co.chunjaeshop.order_product.repository;

import kr.co.chunjaeshop.cart.dto.OrderProductForm;
import kr.co.chunjaeshop.order_product.dto.OrderProductDTO;

import java.util.List;
import java.util.Map;

public interface OrderProductRepository {

    // 남원우


    // 최경락


    // 이무현


    // 유지호
    List<OrderProductDTO> sellProductManagePaging(Map<String, Object> managePagingParams);

    int orderProductCount(Integer productIdx);

    int orderSearchProductCount(Integer productIdx, String searchField, String searchWord);

    // 변재혁
    int insertNewOrder(OrderProductForm orderProductForm);

    OrderProductDTO getOrderProductWithOrderDetails(Integer orderIdx, Integer customerIdx);

    List<OrderProductDTO> selectOrderProductHistoryList(Integer customerIdx);
}
