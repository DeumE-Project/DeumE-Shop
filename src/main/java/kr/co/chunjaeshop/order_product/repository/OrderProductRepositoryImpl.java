package kr.co.chunjaeshop.order_product.repository;

import kr.co.chunjaeshop.order_product.dto.OrderProductDTO;
import kr.co.mapper_interface.order_product.OrderProductMapper;
import kr.co.mapper_interface.product.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

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


    @Override
    public List<OrderProductDTO> sellProductManagePaging(Map<String, Object> managePagingParams) {
        return orderProductMapper.sellProductManagePaging(managePagingParams);
    }

/*
    @Override
    public List<OrderProductDTO> sellProductManage(Integer sellerIdx, Integer productIdx) {
        return orderProductMapper.sellProductManage(sellerIdx, productIdx);
    }
*/


    // 변재혁

}
