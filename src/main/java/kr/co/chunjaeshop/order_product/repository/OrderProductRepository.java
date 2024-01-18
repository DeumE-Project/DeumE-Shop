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

    // 주문된 상품 리스트
    List<OrderProductDTO> sellProductManagePaging(Map<String, Object> managePagingParams);

    // 주문된 상품 카운트
    int orderProductCount(Integer productIdx);

    // 주문된 검색 상품 카운트
    int orderSearchProductCount(Integer productIdx, String searchField, String searchWord);

    // 배송 상태 업데이트
    void updateStatus(Map<String, Object> updateParams);

    // 변재혁
    int insertNewOrder(OrderProductForm orderProductForm);

    OrderProductDTO getOrderProductWithOrderDetails(Integer orderIdx, Integer customerIdx);

    List<OrderProductDTO> selectOrderProductHistoryList(Integer customerIdx);
}
