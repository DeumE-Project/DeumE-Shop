package kr.co.chunjaeshop.customer.service;

import kr.co.chunjaeshop.customer.dto.AddToCartForm;
import kr.co.chunjaeshop.customer.dto.CartResult;
import kr.co.chunjaeshop.customer.dto.CustomerDTO;
import kr.co.chunjaeshop.security.LoginUserDTO;
import kr.co.chunjaeshop.security.RegisterFormDTO;

import java.util.List;

public interface CustomerService {

    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁
    List<CustomerDTO> getAllCustomerList();
    boolean customerRegister(RegisterFormDTO registerFormDTO);
    boolean idDuplicationCheck(String id);
    CartResult addToCart(AddToCartForm addToCartForm);
}
