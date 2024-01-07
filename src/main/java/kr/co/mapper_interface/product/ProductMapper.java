package kr.co.mapper_interface.product;
import kr.co.chunjaeshop.product.dto.ProductDTO;
import java.util.List;
import java.util.Map;

public interface ProductMapper {

    // 남원우


    // 최경락


    // 이무현


    // 유지호
    int countMyProductCnt(Integer sellerIdx);

    List<ProductDTO> myProduct(Integer sellerIdx);

    List<ProductDTO> sellProductPaging(Map<String, Integer> pagingParams);

    int productCount();

    // 변재혁

}
