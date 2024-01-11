package kr.co.chunjaeshop.order_product.repository;

import kr.co.chunjaeshop.cart.dto.OrderProductForm;
import kr.co.mapper_interface.order_product.OrderProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Log4j2
public class OrderProductRepositoryImpl implements OrderProductRepository {
    private final OrderProductMapper orderProductMapper;

    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁
    @Override
    public int insertNewOrder(OrderProductForm orderProductForm) {
        return orderProductMapper.insertNewOrder(orderProductForm);
    }
}
