package kr.co.chunjaeshop.customer.repository;

import kr.co.chunjaeshop.customer.dto.CustomerDTO;
import kr.co.chunjaeshop.security.RegisterFormDTO;
import kr.co.mapper_interface.customer.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Log4j2
public class CustomerRepositoryImpl implements CustomerRepository {
    private final CustomerMapper customerMapper;

    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁
    @Override
    public List<CustomerDTO> getAllCustomerList() {
        return customerMapper.getAllCustomerList();
    }

    @Override
    public int customerRegister(RegisterFormDTO registerFormDTO) {
        return customerMapper.customerRegister(registerFormDTO);
    }
}
