package kr.co.chunjaeshop.seller.repository;

import kr.co.chunjaeshop.seller.dto.SellerDTO;

import java.util.List;

public interface SellerRepository {


    // 남원우


    // 최경락


    // 이무현
    List<SellerDTO> getNotRecognizedList();

    void updateRecognize(int i, String id);

    // 유지호


    // 변재혁

}
