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
    private final ProductMapper productMapper;

    @Override
    public int productSave(ProductDTO productDTO) {
        return productMapper.productSave();
    }

    // 남원우


    // 최경락




    // 이무현


    // 유지호


    // 변재혁

}
