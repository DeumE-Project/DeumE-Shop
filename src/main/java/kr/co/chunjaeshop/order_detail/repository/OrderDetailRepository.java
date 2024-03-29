package kr.co.chunjaeshop.order_detail.repository;

import kr.co.chunjaeshop.order_detail.dto.OrderDetailDTO;

public interface OrderDetailRepository {

    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁
    int insertNewOrderDetail(OrderDetailDTO orderDetailDTO);

    int checkIfCustomerHasOrderDetailIdx(Integer customerIdx, Integer orderDetailIdx);

    int alreadyReviewed(Integer orderDetailIdx);

    Integer getProductIdxByOrderDetailIdx(Integer orderDetailIdx);

    void changeReviewedStatusTo1(Integer orderDetailIdx);
}
