package kr.co.chunjaeshop.product.service;

import kr.co.chunjaeshop.product.dto.ProductDTO;
import kr.co.chunjaeshop.product.dto.ProductDetailImgUpdateDTO;
import kr.co.chunjaeshop.product.dto.ProductMainImgUpdateDTO;
import kr.co.chunjaeshop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<ProductDTO> getList() {
        return productRepository.getList();
    }


    /*@Override
    public List<ProductDTO> productPagingListWithSearch(Integer productIdx, int page, String searchField, String searchWord) {
        int pagingStart = (page - 1) * pageLimit;
        Map<String, Object> pagingParams = new HashMap<>();
        pagingParams.put("start", pagingStart);
        pagingParams.put("limit", pageLimit);
        pagingParams.put("productIdx", productIdx);

        // 검색어가 제공된 경우에만 검색 조건 추가
        if (searchField != null && searchWord != null) {
            pagingParams.put("searchField", searchField);
            pagingParams.put("searchWord", "%" + searchWord + "%"); // 부분 일치 검색을 위해 % 추가
        }

        List<ProductDTO> productPagingListWithSearch = productRepository.productPagingListWithSearch(pagingParams);
        return productPagingListWithSearch;
    }
    @Override
    public PageDTO pagingParam(int page, Integer sellerIdx) {
        // 전체 글 개수 조회
        int productCount = productRepository.productCount(sellerIdx);
        // 전체 페이지 개수 계산
        int maxPage = (int) (Math.ceil((double) productCount / pageLimit));
        // 시작 페이지 값 계산
        int startPage = (((int) (Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        // 끝 페이지 값 계산
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        PageDTO productPagingPageDTO = new PageDTO();
        productPagingPageDTO.setPage(page);
        productPagingPageDTO.setMaxPage(maxPage);
        productPagingPageDTO.setStartPage(startPage);
        productPagingPageDTO.setEndPage(endPage);
        productPagingPageDTO.setPageLimit(pageLimit);
        productPagingPageDTO.setTotalCount(productCount);
        return productPagingPageDTO;
    }



    @Override
    public PageDTO pagingSearchParam(int page, Integer sellerIdx, String searchField, String searchWord) {

        // 전체 글 개수 조회
        int searchproductCount = productRepository.searchproductCount(sellerIdx, searchField, searchWord);
        // 전체 페이지 개수 계산
        int maxPage = (int) (Math.ceil((double) searchproductCount / pageLimit));
        // 시작 페이지 값 계산
        int startPage = (((int) (Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        // 끝 페이지 값 계산
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        PageDTO searchProductPagingPageDTO = new PageDTO();
        searchProductPagingPageDTO.setPage(page);
        searchProductPagingPageDTO.setMaxPage(maxPage);
        searchProductPagingPageDTO.setStartPage(startPage);
        searchProductPagingPageDTO.setEndPage(endPage);
        searchProductPagingPageDTO.setPageLimit(pageLimit);
        searchProductPagingPageDTO.setTotalCount(searchproductCount);
        return searchProductPagingPageDTO;
    }*/


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
}
