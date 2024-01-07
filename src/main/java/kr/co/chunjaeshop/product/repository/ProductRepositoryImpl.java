package kr.co.chunjaeshop.product.repository;

import kr.co.chunjaeshop.product.dto.ProductDTO;
import kr.co.mapper_interface.product.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;;

@Repository
@RequiredArgsConstructor
@Log4j2
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductMapper productMapper;
    // 남원우


    // 최경락


    // 이무현


    // 유지호

    @Override
    public int countMyProductCnt(Integer sellerIdx) {
        return productMapper.countMyProductCnt(sellerIdx);
    }

    @Override
    public List<ProductDTO> myProduct(Integer sellerIdx) {
        return productMapper.myProduct(sellerIdx);
    }
    // 변재혁

}

