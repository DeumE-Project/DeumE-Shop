package kr.co.chunjaeshop.product.service;

import kr.co.chunjaeshop.product.dto.ProductDTO;


public interface ProductService {


    // 남원우


    // 최경락
    int productSave(ProductDTO productDTO);
    ProductDTO findByProductIdx(Integer sellerIdx, Integer productIdx);
    ProductDTO findByProductIdx2(Integer sellerIdx, Integer productIdx);

    //ProductDTO productInfoUpdate(Integer sellerIdx, Integer productIdx);
    boolean productInfoUpdate(ProductDTO productDTO);

    // 이무현


    // 유지호
    int countMyProductCnt(Integer sellerIdx);




    // 변재혁

}
