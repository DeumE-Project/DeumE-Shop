package kr.co.chunjaeshop.product.service;

import kr.co.chunjaeshop.product.dto.ProductDTO;


public interface ProductService {


    // 남원우


    // 최경락
    int productSave(ProductDTO productDTO);
    ProductDTO findByProductIdx(Integer sellerIdx, Integer productIdx);

    // 이무현


    // 유지호
    int countMyProductCnt(Integer sellerIdx);



    // 변재혁

}
