package kr.co.chunjaeshop.product.repository;

import kr.co.chunjaeshop.product.dto.ProductDTO;
import kr.co.mapper_interface.product.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;;

@Repository
@RequiredArgsConstructor
@Log4j2
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductMapper productMapper;

    // 남원우


    // 최경락

    public int productSave(ProductDTO productDTO) {
        return productMapper.productSave(productDTO);
    }

    @Override
    public ProductDTO findByProductIdx(Integer sellerIdx,Integer productIdx) {
        return productMapper.findByProductIdx(sellerIdx, productIdx);
    }


    // 이무현


    // 유지호

    @Override
    public int countMyProductCnt(Integer sellerIdx) {
        return productMapper.countMyProductCnt(sellerIdx);
    }

    @Override
    public List<ProductDTO> productPagingList(Map<String, Integer> pagingParams) {
        return productMapper.sellProductPaging(pagingParams);
    }

    @Override
    public int productCount(Integer sellerIdx) {
        return productMapper.productCount(sellerIdx);
    }

    @Override
    public List<ProductDTO> myProduct(Integer sellerIdx) {
        return productMapper.myProduct(sellerIdx);
    }

    @Override
    public List<ProductDTO> productPagingListWithSearch(Map<String, Object> pagingParams) {
        return productMapper.productPagingListWithSearch(pagingParams);
    }

    @Override
    public int searchproductCount(Integer sellerIdx, String searchField, String searchWord) {
        Map<String, Object> params = new HashMap<>();
        params.put("sellerIdx", sellerIdx);
        params.put("searchField", searchField);
        params.put("searchWord", searchWord);

        return productMapper.searchproductCount(params);
    }

/*    @Override
    public List<ProductDTO> sellProductManage(Integer sellerIdx, Integer productIdx) {
        return productMapper.sellProductManage(sellerIdx, productIdx);
    }*/

    // 변재혁

}