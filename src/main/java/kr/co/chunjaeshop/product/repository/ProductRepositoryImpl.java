package kr.co.chunjaeshop.product.repository;

import kr.co.chunjaeshop.product.dto.ProductDTO;
import kr.co.chunjaeshop.product.dto.ProductDetailImgUpdateDTO;
import kr.co.chunjaeshop.product.dto.ProductMainImgUpdateDTO;
import kr.co.mapper_interface.product.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
@RequiredArgsConstructor
@Log4j2
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductMapper productMapper;

    // 남원우


    // 최경락
    @Override
    public int productSave(ProductDTO productDTO) {
        return productMapper.productSave(productDTO);
    }

    @Override
    public ProductDTO findByProductIdx(Integer sellerIdx,Integer productIdx) {
        return productMapper.findByProductIdx(sellerIdx, productIdx);
    }

    @Override
    public ProductDTO findByProductIdx2(Integer sellerIdx, Integer productIdx) {
        return productMapper.findByProductIdx2(sellerIdx, productIdx);
    }
    @Override
    public int productInfoUpdate(ProductDTO productDTO) {
        return  productMapper.productInfoUpdate(productDTO);
    }

    @Override
    public ProductMainImgUpdateDTO findMainImg(Integer sellerIdx, Integer productIdx) {
        return productMapper.findMainImg(sellerIdx, productIdx);
    }
    @Override
    public int productImgUpdate(ProductMainImgUpdateDTO productMainImgUpdateDTO) {
        log.info(productMainImgUpdateDTO);

        return productMapper.productImgUpdate(productMainImgUpdateDTO);
    }
    @Override
    public ProductDetailImgUpdateDTO findDetailImg(Integer sellerIdx, Integer productIdx) {
        return productMapper.findDetailImg(sellerIdx, productIdx);
    }

    @Override
    public int productDetailImgUpdate(ProductDetailImgUpdateDTO productDetailImgUpdateDTO) {
        return productMapper.productDetailImgUpdate(productDetailImgUpdateDTO);
    }

    @Override
    public List<ProductDTO> productListPagingWithSearch(Map<String, Object> pagingParams) {
        return productMapper.productListPagingWithSearch(pagingParams);
    }

    @Override
    public int productListCount(Integer categoryIdx) {
        return productMapper.productListCount(categoryIdx);
    }

    /**
     * 특정 카테고리에서 검색어를 이용하여 상품 목록의 개수를 조회
     *
     * @param categoryIdx   검색 대상 카테고리의 인덱스
     * @param searchField   검색 필드 (예: 상품명)
     * @param searchWord    검색어
     * @return              검색 결과에 해당하는 상품 목록의 개수
     */
    @Override
    public int searchProductListCount(Integer categoryIdx, String searchField, String searchWord) {
        // 검색에 사용할 매개변수들을 Map에 담음
        Map<String, Object> params = new HashMap<>();
        params.put("categoryIdx", categoryIdx);  // 검색 대상 카테고리 인덱스
        params.put("searchField", searchField);  // 검색 필드 (예: 상품명)
        params.put("searchWord", searchWord);    // 검색어

        // 상품 매퍼를 통해 실제 검색된 상품 목록의 개수를 조회
        return productMapper.searchProductListCount(params);
    }

    // 이무현


    // 유지호

    // 등록 상품 카운트
    @Override
    public int countMyProductCnt(Integer sellerIdx) {
        return productMapper.countMyProductCnt(sellerIdx);
    }


    // 페이징을 위한 카운트
    @Override
    public int productCount(Integer sellerIdx) {
        return productMapper.productCount(sellerIdx);
    }

    // 검색시 상품 리스트
    @Override
    public List<ProductDTO> productPagingListWithSearch(Map<String, Object> pagingParams) {
        return productMapper.productPagingListWithSearch(pagingParams);
    }

    // 검색시 상품 개수 카운트
    @Override
    public int searchProductCount(Integer sellerIdx, String searchField, String searchWord) {
        Map<String, Object> params = new HashMap<>();
        params.put("sellerIdx", sellerIdx);
        params.put("searchField", searchField);
        params.put("searchWord", searchWord);

        return productMapper.searchProductCount(params);
    }


    // 변재혁
    private final ProductMapper pMap;
    @Override
    public ProductDTO getProductInformationByProductIdx(Integer productIdx) {
        return pMap.getProductInformationByProductIdx(productIdx);
    }

    @Override
    public int getProductPrice(Integer productIdx) {
        return pMap.getProductPrice(productIdx);
    }

    @Override
    public List<ProductDTO> getProductListForMainPage() {
        return pMap.getProductListForMainPage();
    }

    @Override
    public int checkIfProductBelongsToSeller(Integer sellerIdx, Integer productIdx) {
        return pMap.checkIfProductBelongsToSeller(sellerIdx, productIdx);
    }

    @Override
    public int getReviewCount(Integer productIdx) {
        return pMap.getReviewCount(productIdx);
    }

    @Override
    public int getReviewStarSum(Integer productIdx) {
        return pMap.getReviewStarSum(productIdx);
    }
}

