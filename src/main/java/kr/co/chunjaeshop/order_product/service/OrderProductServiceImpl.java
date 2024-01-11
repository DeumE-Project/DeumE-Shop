package kr.co.chunjaeshop.order_product.service;

import kr.co.chunjaeshop.cart.dto.OrderProductForm;
import kr.co.chunjaeshop.order_product.repository.OrderProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderProductServiceImpl implements OrderProductService {
    private final OrderProductRepository orderProductRepository;

    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁

    @Override
    public boolean insertNewOrder(OrderProductForm orderProductForm) {
        int result = orderProductRepository.insertNewOrder(orderProductForm);
        return (result == 1) ? true : false;
    }
}
