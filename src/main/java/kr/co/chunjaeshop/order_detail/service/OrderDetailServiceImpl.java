package kr.co.chunjaeshop.order_detail.service;

import kr.co.chunjaeshop.cart.dto.CartDetailDTO;
import kr.co.chunjaeshop.order_detail.dto.OrderDetailDTO;
import kr.co.chunjaeshop.order_detail.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁

    @Override
    public boolean insertNewOrderDetails(Integer orderIdx, List<CartDetailDTO> cartDetailDTOList) {
        for (CartDetailDTO cartDetail : cartDetailDTOList) {
            OrderDetailDTO orderDetailDTO = OrderDetailDTO.builder()
                    .orderIdx(orderIdx)
                    .productIdx(cartDetail.getProductIdx())
                    .productPrice(cartDetail.getProductPrice())
                    .productCount(cartDetail.getBuyCount())
                    .productTotalPrice(cartDetail.getProductPrice() * cartDetail.getBuyCount())
                    .build();
            int result = orderDetailRepository.insertNewOrderDetail(orderDetailDTO);
            if (result != 1) {
                log.error("OrderDetail 저장 오류");
                return false;
            }
        }
        return true;
    }

    @Override
    public int checkIfCustomerHasOrderDetailIdx(Integer customerIdx, Integer orderDetailIdx) {
        return orderDetailRepository.checkIfCustomerHasOrderDetailIdx(customerIdx, orderDetailIdx);
    }

    @Override
    public boolean alreadyReviewed(Integer orderDetailIdx) {
        int result = orderDetailRepository.alreadyReviewed(orderDetailIdx);
        return (result == 1) ? true : false;
    }

    @Override
    public Integer getProductIdxByOrderDetailIdx(Integer orderDetailIdx) {
        return orderDetailRepository.getProductIdxByOrderDetailIdx(orderDetailIdx);
    }

    @Override
    public void changeReviewedStatusTo1(Integer orderDetailIdx) {
        orderDetailRepository.changeReviewedStatusTo1(orderDetailIdx);
    }
}
