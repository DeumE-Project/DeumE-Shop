package kr.co.chunjaeshop.cart.controller;

import kr.co.chunjaeshop.cart.service.CartService;
import kr.co.chunjaeshop.cart.dto.AddToCartForm;
import kr.co.chunjaeshop.cart.dto.CartResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
@Log4j2
public class CartController {
    private final CartService cartService;

    @PostMapping(value = "/add-cart")
    public String addToCart(@ModelAttribute AddToCartForm addToCartForm) {
        log.info("addToCartForm = {}", addToCartForm);
        addToCartForm.setCustomerIdx(8);
        CartResult result = cartService.addToCart(addToCartForm);
        return null;
    }
}
