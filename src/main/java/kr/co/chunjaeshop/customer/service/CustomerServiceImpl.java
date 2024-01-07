package kr.co.chunjaeshop.customer.service;

import kr.co.chunjaeshop.cart.dto.CartDTO;
import kr.co.chunjaeshop.cart.dto.CartDetailDTO;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        // 해당 상품에 대한 판매자 idx 가져오기


        // 이미 카트에 해당 상품이 저장됐는지 체크
        CartDTO cartInformation = cartRepo.getCartInformation(addToCartForm.getCustomerIdx());
        if (cartInformation != null && cartInformation.getCartDetailDTOList().size() > 0) {
            List<CartDetailDTO> cartDetailDTOList = cartInformation.getCartDetailDTOList();
            CartDetailDTO cartDetailDTO = cartDetailDTOList
                    .stream()
                    .filter(dto -> dto.getProductIdx() == addToCartForm.getProductIdx())
                    .findFirst()
                    .orElse(null);
            if (cartDetailDTO != null) {
                // 카트에 이미 해당 상품이 담겨있으므로, cart_detail 에서 수량 업데이트
                // 필요한 것 => cart_idx, product_idx, buyCount
                Map<String, Object> parameterMap = new HashMap<>();
                parameterMap.put("cartIdx", cartInformation.getCartIdx());
                parameterMap.put("productIdx", addToCartForm.getProductIdx());
                parameterMap.put("buyCount", addToCartForm.getBuyCount());
                cartRepo.updateCartDetailProductInfo(parameterMap);
            } else {
                // 카드에 해당 상품이 없으므로, cart_detail 새롭게 담아주기
            }
        } else {
            // 카트가 존재하지도 않으므로, 카트 먼저 만들어주고, cart_detail 에 상품 담아주기
        }
        int productPrice = pRepo.getProductPrice(addToCartForm.getProductIdx());
        return null;
    }
}
