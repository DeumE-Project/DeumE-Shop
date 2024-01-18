package kr.co.mapper_interface.product;

import kr.co.chunjaeshop.product.dto.ProductDTO;
import kr.co.chunjaeshop.product.dto.ProductDetailImgUpdateDTO;
import kr.co.chunjaeshop.product.dto.ProductMainImgUpdateDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductMapper {

    // 남원우


    // 최경락
    int productSave(ProductDTO productDTO);
    //@Param에 넣어준 값을 정확하게 mapper.xml에서 사용 가능.
    ProductDTO findByProductIdx(@Param("sellerIdx") Integer sellerIdx, @Param("productIdx") Integer productIdx);
    //ProductDTO productInfoUpdate(@Param("sellerIdx") Integer sellerIdx, @Param("productIdx") Integer productIdx);
    ProductDTO findByProductIdx2(@Param("sellerIdx") Integer sellerIdx, @Param("productIdx") Integer productIdx);
    int productInfoUpdate(ProductDTO productDTO);
    ProductMainImgUpdateDTO findMainImg(@Param("sellerIdx") Integer sellerIdx, @Param("productIdx") Integer productIdx);
    int productImgUpdate(ProductMainImgUpdateDTO productMainImgUpdateDTO);
    ProductDetailImgUpdateDTO findDetailImg(@Param("sellerIdx") Integer sellerIdx, @Param("productIdx") Integer productIdx);
    int productDetailImgUpdate(ProductDetailImgUpdateDTO productDetailImgUpdateDTO);
    //List<ProductDTO> getList(@Param("categoryIdx")Integer categoryIdx);
    List<ProductDTO> productListPagingWithSearch(Map<String, Object> pagingParams);
    int productListCount(Integer categoryIdx);
    int searchProductListCount(Map<String, Object> searchPagingParams);

    // 이무현


    // 유지호

    // 등록 상품 카운트
    int countMyProductCnt(Integer sellerIdx);

    // 페이징을 위한 카운트
    int productCount(Integer sellerIdx);

    List<ProductDTO> productPagingListWithSearch(Map<String, Object> pagingParams);

    int searchProductCount(Map<String, Object> searchPagingParams);


    // 변재혁
    ProductDTO getProductInformationByProductIdx(Integer productIdx);
    int getProductPrice(Integer productIdx);
    List<ProductDTO> getProductListForMainPage();

    int checkIfProductBelongsToSeller(@Param("sellerIdx") Integer sellerIdx,
                                      @Param("productIdx") Integer productIdx);
}
