package kr.co.chunjaeshop.customer.service;

import kr.co.chunjaeshop.cart.repository.CartRepository;
import kr.co.chunjaeshop.customer.dto.CustomerDTO;
import kr.co.chunjaeshop.customer.repository.CustomerRepository;
import kr.co.chunjaeshop.product.repository.ProductRepository;
import kr.co.chunjaeshop.security.RegisterFormDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁
    private final ProductRepository pRepo;
    private final CartRepository cartRepo;

    @Override
    public List<CustomerDTO> getAllCustomerList() {
        return customerRepository.getAllCustomerList();
    }

    @Override
    public boolean customerRegister(RegisterFormDTO registerFormDTO) {
        int result = customerRepository.customerRegister(registerFormDTO);
        return (result == 1) ? true : false;
    }

    @Override
    public boolean idDuplicationCheck(String id) {
        int result = customerRepository.idDuplicationCheck(id);
        return (result == 1) ? true : false;
    }
}
