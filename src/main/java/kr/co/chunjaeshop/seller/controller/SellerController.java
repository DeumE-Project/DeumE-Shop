package kr.co.chunjaeshop.seller.controller;

import kr.co.chunjaeshop.order_product.dto.OrderProductDTO;
import kr.co.chunjaeshop.pagination.dto.PageDTO;
import kr.co.chunjaeshop.product.dto.ProductDTO;
import kr.co.chunjaeshop.product.service.ProductService;
import kr.co.chunjaeshop.security.LoginUserDTO;
import kr.co.chunjaeshop.seller.dto.SellDashBoardDTO;
import kr.co.chunjaeshop.seller.dto.SellerDTO;
import kr.co.chunjaeshop.seller.service.SellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;


@Controller
@RequestMapping("/seller")
@RequiredArgsConstructor
@Log4j2
public class SellerController {
    private final SellerService sellerService;
    private final ProductService productService;

    // 남원우


    // 최경락


    // 이무현


    // 유지호
    @GetMapping("/mySellerPage")
    public String mySellerInfoByIdx(
            Model model,
            Authentication auth) {
        LoginUserDTO loginUserDTO = (LoginUserDTO) auth.getPrincipal();
        Integer sellerIdx = loginUserDTO.getIdx();
        log.info("sellerIdx = {}", sellerIdx);
        SellerDTO sellerDTO = sellerService.mySellerInfoByIdx(sellerIdx);
        int resultCnt = productService.countMyProductCnt(sellerIdx); // 등록한 상품 개수를 카운트하기 위해 사용
        int totalRev = sellerService.getMyTotalRev(sellerIdx); // 누적판매금액을 계산하기 위해 사용
        int getDateRev = sellerService.getDateRev(sellerIdx); // 이달의 매출을 구하기 위해 사용
        int avgRev = sellerService.avgRev(sellerIdx); // 월평균 매출을 구하기 위해 사용 (현재 월 제외)
        List<SellDashBoardDTO> monthlySalesList = sellerService.monthlySalesList(sellerIdx); // 월별 매출을 구하기 위해 사용
        List<SellDashBoardDTO> categorySales = sellerService.categorySales(sellerIdx); // 카테고리별 매출을 구하기 위해 사용
        List<SellDashBoardDTO> bestSellCountList = sellerService.bestSellCount(sellerIdx); // 가장 많이 팔린 상품 카운트
        List<SellDashBoardDTO> bestSellRevList = sellerService.bestSellRev(sellerIdx); // 가장 매출이 높은 상품 카운트
        model.addAttribute("myCount", resultCnt);
        model.addAttribute("mySeller", sellerDTO);
        model.addAttribute("myRev", totalRev);
        model.addAttribute("dateRev", getDateRev);
        model.addAttribute("avgRev", avgRev);
        model.addAttribute("sellerIdx", 1);
        model.addAttribute("monthlySalesList", monthlySalesList);
        model.addAttribute("categorySales", categorySales);
        model.addAttribute("bestSellCountList", bestSellCountList);
        model.addAttribute("bestSellRevList", bestSellRevList);
        return "seller/mySellerPage";
    }

    @GetMapping("/myProduct")
    public String myProductManage(
//            @RequestParam("sellerIdx") Integer sellerIdx,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page, // 페이징, 기본값을 1로 받아옴
            @RequestParam(value = "searchField", required = false) String searchField, // 검색시 해당 값을 받아옴
            @RequestParam(value = "searchWord", required = false) String searchWord, // 검색 단어 값을 받아옴
            Model model,
            Authentication auth) {

        LoginUserDTO loginUserDTO = (LoginUserDTO) auth.getPrincipal();
        Integer sellerIdx = loginUserDTO.getIdx();

        List<ProductDTO> productPagingList = sellerService.productPagingListWithSearch(sellerIdx, page, searchField, searchWord); // 페이징, 검색 포함 쿼리에 넘겨 줄 값
        PageDTO sellProductPageDTO = sellerService.pagingParam(page, sellerIdx); // 페이징 시 필요한 값들을 PageDTO에 저장
        PageDTO sellProductSearchPageDTO = sellerService.pagingSearchParam(page, sellerIdx, searchField, searchWord); // 검색할 경우 페이징 시 필요한 값들을 PageDTO에 저장
        // jsp로 변수 값을 넘겨줌
        model.addAttribute("myProductList", productPagingList);
        model.addAttribute("sellProductpaging", sellProductPageDTO);
        model.addAttribute("sellProductSearchPaging", sellProductSearchPageDTO);
        model.addAttribute("sellerIdx", 1);
        model.addAttribute("searchField", searchField);
        model.addAttribute("searchWord", searchWord);
        return "seller/myProduct";
    }

