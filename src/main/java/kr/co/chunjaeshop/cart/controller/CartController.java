package kr.co.chunjaeshop.cart.controller;

import kr.co.chunjaeshop.cart.dto.*;
import kr.co.chunjaeshop.cart.service.CartService;
import kr.co.chunjaeshop.order_detail.service.OrderDetailService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
@Log4j2
public class CartController {
    private final CartService cartService;
    private final OrderProductService orderProductService;
    private final OrderDetailService orderDetailService;

    @PostMapping(value = "/add-cart")
    public String addToCart(@ModelAttribute AddToCartForm addToCartForm,
                            RedirectAttributes redirectAttributes) {
        log.info("addToCartForm = {}", addToCartForm);

        // security 에서 받아와야 함
        Integer customerIdx = 8;
        addToCartForm.setCustomerIdx(customerIdx);
        CartResult result = cartService.addToCart(addToCartForm);
        if (result == CartResult.NEW_CART) {
            redirectAttributes.addFlashAttribute("cartMsg", "장바구니가 새롭게 생성되어 상품이 추가되었습니다");
        } else if (result == CartResult.NEW_CART_DETAIL) {
            redirectAttributes.addFlashAttribute("cartMsg", "장바구니에 상품이 추가되었습니다");
        } else if (result == CartResult.ALREADY) {
            redirectAttributes.addFlashAttribute("cartMsg", "상품이 이미 장바구니에 담겨있어 개수가 추가되었습니다");
        } else if (result == CartResult.FAILED) {
            redirectAttributes.addFlashAttribute("cartMsg", "장바구니 추가 오류");
        }
        return "redirect:/product/detail?productIdx=" + addToCartForm.getProductIdx();
    }

    @GetMapping(value = "/list")
    public String myCartList(Model model) {
        // security 에서 받아와야 함
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

        // security에서 받아와야 함
        Integer customerIdx = 8;
        CartDTO cart = cartService.getSpecificCart(customerIdx, cartIdx);

        if (cart == null) {
            log.warn("사용자의 장바구니가 아닙니다");
        }

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

        Integer customerIdx = 8;
        CartDTO cart = cartService.getSpecificCart(customerIdx, cartIdx);

        if (cart == null) {
            log.warn("사용자의 장바구니가 아닙니다.");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("cart", cart);
            return "cart/orderPage";
        }

        orderProductForm.setCustomerIdx(customerIdx);

        log.info("after setting orderProductForm = {}", orderProductForm);
        boolean orderProductInsertResult = orderProductService.insertNewOrder(orderProductForm, cart);

        if (orderProductInsertResult) {
            log.info("order_product_idx = {}", orderProductForm.getOrderIdx());
            boolean result = orderDetailService.insertNewOrderDetails(orderProductForm.getOrderIdx(),
                                                                        cart.getCartDetailDTOList());
        } else {

        }

        return null;
    }
}
