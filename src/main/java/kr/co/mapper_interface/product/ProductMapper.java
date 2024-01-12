package kr.co.mapper_interface.product;

import kr.co.chunjaeshop.product.dto.ProductDTO;
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

    // 이무현


    // 유지호
    int countMyProductCnt(Integer sellerIdx);

    List<ProductDTO> myProduct(Integer sellerIdx);

/*    List<ProductDTO> sellProductPaging(Map<String, Integer> pagingParams);*/

    int productCount(Integer sellerIdx);

    List<ProductDTO> productPagingListWithSearch(Map<String, Object> pagingParams);

    int searchproductCount(Map<String, Object> searchPagingParams);

    /*List<ProductDTO> sellProductManage(@Param("sellerIdx") Integer sellerIdx, @Param("productIdx") Integer productIdx);*/


    // 변재혁
    ProductDTO getProductInformationByProductIdx(Integer productIdx);
    int getProductPrice(Integer productIdx);
}