    @GetMapping("/manageProduct")
    public String sellManage(
//            @RequestParam("sellerIdx") Integer sellerIdx,
            @RequestParam("productIdx") Integer productIdx,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "searchField", required = false) String searchField,
            @RequestParam(value = "searchWord", required = false) String searchWord,
            Model model,
            Authentication auth) throws UnsupportedEncodingException {

        if (searchWord != null) {
            searchWord = URLDecoder.decode(searchWord, "utf-8");
        }

        {

            LoginUserDTO loginUserDTO = (LoginUserDTO) auth.getPrincipal();
            Integer sellerIdx = loginUserDTO.getIdx();

            // 본인의 상품이 맞는지 검증 로직
            int result = productService.checkIfProductBelongsToSeller(sellerIdx, productIdx);

            if (result != 1) {
                return "redirect:/seller/myProduct";
            }
                log.info("productIdx = {}", productIdx);
                List<OrderProductDTO> orderProductDTOList = sellerService.sellProductManage(sellerIdx, productIdx, page, searchField, searchWord);
                PageDTO orderManagePageDTO = sellerService.orderManagePagingParm(page, sellerIdx, productIdx);
                PageDTO orderManageSearchPageDTO = sellerService.orderManageSearchPagingParm(page, sellerIdx, productIdx, searchField, searchWord);
                model.addAttribute("detailMyPd", orderProductDTOList);
                model.addAttribute("orderManagePaging", orderManagePageDTO);
                model.addAttribute("orderManageSearchPaging", orderManageSearchPageDTO);
                model.addAttribute("sellerIdx", sellerIdx);
                model.addAttribute("productIdx", productIdx);
                model.addAttribute("searchWord", searchWord);
                model.addAttribute("page", page);

            return "seller/manageProduct";

            }
        }


    @GetMapping("updateStatus")
    public String updateStatus(@RequestParam("updateStatus") String updateStatus,
                               @RequestParam("orderIdx") Integer orderIdx,
                               //@RequestParam("sellerIdx") Integer sellerIdx,
                               @RequestParam("productIdx") Integer productIdx,
                               @RequestParam(value = "page", required = false) String page,
                               @RequestParam(value = "searchWord", required = false) String searchWord,
                               Authentication auth) throws UnsupportedEncodingException {

        LoginUserDTO loginUserDTO = (LoginUserDTO) auth.getPrincipal();
        Integer sellerIdx = loginUserDTO.getIdx();


        String updatedStatus = "1"; // 기본 배송중

        log.info("updateStatus = {}, orderIdx = {}, sellerIdx = {}, productIdx = {}", updateStatus, orderIdx, sellerIdx, productIdx);

        if ("배송완료".equals(updateStatus)) {
            updatedStatus = "2"; // 배송완료시 2로 업데이트
        }

        sellerService.updateStatus(orderIdx, updatedStatus);

        // 기본값이 아닌 경우에만 URL에 추가
        StringBuilder redirectUrl = new StringBuilder("/seller/manageProduct?sellerIdx=" + sellerIdx + "&productIdx=" + productIdx);

        String redirecrurl = "/seller/manageProduct?sellerIdx=" + sellerIdx + "&productIdx=" + productIdx;

        // page 값이 숫자로 변환 가능한지 확인하고, 가능하지 않으면 기본값 설정
        try {
            Integer pageValue = Integer.parseInt(page);
            redirectUrl.append("&page=").append(pageValue);
            redirecrurl = redirecrurl + "&page=" + pageValue;
        } catch (NumberFormatException e) {
            // 변환 불가능한 경우에는 기본값인 1로 설정
            redirectUrl.append("&page=1");
            redirecrurl = redirecrurl + "&page=1";
        }


        if (searchWord != null) {
            redirectUrl.append("&searchWord=").append(searchWord);
            redirecrurl = redirecrurl + "&searchWord=" + URLEncoder.encode(searchWord, "utf-8");
        }
        log.info("redirecturl = {}", redirecrurl);
        return "redirect:" + redirecrurl ;
    }


            // 변재혁



}