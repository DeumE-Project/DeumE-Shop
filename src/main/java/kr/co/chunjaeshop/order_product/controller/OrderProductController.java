package kr.co.chunjaeshop.order_product.controller;

import kr.co.chunjaeshop.order_product.dto.OrderProductDTO;
import kr.co.chunjaeshop.order_product.service.OrderProductService;
import kr.co.chunjaeshop.security.LoginUserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/order/product")
@RequiredArgsConstructor
@Log4j2
public class OrderProductController {
    private final OrderProductService orderProductService;

    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁
    @GetMapping(value = "/detail")
    public String orderDetailPage(@RequestParam Integer orderIdx,
                                  Model model,
                                  Authentication auth,
                                  RedirectAttributes redirectAttributes) {
        // security 에서 받아와야 하는 값
        LoginUserDTO loginUserDTO = (LoginUserDTO) auth.getPrincipal();
        Integer customerIdx = loginUserDTO.getIdx();

        OrderProductDTO orderProductDTO = orderProductService.getOrderProductWithOrderDetails(orderIdx, customerIdx);

        if (orderProductDTO == null) {
            log.error("사용자의 주문번호가 아닙니다");
            redirectAttributes.addFlashAttribute("orderDetailErrorMsg", "주문번호를 조회할 수 없습니다");
        }
        model.addAttribute("order", orderProductDTO);
        return "cart/orderDetail";
    }

    @GetMapping(value = "/list")
    public String orderList(Model model,
                            Authentication auth) {
        LoginUserDTO loginUserDTO = (LoginUserDTO) auth.getPrincipal();
        Integer customerIdx = loginUserDTO.getIdx();

        List<OrderProductDTO> orderProductList = orderProductService.selectOrderProductHistoryList(customerIdx);
        model.addAttribute("orderProductList", orderProductList);
        return "cart/orderHistoryList";
    }
}
