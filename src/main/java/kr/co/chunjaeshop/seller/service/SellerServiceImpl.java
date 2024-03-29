package kr.co.chunjaeshop.seller.service;


import kr.co.chunjaeshop.admin.dto.AdminPageDTO;
import kr.co.chunjaeshop.cart.dto.CartDetailDTO;
import kr.co.chunjaeshop.order_product.dto.OrderProductDTO;
import kr.co.chunjaeshop.order_product.repository.OrderProductRepository;
import kr.co.chunjaeshop.pagination.dto.PageDTO;
import kr.co.chunjaeshop.product.dto.ProductDTO;
import kr.co.chunjaeshop.product.repository.ProductRepository;
import kr.co.chunjaeshop.security.RegisterFormDTO;
import kr.co.chunjaeshop.seller.dto.SellDashBoardDTO;
import kr.co.chunjaeshop.seller.dto.SellerDTO;
import kr.co.chunjaeshop.seller.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;


    // 남원우


    // 최경락


    // 이무현
    @Override
    public List<SellerDTO> getNotRecognizedList() {
        return sellerRepository.getNotRecognizedList();
    }

    @Override
    public void updateRecognize(int i, String id) {
        sellerRepository.updateRecognize(i,id);
    }

    int adminPageLimit = 20; // (판매자 관리) 한 페이지의 게시물 갯수
    int adminBlockLimit = 5; // (판매자 관리) 한 블록당 페이지 갯수
    @Override
    public List<SellerDTO> getNotRecognizedSellerList(int page) {
        int pageStart = (page-1)*adminPageLimit;
        Map<String, Integer> notRecognizedSellerPagingParam = new HashMap<>();
        notRecognizedSellerPagingParam.put("start", pageStart);
        notRecognizedSellerPagingParam.put("limit", adminPageLimit);
        List<SellerDTO> notRecognizedSellerList = sellerRepository.getNotRecognizedSellerList(notRecognizedSellerPagingParam);

        return notRecognizedSellerList;
    }

    @Override
    public void insertRejectReason(String reason, String id) {
        Map<String, Object> rejectParam = new HashMap<>();
        rejectParam.put("reason", reason);
        rejectParam.put("id", id);
        sellerRepository.insertRejectReason(rejectParam);
    }

    @Override
    public AdminPageDTO notRecognizedSellerPagingParam(int page) {
        int notRecognizeCount = sellerRepository.notRecognizeCount();
        int maxPage = (int)(Math.ceil((double) notRecognizeCount / adminPageLimit));
        int startPage = (((int)(Math.ceil((double) page / adminBlockLimit))) - 1) * adminBlockLimit + 1;
        int endPage = startPage + adminBlockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        AdminPageDTO notRecognizePageDTO = new AdminPageDTO();
        notRecognizePageDTO.setPage(page);
        notRecognizePageDTO.setMaxPage(maxPage);
        notRecognizePageDTO.setStartPage(startPage);
        notRecognizePageDTO.setEndPage(endPage);
        return notRecognizePageDTO;
    }

    @Override
    public List<SellerDTO> getRejectSellerList(int page) {
        int pageStart = (page-1)*adminPageLimit;
        Map<String, Integer> rejectSellerPagingParam = new HashMap<>();
        rejectSellerPagingParam.put("start", pageStart);
        rejectSellerPagingParam.put("limit", adminPageLimit);
        List<SellerDTO> rejectSellerList = sellerRepository.getRejectSellerList(rejectSellerPagingParam);

        return rejectSellerList;
    }

    @Override
    public AdminPageDTO rejectSellerPagingParam(int page) {
        int rejectSellerCount = sellerRepository.rejectSellerCount();
        int maxPage = (int)(Math.ceil((double) rejectSellerCount / adminPageLimit));
        int startPage = (((int)(Math.ceil((double) page / adminBlockLimit))) - 1) * adminBlockLimit + 1;
        int endPage = startPage + adminBlockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        AdminPageDTO rejectSellerPageDTO = new AdminPageDTO();
        rejectSellerPageDTO.setPage(page);
        rejectSellerPageDTO.setMaxPage(maxPage);
        rejectSellerPageDTO.setStartPage(startPage);
        rejectSellerPageDTO.setEndPage(endPage);
        return rejectSellerPageDTO;
    }

    @Override
    public List<SellerDTO> getRecognizedSellerList(int page) {
        int pageStart = (page-1)*adminPageLimit;
        Map<String, Integer> recognizedSellerPagingParam = new HashMap<>();
        recognizedSellerPagingParam.put("start", pageStart);
        recognizedSellerPagingParam.put("limit", adminPageLimit);
        List<SellerDTO> recognizedSellerList = sellerRepository.getRecognizedSellerList(recognizedSellerPagingParam);

        return recognizedSellerList;
    }

    @Override
    public AdminPageDTO recognizedSellerPagingParam(int page) {
        int recognizedSellerCount = sellerRepository.recognizedSellerCount();
        int maxPage = (int)(Math.ceil((double) recognizedSellerCount / adminPageLimit));
        int startPage = (((int)(Math.ceil((double) page / adminBlockLimit))) - 1) * adminBlockLimit + 1;
        int endPage = startPage + adminBlockLimit  - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        AdminPageDTO recognizedSellerPageDTO = new AdminPageDTO();
        recognizedSellerPageDTO.setPage(page);
        recognizedSellerPageDTO.setMaxPage(maxPage);
        recognizedSellerPageDTO.setStartPage(startPage);
        recognizedSellerPageDTO.setEndPage(endPage);
        return recognizedSellerPageDTO;
    }

    @Override
    public SellerDTO getInfoBySellerId(String id) {
        return sellerRepository.getInfoBySellerId(id);
    }



    // 유지호

    // seller 정보 조회
    @Override
    public SellerDTO mySellerInfoByIdx(Integer sellerIdx) {
        SellerDTO sellerDTO = sellerRepository.mySellerInfoByIdx(sellerIdx);
        return sellerDTO;
    }

    // 누적매출
    @Override
    public int getMyTotalRev(Integer sellerIdx) {
        int myRev = sellerRepository.getMyTotalRev(sellerIdx);
        return myRev;
    }

    // 현재 월의 매출
    @Override
    public int getDateRev(Integer sellerIdx) {
        LocalDate currentDate = LocalDate.now();

        // 현재 년도와 월 얻기
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        String yearMonthString = String.format("%04d-%02d", currentYear, currentMonth); // '2024-01' 형식으로 값을 포매팅

        int dateRev = sellerRepository.getDateRev(sellerIdx, yearMonthString);
        return dateRev;
    }


    // 평균 월 매출
    @Override
    public int avgRev(Integer sellerIdx) {
        int avgRev = sellerRepository.avgRev(sellerIdx);
        return avgRev;
    }


    // 페이징 변수 선언
    int pageLimit = 10; // 한 페이지당 보여줄 글 개수
    int blockLimit = 5; // 하단에 보여줄 페이지 번호 개수


    // myProduct 페이징
    @Override
    public List<ProductDTO> productPagingListWithSearch(Integer sellerIdx, int page, String searchField, String searchWord) { // 넘겨줄 변수 값
        int pagingStart = (page - 1) * pageLimit; // 한 페이지에 보여줄 데이터를 계산하기 위해서 사용. 시작점
        Map<String, Object> pagingParams = new HashMap<>();
        pagingParams.put("start", pagingStart);
        pagingParams.put("limit", pageLimit);
        pagingParams.put("sellerIdx", sellerIdx);

        // 검색어가 제공된 경우에만 검색 조건 추가
        if (searchField != null && searchWord != null) { // 검색필드와 단어가 존재할 경우 해당 값을 pagingParams 저장
            pagingParams.put("searchField", searchField);
            pagingParams.put("searchWord", "%" + searchWord + "%"); // 부분 일치 검색을 위해 % 추가
        }

        List<ProductDTO> productPagingListWithSearch = productRepository.productPagingListWithSearch(pagingParams);
        return productPagingListWithSearch;
    }

    // myProduct의 페이징 계산
    @Override
    public PageDTO pagingParam(int page, Integer sellerIdx) {
        // 전체 글 개수 조회
        int productCount = productRepository.productCount(sellerIdx); // 페이징하기 위해 해당 조건에 맞는 전체 데이터의 개수를 카운트
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



    // myProduct의 검색 시 페이징 계산
    @Override
    public PageDTO pagingSearchParam(int page, Integer sellerIdx, String searchField, String searchWord) {

        // 전체 글 개수 조회
        int searchProductCount = productRepository.searchProductCount(sellerIdx, searchField, searchWord); // 검색했을 때 해당하는 전체 데이터 개수 카운트
        // 전체 페이지 개수 계산
        int maxPage = (int) (Math.ceil((double) searchProductCount / pageLimit));
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
        searchProductPagingPageDTO.setTotalCount(searchProductCount);
        return searchProductPagingPageDTO;
    }

    // manageProduct 페이징
    @Override
    public List<OrderProductDTO> sellProductManage(Integer sellerIdx, Integer productIdx, int page, String searchField, String searchWord) {
        int pagingStart = (page - 1) * pageLimit;
        Map<String, Object> pagingParams = new HashMap<>();
        pagingParams.put("start", pagingStart);
        pagingParams.put("limit", pageLimit);
        pagingParams.put("sellerIdx", sellerIdx);
        pagingParams.put("productIdx", productIdx);

        // 검색어가 제공된 경우에만 검색 조건 추가
        if (searchWord != null) {
            pagingParams.put("searchField", searchField);
            pagingParams.put("searchWord", "%" + searchWord + "%"); // 부분 일치 검색을 위해 % 추가
        }

        List<OrderProductDTO> sellProductManagePaging = orderProductRepository.sellProductManagePaging(pagingParams);
        return sellProductManagePaging;
    }

    // manageProduct 페이징 계산
    @Override
    public PageDTO orderManagePagingParm(int page, Integer sellerIdx, Integer productIdx) {
        // 전체 글 개수 조회
        int orderProductCount = orderProductRepository.orderProductCount(productIdx);
        // 전체 페이지 개수 계산
        int maxPage = (int) (Math.ceil((double) orderProductCount / pageLimit));
        // 시작 페이지 값 계산
        int startPage = (((int) (Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        // 끝 페이지 값 계산
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        PageDTO orderProductPagingPageDTO = new PageDTO();
        orderProductPagingPageDTO.setPage(page);
        orderProductPagingPageDTO.setMaxPage(maxPage);
        orderProductPagingPageDTO.setStartPage(startPage);
        orderProductPagingPageDTO.setEndPage(endPage);
        orderProductPagingPageDTO.setPageLimit(pageLimit);
        orderProductPagingPageDTO.setTotalCount(orderProductCount);
        return orderProductPagingPageDTO;
    }

    // manageProduct 검색 시 페이징 계산
    @Override
    public PageDTO orderManageSearchPagingParm(int page, Integer sellerIdx, Integer productIdx, String searchField, String searchWord) {
        // 전체 글 개수 조회
        int orderSearchProductCount = orderProductRepository.orderSearchProductCount(productIdx, searchField, searchWord);
        // 전체 페이지 개수 계산
        int maxPage = (int) (Math.ceil((double) orderSearchProductCount / pageLimit));
        // 시작 페이지 값 계산
        int startPage = (((int) (Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        // 끝 페이지 값 계산
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        PageDTO orderSearchProductPagingPageDTO = new PageDTO();
        orderSearchProductPagingPageDTO.setPage(page);
        orderSearchProductPagingPageDTO.setMaxPage(maxPage);
        orderSearchProductPagingPageDTO.setStartPage(startPage);
        orderSearchProductPagingPageDTO.setEndPage(endPage);
        orderSearchProductPagingPageDTO.setPageLimit(pageLimit);
        orderSearchProductPagingPageDTO.setTotalCount(orderSearchProductCount);
        return orderSearchProductPagingPageDTO;
    }

    // 매월 매출
    @Override
    public List<SellDashBoardDTO> monthlySalesList(Integer sellerIdx) {
        List<SellDashBoardDTO> monthlySalesList = sellerRepository.monthlySalesList(sellerIdx);
        return monthlySalesList;
    }

    // 카테고리별 매출
    @Override
    public List<SellDashBoardDTO> categorySales(Integer sellerIdx) {
        List<SellDashBoardDTO> categorySalesList = sellerRepository.categorySalesList(sellerIdx);
        return categorySalesList;
    }

    // 많이 팔린 상품
    @Override
    public List<SellDashBoardDTO> bestSellCount(Integer sellerIdx) {
        List<SellDashBoardDTO> bestSellCountList = sellerRepository.bestSellCountList(sellerIdx);
        return bestSellCountList;
    }

    // 매출이 높은 상품
    @Override
    public List<SellDashBoardDTO> bestSellRev(Integer sellerIdx) {
        List<SellDashBoardDTO> bestSellRevList = sellerRepository.bestSellRevList(sellerIdx);
        return bestSellRevList;
    }

    // 변재혁
    @Override
    public boolean sellerRegister(RegisterFormDTO registerFormDTO) {
        int result = sellerRepository.sellerRegister(registerFormDTO);
        return (result == 1) ? true : false;
    }


    @Override
    public boolean idDuplicationCheck(String id) {
        int result = sellerRepository.idDuplicationCheck(id);
        return (result == 1) ? true : false;
    }

    @Override
    public int increaseSellerIncome(List<CartDetailDTO> cartDetailDTOList, Integer sellerIdx) {
        int money = 0;
        for (CartDetailDTO cartDetailDTO : cartDetailDTOList) {
            int count = cartDetailDTO.getBuyCount();
            int price = cartDetailDTO.getProductPrice();
            money += (count * price);
        }
        return sellerRepository.increaseSellerIncome(money, sellerIdx);
    }

    @Override
    public void updateStatus(Integer orderIdx, String updatedStatus) {
        Map<String, Object> updateParams = new HashMap<>();
        updateParams.put("orderIdx", orderIdx);
        updateParams.put("updatedStatus", updatedStatus);
        orderProductRepository.updateStatus(updateParams);
    }
}