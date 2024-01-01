package kr.co.chunjaeshop.customer.controller;

import kr.co.chunjaeshop.customer.dto.CustomerDTO;
import kr.co.chunjaeshop.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
@Log4j2
public class CustomerController {
    private final CustomerService customerService;

    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁
    @GetMapping(value = "/test")
    public void test(HttpServletResponse httpServletResponse) {
        log.info("test!!");
    }

    @GetMapping(value = "/all-customer-list")
    public void getAllCustomerList(HttpServletResponse httpServletResponse) {
        List<CustomerDTO> allCustomerList = customerService.getAllCustomerList();
        log.info(allCustomerList);
    }
}
