package kr.co.chunjaeshop.cart.controller;

import kr.co.chunjaeshop.cart.dto.*;
import kr.co.chunjaeshop.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        Integer customerIdx = 8;
        List<CartDTO> allMyCartList = cartService.getAllMyCartList(customerIdx);
        log.info("allMyCartList = {}", allMyCartList);
        model.addAttribute("cartList", allMyCartList);
        return "cart/myCartList";
    }

    @PostMapping(value = "/change-cart-detail-buy-count")
    @ResponseBody
    public ResponseEntity<String> changeCartDetailBuyCount(@ModelAttribute ChangeCartDetailDTO changeCartDetailDTO) {
        log.info("updateCartDetailDTO = {}", changeCartDetailDTO);
        CartDetailResult result = cartService.changeCartDetailBuyCount(changeCartDetailDTO);
        if (result == CartDetailResult.CHANGED) {
            return ResponseEntity.status(HttpStatus.CREATED).body("changed");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
        }
    }
}
