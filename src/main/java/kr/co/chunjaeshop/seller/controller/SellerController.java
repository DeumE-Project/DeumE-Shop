package kr.co.chunjaeshop.seller.controller;

import kr.co.chunjaeshop.product.dto.ProductDTO;
import kr.co.chunjaeshop.product.service.ProductService;
import kr.co.chunjaeshop.seller.dto.SellerDTO;
import kr.co.chunjaeshop.seller.service.SellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

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
    public String mySellerInfoByIdx(@RequestParam("sellerIdx") Integer sellerIdx,
                                    @RequestParam("thisMonth") String thisMonth,
                                    /*@RequestParam("lastMonth") String lastMonth,*/
                                    Model model){
        log.info("sellerIdx = {}", sellerIdx);
        SellerDTO sellerDTO = sellerService.mySellerInfoByIdx(sellerIdx);
        int resultCnt = productService.countMyProductCnt(sellerIdx);
        int totalRev = sellerService.getMyTotalRev(sellerIdx);
        int getDateRev = sellerService.getDateRev(sellerIdx, thisMonth);
        int avgRev = sellerService.avgRev(sellerIdx);
        /*int getDateRevLast = sellerService.getDateRevLast(sellerIdx, lastMonth);*/
        model.addAttribute("myCount", resultCnt);
        model.addAttribute("mySeller", sellerDTO);
        model.addAttribute("myRev", totalRev);
        model.addAttribute("dateRev", getDateRev);
        model.addAttribute("avgRev", avgRev);
        /*model.addAttribute("lastDateRev", getDateRevLast);*/
        return "/seller/mySellerPage";
    }

    @GetMapping("/myProduct")
    public String myProductManage(@RequestParam("sellerIdx") Integer sellerIdx, Model model){
        List<ProductDTO> productDTOList = sellerService.myProduct(sellerIdx);
        model.addAttribute("myProductList", productDTOList);
        return "/seller/myProduct";
    }



    // 변재혁

}
