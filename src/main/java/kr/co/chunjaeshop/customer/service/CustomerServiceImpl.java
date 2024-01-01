package kr.co.chunjaeshop.customer.service;

import kr.co.chunjaeshop.customer.dto.CustomerDTO;
import kr.co.chunjaeshop.customer.repository.CustomerRepository;
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

    @Override
    public List<CustomerDTO> getAllCustomerList() {
        return customerRepository.getAllCustomerList();
    }
}
