package kr.co.chunjaeshop.product.repository;

import kr.co.chunjaeshop.product.dto.ProductDTO;

import java.util.List;
import java.util.Map;


public interface ProductRepository {



    // 남원우


    // 최경락
    int productSave(ProductDTO productDTO);
    ProductDTO findByProductIdx(Integer sellerIdx,Integer productIdx);


    // 이무현


    // 유지호
    int countMyProductCnt(Integer sellerIdx);

    List<ProductDTO> myProduct(Integer sellerIdx);

    List<ProductDTO> productPagingList(Map<String, Integer> pagingParams);

    int productCount(Integer sellerIdx);

    List<ProductDTO> productPagingListWithSearch(Map<String, Object> pagingParams);

    int searchproductCount(Integer sellerIdx, String searchField, String searchWord);

    /*List<ProductDTO> sellProductManage(Integer sellerIdx, Integer productIdx);*/


    // 변재혁

}