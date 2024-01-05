package kr.co.chunjaeshop.product.repository;

import kr.co.chunjaeshop.product.dto.ProductDTO;
import kr.co.mapper_interface.product.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Log4j2
public class ProductRepositoryImpl implements ProductRepository {

    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁
    private final ProductMapper pMap;
    @Override
    public ProductDTO getProductInformationByProductIdx(Integer productIdx) {
        return pMap.getProductInformationByProductIdx(productIdx);
    }

    @Override
    public int getProductPrice(Integer productIdx) {
        return pMap.getProductPrice(productIdx);
    }
}
