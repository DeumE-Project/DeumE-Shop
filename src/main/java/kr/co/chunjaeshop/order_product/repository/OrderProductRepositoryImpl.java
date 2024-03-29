package kr.co.chunjaeshop.order_product.repository;

import kr.co.chunjaeshop.cart.dto.OrderProductForm;
import kr.co.mapper_interface.order_product.OrderProductMapper;
import kr.co.chunjaeshop.order_product.dto.OrderProductDTO;
import kr.co.mapper_interface.order_product.OrderProductMapper;
import kr.co.mapper_interface.product.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Log4j2
public class OrderProductRepositoryImpl implements OrderProductRepository {
    private final OrderProductMapper orderProductMapper;

    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 주문된 상품의 리스트
    @Override
    public List<OrderProductDTO> sellProductManagePaging(Map<String, Object> managePagingParams) {
        return orderProductMapper.sellProductManagePaging(managePagingParams);
    }

    // 주문 상품 카운트
    @Override
    public int orderProductCount(Integer productIdx) {
        return orderProductMapper.orderProductCount(productIdx);
    }

    // 주문된 검색 상품 카운트
    @Override
    public int orderSearchProductCount(Integer productIdx, String searchField, String searchWord) {
        Map<String, Object> params = new HashMap<>();
        params.put("productIdx", productIdx);
        params.put("searchField", searchField);
        params.put("searchWord", searchWord);
        log.info("wwwww"+params);
        return orderProductMapper.orderSearchProductCount(params);
    }

    // 배송상태 업데이트
    @Override
    public void updateStatus(Map<String, Object> updateParams) {
        orderProductMapper.updateStatus(updateParams);}


    // 변재혁
    @Override
    public int insertNewOrder(OrderProductForm orderProductForm) {
        return orderProductMapper.insertNewOrder(orderProductForm);
    }

    @Override
    public OrderProductDTO getOrderProductWithOrderDetails(Integer orderIdx, Integer customerIdx) {
        return orderProductMapper.getOrderProductWithOrderDetails(orderIdx, customerIdx);
    }

    @Override
    public List<OrderProductDTO> selectOrderProductHistoryList(Integer customerIdx) {
        return orderProductMapper.selectOrderProductHistoryList(customerIdx);
    }
}
