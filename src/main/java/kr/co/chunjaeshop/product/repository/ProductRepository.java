package kr.co.chunjaeshop.product.repository;

import kr.co.chunjaeshop.product.dto.ProductDTO;

import java.util.List;


public interface ProductRepository {



    // 남원우


    // 최경락
    int productSave(ProductDTO productDTO);


    // 이무현


    // 유지호
    int countMyProductCnt(Integer sellerIdx);

    List<ProductDTO> myProduct(Integer sellerIdx);


    // 변재혁

}
