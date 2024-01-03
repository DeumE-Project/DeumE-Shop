package kr.co.chunjaeshop.customer.repository;

import kr.co.chunjaeshop.customer.dto.CustomerDTO;
import kr.co.chunjaeshop.security.RegisterFormDTO;

import java.util.List;

public interface CustomerRepository {

    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁
    List<CustomerDTO> getAllCustomerList();
    int customerRegister(RegisterFormDTO registerFormDTO);
}
