package kr.co.chunjaeshop.seller.repository;


import kr.co.chunjaeshop.seller.dto.SellDashBoardDTO;
import kr.co.chunjaeshop.seller.dto.SellerDTO;


import kr.co.chunjaeshop.security.RegisterFormDTO;

import java.util.List;

public interface SellerRepository {

    // 남원우


    // 최경락


    // 이무현


    // 유지호
    SellerDTO mySellerInfoByIdx(Integer sellerIdx);

    int getMyTotalRev(Integer sellerIdx);

    int getDateRev(Integer sellerIdx, String thisMonth);

    /*int getDateRevLast(Integer sellerIdx, String lastMonthString);*/

    int avgRev(Integer sellerIdx);

    List<SellDashBoardDTO> monthlySalesList(Integer sellerIdx);

    List<SellDashBoardDTO> categorySalesList(Integer sellerIdx);

    // 변재혁
    int sellerRegister(RegisterFormDTO registerFormDTO);
    int idDuplicationCheck(String id);



}
