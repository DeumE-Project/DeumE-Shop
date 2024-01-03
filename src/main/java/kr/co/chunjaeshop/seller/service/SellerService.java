package kr.co.chunjaeshop.seller.service;

import kr.co.chunjaeshop.security.RegisterFormDTO;

public interface SellerService {

    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁
    boolean sellerRegister(RegisterFormDTO registerFormDTO);
    boolean idDuplicationCheck(String id);
}
