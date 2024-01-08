package kr.co.chunjaeshop.cart.controller;

import kr.co.chunjaeshop.cart.dto.CartDTO;
import kr.co.chunjaeshop.cart.service.CartService;
import kr.co.chunjaeshop.cart.dto.AddToCartForm;
import kr.co.chunjaeshop.cart.dto.CartResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
@Log4j2
public class CartController {
    private final CartService cartService;

    @PostMapping(value = "/add-cart")
    @ResponseBody
    public String addToCart(@ModelAttribute AddToCartForm addToCartForm) {
        log.info("addToCartForm = {}", addToCartForm);
        addToCartForm.setCustomerIdx(8);
        CartResult result = cartService.addToCart(addToCartForm);
        return null;
    }

    @GetMapping(value = "/list")
    public String myCartList(Model model) {
        Integer customerIdx = 1;
        List<CartDTO> allMyCartList = cartService.getAllMyCartList(customerIdx);
        log.info("allMyCartList = {}", allMyCartList);
        model.addAttribute("cartList", allMyCartList);
        return "cart/myCartList";
    }
}
