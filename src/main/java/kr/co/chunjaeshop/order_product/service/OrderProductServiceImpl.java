package kr.co.chunjaeshop.order_product.service;

import kr.co.chunjaeshop.cart.dto.CartDTO;
import kr.co.chunjaeshop.cart.dto.CartDetailDTO;
import kr.co.chunjaeshop.cart.dto.OrderProductForm;
import kr.co.chunjaeshop.order_product.dto.OrderProductDTO;
import kr.co.chunjaeshop.order_product.repository.OrderProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public boolean insertNewOrder(OrderProductForm orderProductForm, CartDTO cart) {
        int orderTotalPrice = 0;
        for (CartDetailDTO cartDetailDTO : cart.getCartDetailDTOList()) {
            orderTotalPrice += (cartDetailDTO.getProductPrice() * cartDetailDTO.getBuyCount());
        }
        orderProductForm.setOrderTotalPrice(orderTotalPrice);

        int result = orderProductRepository.insertNewOrder(orderProductForm);
        return (result == 1) ? true : false;
    }

    @Override
    public OrderProductDTO getOrderProductWithOrderDetails(Integer orderIdx, Integer customerIdx) {
        return orderProductRepository.getOrderProductWithOrderDetails(orderIdx, customerIdx);
    }

    @Override
    public List<OrderProductDTO> selectOrderProductHistoryList(Integer customerIdx) {
        return orderProductRepository.selectOrderProductHistoryList(customerIdx);
    }
}
