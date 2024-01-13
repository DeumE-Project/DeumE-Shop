package kr.co.chunjaeshop.product.repository;

import kr.co.chunjaeshop.product.dto.ProductDTO;
import kr.co.chunjaeshop.product.dto.ProductDetailImgUpdateDTO;
import kr.co.chunjaeshop.product.dto.ProductMainImgUpdateDTO;

import java.util.List;
import java.util.Map;


public interface ProductRepository {

    // 남원우


    // 최경락
    int productSave(ProductDTO productDTO);
    ProductDTO findByProductIdx(Integer sellerIdx,Integer productIdx);
    //ProductDTO productInfoUpdate(Integer sellerIdx, Integer productIdx);
    ProductDTO findByProductIdx2(Integer sellerIdx, Integer productIdx);
    ProductDTO findMainImg(Integer sellerIdx, Integer productIdx);
    int productInfoUpdate(ProductDTO productDTO);
    int productImgUpdate(ProductMainImgUpdateDTO productMainImgUpdateDTO);
    ProductDTO findDetailImg(Integer sellerIdx, Integer productIdx);
    int productDetailImgUpdate(ProductDetailImgUpdateDTO productDetailImgUpdateDTO);



    // 이무현


    // 유지호
    int countMyProductCnt(Integer sellerIdx);

    List<ProductDTO> myProduct(Integer sellerIdx);

/*    List<ProductDTO> productPagingList(Map<String, Integer> pagingParams);*/

    int productCount(Integer sellerIdx);

    List<ProductDTO> productPagingListWithSearch(Map<String, Object> pagingParams);

    int searchproductCount(Integer sellerIdx, String searchField, String searchWord);


    /*List<ProductDTO> sellProductManage(Integer sellerIdx, Integer productIdx);*/


    // 변재혁
    ProductDTO getProductInformationByProductIdx(Integer productIdx);
    int getProductPrice(Integer productIdx);
}
