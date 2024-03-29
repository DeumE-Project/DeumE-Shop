package kr.co.chunjaeshop.order_detail.service;

import kr.co.chunjaeshop.cart.dto.CartDetailDTO;

import java.util.List;

public interface OrderDetailService {

    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁
    boolean insertNewOrderDetails(Integer orderIdx, List<CartDetailDTO> cartDetailDTOList);

    int checkIfCustomerHasOrderDetailIdx(Integer customerIdx, Integer orderDetailIdx);

    boolean alreadyReviewed(Integer orderDetailIdx);

    Integer getProductIdxByOrderDetailIdx(Integer orderDetailIdx);

    void changeReviewedStatusTo1(Integer orderDetailIdx);
}
