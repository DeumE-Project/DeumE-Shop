package kr.co.chunjaeshop.product.controller;

import kr.co.chunjaeshop.product.dto.ProductDTO;
import kr.co.chunjaeshop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
@Log4j2
public class ProductController {

    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁
    private final ProductService pService;

    @GetMapping(value = "/detail")
    public String productDetailForm(@RequestParam(required = false) Integer productIdx,
                                    Model model) {
        log.info("productIdx = {}", productIdx);
        if (productIdx == null) {
            return "cart/productMainCart";
        }
        ProductDTO productDTO = pService.getProductInformationByProductIdx(productIdx);
        log.info("productDTO = {}", productDTO);
        model.addAttribute("productDTO", productDTO);
        return "cart/productMainCart";
    }
}
