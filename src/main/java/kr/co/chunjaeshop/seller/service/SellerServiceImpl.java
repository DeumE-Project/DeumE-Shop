package kr.co.chunjaeshop.seller.service;

import kr.co.chunjaeshop.security.RegisterFormDTO;
import kr.co.chunjaeshop.seller.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;

    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁
    @Override
    public boolean sellerRegister(RegisterFormDTO registerFormDTO) {
        int result = sellerRepository.sellerRegister(registerFormDTO);
        return (result == 1) ? true : false;
    }

    @Override
    public boolean idDuplicationCheck(String id) {
        int result = sellerRepository.idDuplicationCheck(id);
        return (result == 1) ? true : false;
    }
}
