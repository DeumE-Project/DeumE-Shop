package kr.co.chunjaeshop.product.repository;

import kr.co.chunjaeshop.product.dto.ProductDTO;
import kr.co.chunjaeshop.seller.dto.SellerDTO;

import java.util.List;

public interface ProductRepository {

    // 남원우


    // 최경락


    // 이무현


    // 유지호
    int countMyProductCnt(Integer sellerIdx);

    List<ProductDTO> myProduct(Integer sellerIdx);


    // 변재혁

}
