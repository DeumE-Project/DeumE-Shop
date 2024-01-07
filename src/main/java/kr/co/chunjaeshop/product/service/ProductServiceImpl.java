package kr.co.chunjaeshop.product.service;

import kr.co.chunjaeshop.product.dto.ProductDTO;
import kr.co.chunjaeshop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public int productSave(ProductDTO productDTO) {
        return productRepository.productSave(productDTO);
    }


    // 남원우


    // 최경락

    // 이무현


    // 유지호


    // 변재혁

}
