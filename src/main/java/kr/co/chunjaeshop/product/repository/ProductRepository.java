package kr.co.chunjaeshop.product.repository;

import kr.co.chunjaeshop.product.dto.ProductDTO;
import kr.co.chunjaeshop.seller.dto.SellerDTO;

import java.util.List;
import java.util.Map;

public interface ProductRepository {

    // 남원우


    // 최경락


    // 이무현


    // 유지호
    int countMyProductCnt(Integer sellerIdx);

    List<ProductDTO> myProduct(Integer sellerIdx);

    List<ProductDTO> productPagingList(Map<String, Integer> pagingParams);

    int productCount();


    // 변재혁

}
