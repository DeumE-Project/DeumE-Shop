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
        int resultCnt = productService.countMyProductCnt(sellerIdx);
        int totalRev = sellerService.getMyTotalRev(sellerIdx);
        int getDateRev = sellerService.getDateRev(sellerIdx);
        int avgRev = sellerService.avgRev(sellerIdx);
        List<SellDashBoardDTO> monthlySalesList = sellerService.monthlySalesList(sellerIdx);
        List<SellDashBoardDTO> categorySales = sellerService.categorySales(sellerIdx);
        List<SellDashBoardDTO> bestSellCountList = sellerService.bestSellCount(sellerIdx);
        List<SellDashBoardDTO> bestSellRevList = sellerService.bestSellRev(sellerIdx);
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
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "searchField", required = false) String searchField,
            @RequestParam(value = "searchWord", required = false) String searchWord,
            Model model,
            Authentication auth) {

        LoginUserDTO loginUserDTO = (LoginUserDTO) auth.getPrincipal();
        Integer sellerIdx = loginUserDTO.getIdx();

        List<ProductDTO> productPagingList = sellerService.productPagingListWithSearch(sellerIdx, page, searchField, searchWord);
        PageDTO sellProductPageDTO = sellerService.pagingParam(page, sellerIdx);
        PageDTO sellProductSearchPageDTO = sellerService.pagingSearchParam(page, sellerIdx, searchField, searchWord);
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