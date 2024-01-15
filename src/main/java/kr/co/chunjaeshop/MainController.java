package kr.co.chunjaeshop;

import kr.co.chunjaeshop.product.dto.ProductDTO;
import kr.co.chunjaeshop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = "/main")
@Controller
@RequiredArgsConstructor
@Log4j2
public class MainController {
    private final ProductService productService;

    @GetMapping
    public String mainPage(Model model) {
        List<ProductDTO> productList = productService.getProductListForMainPage();
        model.addAttribute("productList", productList);
        return "common/mainPage";
    }
}
