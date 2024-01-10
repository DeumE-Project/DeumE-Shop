package kr.co.chunjaeshop.seller.repository;

import kr.co.chunjaeshop.seller.dto.SellerDTO;

import java.util.List;
import java.util.Map;

public interface SellerRepository {


    // 남원우


    // 최경락


    // 이무현
    List<SellerDTO> getNotRecognizedList();

    void updateRecognize(int i, String id);

    int notRecognizeCount();

    List<SellerDTO> getNotRecognizedSellerList(Map<String, Integer> notRecognizedSellerPagingParam);

    void insertRejectReason(Map<String, Object> reason);

    // 유지호


    // 변재혁

}
