package kr.co.chunjaeshop.cart.controller;

import kr.co.chunjaeshop.cart.dto.*;
import kr.co.chunjaeshop.cart.service.CartService;
import kr.co.chunjaeshop.order_detail.service.OrderDetailService;
import kr.co.chunjaeshop.order_product.service.OrderProductService;
import kr.co.chunjaeshop.security.LoginUserDTO;
import kr.co.chunjaeshop.seller.service.SellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
@Log4j2
public class CartController {
    private final CartService cartService;
    private final OrderProductService orderProductService;
    private final OrderDetailService orderDetailService;
    private final SellerService sellerService;

    @PostMapping(value = "/add-cart")
    public String addToCart(@ModelAttribute AddToCartForm addToCartForm,
                            RedirectAttributes redirectAttributes,
                            HttpServletResponse httpServletResponse,
                            HttpServletRequest httpServletRequest,
                            Authentication auth) throws UnsupportedEncodingException {
        log.info("addToCartForm = {}", addToCartForm);
        log.info("auth = {}", auth);

        if (auth != null) {
            // security 에서 받아와야 함
            LoginUserDTO loginUserDTO = (LoginUserDTO) auth.getPrincipal();
            Integer customerIdx = loginUserDTO.getIdx();

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
        } else {
            // productIdx 와 buyCount가 들어올 것임
            // 먼저 cart 라는 이름을 가진 cookie가 있는지 확인하고, 있다면 productIdx 를 가진 게 있는지 확인
            Cookie[] cookies = httpServletRequest.getCookies();
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                if (cookieName.equals("cart")) { // 일단 cart 라는 쿠키 이름이 있을 경우
                    String cookieValue = URLDecoder.decode(cookie.getValue(), "utf-8"); // 1=1;2=4;5=18;
                    log.info("cookieValue = {}", cookieValue);
                    if (Pattern.matches("^[1-9]\\d*=[1-9]\\d*;([1-9]\\d*=[1-9]\\d*;)*$", cookieValue)) {
                        String[] pairOfEquals = cookieValue.split(";"); // 1=1 2=4 5=18

                        String subStr = "";
                        boolean check = false;

                        for (int i = 0; i < pairOfEquals.length; i++) {
                            String[] pairOfProductIdxAndBuyCount = pairOfEquals[i].split("=");
                            if (pairOfProductIdxAndBuyCount[0].equals(addToCartForm.getProductIdx() + "")) {
                                check = true;
                                int currCount = Integer.parseInt(pairOfProductIdxAndBuyCount[1]);
                                pairOfProductIdxAndBuyCount[1] = (currCount + addToCartForm.getBuyCount()) + "";
                                subStr += (pairOfProductIdxAndBuyCount[0] + "=" + pairOfProductIdxAndBuyCount[1] + ";");
                            } else {
                                subStr += (pairOfProductIdxAndBuyCount[0] + "=" + pairOfProductIdxAndBuyCount[1] + ";");
                            }
                        }
                        if (check) {
                            Cookie newCookie = new Cookie("cart", URLEncoder.encode(subStr, "utf-8"));
                            newCookie.setPath("/");
                            httpServletResponse.addCookie(newCookie);
                            redirectAttributes.addFlashAttribute("cartMsg", "[비로그인1] 상품이 이미 장바구니에 담겨있어 개수가 추가되었습니다");
                            return "redirect:/product/detail?productIdx=" + addToCartForm.getProductIdx();
                        } else {
                            Cookie newCookie = new Cookie("cart",
                                    URLEncoder.encode(subStr + "" + addToCartForm.getProductIdx() + "=" + addToCartForm.getBuyCount() + ";", "utf-8"));
                            newCookie.setPath("/");
                            httpServletResponse.addCookie(newCookie);
                            redirectAttributes.addFlashAttribute("cartMsg", "[비로그인2] 상품을 장바구니에 추가했습니다");
                            return "redirect:/product/detail?productIdx=" + addToCartForm.getProductIdx();
                        }
                    } else {
                        // cart 라는 cookie 이름이 있는데 value가 이상할 경우
                        Cookie newCookie = new Cookie("cart",
                                URLEncoder.encode(addToCartForm.getProductIdx() + "=" + addToCartForm.getBuyCount() + ";", "utf-8"));
                        newCookie.setPath("/");
                        httpServletResponse.addCookie(newCookie);
                        redirectAttributes.addFlashAttribute("cartMsg", "[비로그인3] 상품을 장바구니에 추가했습니다");
                        return "redirect:/product/detail?productIdx=" + addToCartForm.getProductIdx();
                    }
                }
            } // outer cookies for ends
            Cookie newCookie = new Cookie("cart",
                    URLEncoder.encode(addToCartForm.getProductIdx() + "=" + addToCartForm.getBuyCount() + ";", "utf-8"));
            newCookie.setPath("/");
            httpServletResponse.addCookie(newCookie);
            redirectAttributes.addFlashAttribute("cartMsg", "[비로그인4] 상품을 장바구니에 추가했습니다");
            return "redirect:/product/detail?productIdx=" + addToCartForm.getProductIdx();
        } // else ends
    } // whole method ends

    @GetMapping(value = "/list")
    public String myCartList(Model model,
                             Authentication auth) {
        // security 에서 받아와야 함
        LoginUserDTO loginUserDTO = (LoginUserDTO) auth.getPrincipal();
        Integer customerIdx = loginUserDTO.getIdx();

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
                                Model model,
                                Authentication auth,
                                RedirectAttributes redirectAttributes) {
        Integer cartIdx = orderProductForm.getCartIdx();
        if (cartIdx == null) {
            log.warn("cartIdx is null");
            redirectAttributes.addFlashAttribute("orderProductErrorMsg", "잘못된 주문 요청입니다");
            return "redirect:/cart/list";
        }
        model.addAttribute("cartIdx", orderProductForm.getCartIdx());

        // security에서 받아와야 함
        LoginUserDTO loginUserDTO = (LoginUserDTO) auth.getPrincipal();
        Integer customerIdx = loginUserDTO.getIdx();

        CartDTO cart = cartService.getSpecificCart(customerIdx, cartIdx);

        if (cart == null) {
            log.warn("사용자의 장바구니가 아닙니다");
            redirectAttributes.addFlashAttribute("orderProductErrorMsg", "고객님의 장바구니가 아닙니다");
            return "redirect:/cart/list";
        }

        model.addAttribute("cart", cart);

        return "cart/orderPage";
    }

    @PostMapping(value = "/order")
    public String order(@Validated @ModelAttribute OrderProductForm orderProductForm,
                        BindingResult bindingResult,
                        Model model,
                        Authentication auth,
                        RedirectAttributes redirectAttributes) {
        log.info("orderProductForm = {}", orderProductForm);
        Integer cartIdx = orderProductForm.getCartIdx();
        model.addAttribute("cartIdx", cartIdx);

        LoginUserDTO loginUserDTO = (LoginUserDTO) auth.getPrincipal();
        Integer customerIdx = loginUserDTO.getIdx();

        CartDTO cart = cartService.getSpecificCart(customerIdx, cartIdx);

        if (cart == null) {
            log.warn("사용자의 장바구니가 아닙니다.");
            redirectAttributes.addFlashAttribute("orderProductErrorMsg", "고객님의 장바구니가 아닙니다");
            return "redirect:/cart/list";
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
            boolean orderDetailsInsertResult = orderDetailService.insertNewOrderDetails(orderProductForm.getOrderIdx(),
                    cart.getCartDetailDTOList());
            if (orderDetailsInsertResult) {
                sellerService.increaseSellerIncome(cart.getCartDetailDTOList(), cart.getSellerIdx());
                log.info("새로운 주문 및 주문 디테일 저장 완료");
                cartService.deleteCartAndCartDetail(cartIdx);
            } else {
                log.error("새로운 주문 및 주문 디테일 저장 실패");
            }
        } else {
            log.error("새로운 주문 저장 실패");
        }
        redirectAttributes.addFlashAttribute("orderSuccessMsg", "주문이 완료되었습니다");
        return "redirect:/order/product/list";
    }
}
