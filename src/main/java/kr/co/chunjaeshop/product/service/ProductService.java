package kr.co.chunjaeshop.product.service;

import kr.co.chunjaeshop.customer.dto.AddToCartForm;
import kr.co.chunjaeshop.customer.dto.CartResult;
import kr.co.chunjaeshop.product.dto.ProductDTO;

public interface ProductService {

    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁
    ProductDTO getProductInformationByProductIdx(Integer productIdx);
    int getProductPrice(Integer productIdx);
}
