package kr.co.chunjaeshop.seller.repository;


import kr.co.chunjaeshop.seller.dto.SellerDTO;

import kr.co.chunjaeshop.security.RegisterFormDTO;

import kr.co.mapper_interface.seller.SellerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
@Log4j2
public class SellerRepositoryImpl implements SellerRepository {
    private final SellerMapper sellerMapper;

    // 남원우


    // 최경락


    // 이무현


    // 유지호
    @Override
    public SellerDTO mySellerInfoByIdx(Integer sellerIdx) {
        return sellerMapper.mySellerInfoByIdx(sellerIdx);
    }

    @Override
    public int getMyTotalRev(Integer sellerIdx) {
        return sellerMapper.getMyTotalRev(sellerIdx);
    }

    @Override
    public int getDateRev(Integer sellerIdx, String thisMonth) {
        return sellerMapper.getDateRev(sellerIdx, thisMonth);
    }

 /*   @Override
    public int getDateRevLast(Integer sellerIdx, String lastMonth) {
        return sellerMapper.getDateRevLast(sellerIdx, lastMonth);
    }*/

    @Override
    public int avgRev(Integer sellerIdx) {
        return sellerMapper.avgRev(sellerIdx);
    }

    // 변재혁
    @Override
    public int sellerRegister(RegisterFormDTO registerFormDTO) {
        return sellerMapper.sellerRegister(registerFormDTO);
    }

    @Override
    public int idDuplicationCheck(String id) {
        return sellerMapper.idDuplicationCheck(id);
    }
}



