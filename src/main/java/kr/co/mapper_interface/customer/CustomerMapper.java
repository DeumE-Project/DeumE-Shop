package kr.co.mapper_interface.customer;

import kr.co.chunjaeshop.customer.dto.CustomerDTO;
import kr.co.chunjaeshop.security.RegisterFormDTO;

import java.util.List;

public interface CustomerMapper {
    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁
    List<CustomerDTO> getAllCustomerList();
    CustomerDTO getCustomerByUsernameForLogin(String username);
    int customerRegister(RegisterFormDTO registerFormDTO);
    int idDuplicationCheck(String id);
}
