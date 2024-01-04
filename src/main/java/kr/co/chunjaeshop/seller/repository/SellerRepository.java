package kr.co.chunjaeshop.seller.repository;

import kr.co.chunjaeshop.security.RegisterFormDTO;

public interface SellerRepository {

    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁
    int sellerRegister(RegisterFormDTO registerFormDTO);
    int idDuplicationCheck(String id);
}
