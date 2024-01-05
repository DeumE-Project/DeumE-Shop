package kr.co.chunjaeshop.customer.service;

import kr.co.chunjaeshop.cart.dto.CartDTO;
import kr.co.chunjaeshop.cart.repository.CartRepository;
import kr.co.chunjaeshop.customer.dto.AddToCartForm;
import kr.co.chunjaeshop.customer.dto.CartResult;
import kr.co.chunjaeshop.customer.dto.CustomerDTO;
import kr.co.chunjaeshop.customer.repository.CustomerRepository;
import kr.co.chunjaeshop.product.repository.ProductRepository;
import kr.co.chunjaeshop.security.LoginUserDTO;
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

    @Override
    public CartResult addToCart(AddToCartForm addToCartForm) {
        // 이미 카트에 해당 상품이 저장됐는지 체크
        Integer customerIdx = 8;
        CartDTO cartInformation = cartRepo.getCartInformation(customerIdx);
        if (cartInformation == null) {
            // 카트에 담겨있지 않다면,
        } else {
            // 카트에 담겨있다면
        }
        int productPrice = pRepo.getProductPrice(addToCartForm.getProductIdx());
        return null;
    }
}
