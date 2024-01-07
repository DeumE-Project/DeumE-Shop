package kr.co.chunjaeshop.seller.service;

import kr.co.chunjaeshop.admin.dto.NotRecognizePageDTO;
import kr.co.chunjaeshop.seller.dto.SellerDTO;

import java.util.List;

public interface SellerService {


    // 남원우


    // 최경락


    // 이무현
    List<SellerDTO> getNotRecognizedList();

    void updateRecognize(int i, String id);

    NotRecognizePageDTO notRecognizedSellerPagingParam(int page);

    List<SellerDTO> getNotRecognizedSellerList(int page);

    // 유지호


    // 변재혁

}
