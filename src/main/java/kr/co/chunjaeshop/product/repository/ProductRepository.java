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
    int productInfoUpdate(ProductDTO productDTO);
    ProductMainImgUpdateDTO findMainImg(Integer sellerIdx, Integer productIdx);
    int productImgUpdate(ProductMainImgUpdateDTO productMainImgUpdateDTO);
    ProductDetailImgUpdateDTO findDetailImg(Integer sellerIdx, Integer productIdx);
    int productDetailImgUpdate(ProductDetailImgUpdateDTO productDetailImgUpdateDTO);
    List<ProductDTO> productListPagingWithSearch(Map<String, Object> pagingParams);
    int productListCount(Integer categoryIdx);
    int searchProductListCount(Integer categoryIdx, String searchField, String searchWord);



    // 이무현


    // 유지호
    int countMyProductCnt(Integer sellerIdx);


    int productCount(Integer sellerIdx);

    List<ProductDTO> productPagingListWithSearch(Map<String, Object> pagingParams);

    int searchproductCount(Integer sellerIdx, String searchField, String searchWord);


    // 변재혁
    ProductDTO getProductInformationByProductIdx(Integer productIdx);
    int getProductPrice(Integer productIdx);

    List<ProductDTO> getProductListForMainPage();

    int checkIfProductBelongsToSeller(Integer sellerIdx, Integer productIdx);

}
