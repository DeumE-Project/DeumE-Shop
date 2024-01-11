package kr.co.chunjaeshop.cart.controller;

import kr.co.chunjaeshop.cart.dto.*;
import kr.co.chunjaeshop.cart.service.CartService;
import kr.co.chunjaeshop.order_product.service.OrderProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
@Log4j2
public class CartController {
    private final CartService cartService;
    private final OrderProductService orderProductService;

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

    @GetMapping(value = "/order")
    public String orderPageForm(@ModelAttribute OrderProductForm orderProductForm,
                                Model model) {
        Integer cartIdx = orderProductForm.getCartIdx();
        if (cartIdx == null) {
            log.warn("cartIdx is null");
            return "redirect:/cart/list";
        }
        model.addAttribute("cartIdx", orderProductForm.getCartIdx());

        CartDTO cart = cartService.getSpecificCart(8, cartIdx);
        model.addAttribute("cart", cart);

        return "cart/orderPage";
    }

    @PostMapping(value = "/order")
    public String order(@Validated @ModelAttribute OrderProductForm orderProductForm,
                        BindingResult bindingResult,
                        Model model) {
        log.info("orderProductForm = {}", orderProductForm);
        Integer cartIdx = orderProductForm.getCartIdx();
        model.addAttribute("cartIdx", cartIdx);

        CartDTO cart = cartService.getSpecificCart(8, cartIdx);

        if (bindingResult.hasErrors()) {
            model.addAttribute("cart", cart);
            return "cart/orderPage";
        }

        int orderTotalPrice = 0;

        for (CartDetailDTO cartDetailDTO : cart.getCartDetailDTOList()) {
            orderTotalPrice += cartDetailDTO.getProductPrice();
        }

        orderProductForm.setOrderTotalPrice(orderTotalPrice);
        orderProductForm.setCustomerIdx(8);

        log.info("after setting orderProductForm = {}", orderProductForm);
        boolean result = orderProductService.insertNewOrder(orderProductForm);
        log.info("order_product_idx = {}", orderProductForm.getOrderIdx());

        return null;
    }
}
