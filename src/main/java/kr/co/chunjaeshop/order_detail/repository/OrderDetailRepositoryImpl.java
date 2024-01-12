package kr.co.chunjaeshop.order_detail.repository;

import kr.co.chunjaeshop.order_detail.dto.OrderDetailDTO;
import kr.co.mapper_interface.order_detail.OrderDetailMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Log4j2
public class OrderDetailRepositoryImpl implements OrderDetailRepository {
    private final OrderDetailMapper orderDetailMapper;

    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁

    @Override
    public int insertNewOrderDetail(OrderDetailDTO orderDetailDTO) {
        return orderDetailMapper.insertNewOrderDetail(orderDetailDTO);
    }
}
