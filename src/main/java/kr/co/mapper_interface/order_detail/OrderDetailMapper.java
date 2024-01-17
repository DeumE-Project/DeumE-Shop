package kr.co.mapper_interface.order_detail;

import kr.co.chunjaeshop.order_detail.dto.OrderDetailDTO;
import org.apache.ibatis.annotations.Param;

public interface OrderDetailMapper {
    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁
    int insertNewOrderDetail(OrderDetailDTO orderDetailDTO);

    int checkIfCustomerHasOrderDetailIdx(@Param("customerIdx") Integer customerIdx,
                                         @Param("orderDetailIdx") Integer orderDetailIdx);

    int alreadyReviewed(Integer orderDetailIdx);

    int getProductIdxByOrderDetailIdx(Integer orderDetailIdx);

    void changeReviewedStatusTo1(Integer orderDetailIdx);
}
