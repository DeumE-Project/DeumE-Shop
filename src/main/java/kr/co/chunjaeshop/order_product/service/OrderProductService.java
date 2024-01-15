package kr.co.chunjaeshop.order_product.service;

import kr.co.chunjaeshop.cart.dto.CartDTO;
import kr.co.chunjaeshop.cart.dto.OrderProductForm;
import kr.co.chunjaeshop.order_product.dto.OrderProductDTO;

import java.util.List;

public interface OrderProductService {

    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁
    boolean insertNewOrder(OrderProductForm orderProductForm, CartDTO cartDTO);

    OrderProductDTO getOrderProductWithOrderDetails(Integer orderIdx, Integer customerIdx);

    List<OrderProductDTO> selectOrderProductHistoryList(Integer customerIdx);
}
