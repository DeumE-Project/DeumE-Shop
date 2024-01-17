package kr.co.chunjaeshop.product.service;

import kr.co.chunjaeshop.pagination.dto.PageDTO;
import kr.co.chunjaeshop.product.dto.ProductDTO;
import kr.co.chunjaeshop.product.dto.ProductDetailImgUpdateDTO;
import kr.co.chunjaeshop.product.dto.ProductMainImgUpdateDTO;
import kr.co.chunjaeshop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;




    // 남원우


    // 최경락
    @Override
    public int productSave(ProductDTO productDTO) {
        int saveResult = productRepository.productSave(productDTO);
        return saveResult;
    }
    public ProductDTO findByProductIdx(Integer sellerIdx, Integer productIdx) {
        return productRepository.findByProductIdx(sellerIdx, productIdx);
    }
    @Override
    public ProductDTO findByProductIdx2(Integer sellerIdx, Integer productIdx) {
        return productRepository.findByProductIdx2(sellerIdx, productIdx);
    }
    @Override
    public boolean productInfoUpdate(ProductDTO productDTO) {
        int result = productRepository.productInfoUpdate(productDTO);
        return result > 0;
    }
    @Override
    public ProductMainImgUpdateDTO findMainImg(Integer sellerIdx, Integer productIdx) {
        return productRepository.findMainImg(sellerIdx, productIdx);
    }
    @Override
    public boolean productImgUpdate(ProductMainImgUpdateDTO productMainImgUpdateDTO) {
        int result = productRepository.productImgUpdate(productMainImgUpdateDTO);
        log.info(productMainImgUpdateDTO);
        return  result > 0;

    }
    @Override
    public ProductDetailImgUpdateDTO findDetailImg(Integer sellerIdx, Integer productIdx) {
        return productRepository.findDetailImg(sellerIdx, productIdx);
    }
    @Override
    public boolean productDetailImgUpdate(ProductDetailImgUpdateDTO productDetailImgUpdateDTO) {
        int result = productRepository.productDetailImgUpdate(productDetailImgUpdateDTO);
        return result >0;
    }
    // 전역변수로 사용
    int pageLimit = 16; // 한 페이지당 보여줄 글 개수
    int blockLimit = 5; // 하단에 보여줄 페이지 번호 개수
    @Override
    public List<ProductDTO> productListPagingWithSearch(Integer categoryIdx, int page, String searchField, String searchWord) {
        /**
         * 페이징 및 검색 조건을 적용하여 상품 목록을 조회하는 메서드
         *
         * @param categoryIdx 카테고리 식별자
         * @param page 현재 페이지 번호
         * @param searchField 검색 필드 (상품명, 카테고리 등)
         * @param searchWord 검색어
         * @return 페이징 및 검색 조건에 따른 상품 목록
         */
        // 페이징 시작 위치 계산
        int pagingStart = (page - 1) * pageLimit;
        // 페이징 조건을 담은 맵 생성
        Map<String, Object> pagingParams = new HashMap<>();
        pagingParams.put("start", pagingStart);
        pagingParams.put("limit", pageLimit);
        pagingParams.put("categoryIdx", categoryIdx);

        // 검색어가 제공된 경우에만 검색 조건 추가
        if (searchField != null && searchWord != null) {
            pagingParams.put("searchField", searchField);
            pagingParams.put("searchWord", "%" + searchWord + "%"); // 부분 일치 검색을 위해 % 추가
        }
        // 상품 목록 조회 서비스 호출
        List<ProductDTO> productListPagingWithSearch = productRepository.productListPagingWithSearch(pagingParams);
        return productListPagingWithSearch;
    }

    /**
     * 페이징 처리에 필요한 매개변수를 계산하여 반환하는 메서드
     *
     * @param page 현재 페이지 번호
     * @param categoryIdx 카테고리 식별자
     * @return 페이징 처리에 필요한 매개변수를 담은 PageDTO 객체
     */
    @Override
    public PageDTO productListPagingParam(int page, Integer categoryIdx) {

        int productCount = productRepository.productListCount(categoryIdx);
        // 전체 페이지 개수 계산
        int maxPage = (int) (Math.ceil((double) productCount / pageLimit));
        // 시작 페이지 값 계산
        int startPage = (((int) (Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        // 끝 페이지 값 계산
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }

        /**
         * 페이징 처리에 필요한 매개변수들을 설정하여 반환하는 객체를 생성합니다.
         *
         * @param page        현재 페이지 번호
         * @param maxPage     전체 페이지 개수
         * @param startPage   시작 페이지 값
         * @param endPage     끝 페이지 값
         * @param pageLimit   한 페이지에 보여질 항목 개수
         * @param totalCount  전체 항목 개수
         * @return            설정된 페이징 매개변수를 담은 PageDTO 객체
         */
        // PageDTO 객체 생성 및 설정
        PageDTO productPagingPageDTO = new PageDTO();
        productPagingPageDTO.setPage(page);         // 현재 페이지 번호 설정
        productPagingPageDTO.setMaxPage(maxPage);   // 전체 페이지 개수 설정
        productPagingPageDTO.setStartPage(startPage); // 시작 페이지 값 설정
        productPagingPageDTO.setEndPage(endPage);     // 끝 페이지 값 설정
        productPagingPageDTO.setPageLimit(pageLimit); // 한 페이지에 보여질 항목 개수 설정
        productPagingPageDTO.setTotalCount(productCount);  // 전체 항목 개수 설정
        return productPagingPageDTO;
    }
    /**
     * 검색 조건을 포함한 페이징 처리에 필요한 매개변수를 계산하여 반환하는 메서드
     *
     * @param page 현재 페이지 번호
     * @param categoryIdx 카테고리 식별자
     * @param searchField 검색 필드 (제목 등)
     * @param searchWord 검색어
     * @return 페이징 처리에 필요한 매개변수를 담은 PageDTO 객체
     */
    @Override
    public PageDTO productListPagingSearchParam(int page, Integer categoryIdx, String searchField, String searchWord) {
        // 전체 글 개수 조회
        int searchProductCount = productRepository.searchProductListCount(categoryIdx, searchField, searchWord);
        // 전체 페이지 개수 계산
        int maxPage = (int) (Math.ceil((double) searchProductCount / pageLimit));
        // 시작 페이지 값 계산
        int startPage = (((int) (Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        // 끝 페이지 값 계산
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        // PageDTO 객체 생성 및 설정
        PageDTO searchProductPagingPageDTO = new PageDTO();
        searchProductPagingPageDTO.setPage(page);             // 현재 페이지 번호 설정
        searchProductPagingPageDTO.setMaxPage(maxPage);       // 전체 페이지 개수 설정
        searchProductPagingPageDTO.setStartPage(startPage);    // 시작 페이지 값 설정
        searchProductPagingPageDTO.setEndPage(endPage);        // 끝 페이지 값 설정
        searchProductPagingPageDTO.setPageLimit(pageLimit);    // 한 페이지에 보여질 상품 개수 설정
        searchProductPagingPageDTO.setTotalCount(searchProductCount);  // 검색된 상품의 총 개수 설정

        return searchProductPagingPageDTO;
    }


    // 이무현


    // 유지호

    @Override
    public int countMyProductCnt(Integer sellerIdx) {
        int myCount = productRepository.countMyProductCnt(sellerIdx);
        return myCount;
    }




    // 변재혁
    private final ProductRepository pRepo;

    @Override
    public ProductDTO getProductInformationByProductIdx(Integer productIdx) {
        ProductDTO result = pRepo.getProductInformationByProductIdx(productIdx);
        return result;
    }

    @Override
    public List<ProductDTO> getProductListForMainPage() {
        return pRepo.getProductListForMainPage();
    }

    @Override
    public int checkIfProductBelongsToSeller(Integer sellerIdx, Integer productIdx) {
        return pRepo.checkIfProductBelongsToSeller(sellerIdx, productIdx);
    }
}
