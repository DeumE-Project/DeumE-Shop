package kr.co.chunjaeshop.product.repository;

import kr.co.chunjaeshop.product.dto.ProductDTO;

public interface ProductRepository {

    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁
    ProductDTO getProductInformationByProductIdx(Integer productIdx);
    int getProductPrice(Integer productIdx);
}
