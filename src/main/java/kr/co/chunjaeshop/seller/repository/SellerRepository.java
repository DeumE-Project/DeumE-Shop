package kr.co.chunjaeshop.seller.repository;


import kr.co.chunjaeshop.seller.dto.SellDashBoardDTO;
import kr.co.chunjaeshop.seller.dto.SellerDTO;
import java.util.List;
import java.util.Map;

import kr.co.chunjaeshop.security.RegisterFormDTO;


public interface SellerRepository {


    // 남원우


    // 최경락


    // 이무현
    List<SellerDTO> getNotRecognizedList();

    void updateRecognize(int i, String id);

    int notRecognizeCount();

    List<SellerDTO> getNotRecognizedSellerList(Map<String, Integer> notRecognizedSellerPagingParam);

    void insertRejectReason(Map<String, Object> reason);

    List<SellerDTO> getRejectSellerList(Map<String, Integer> rejectSellerPagingParam);

    int rejectSellerCount();

    List<SellerDTO> getRecognizedSellerList(Map<String, Integer> recognizedSellerPagingParam);

    int recognizedSellerCount();

    SellerDTO getInfoBySellerId(String id);



    // 유지호
    SellerDTO mySellerInfoByIdx(Integer sellerIdx);

    int getMyTotalRev(Integer sellerIdx);

    int getDateRev(Integer sellerIdx, String thisMonth);


    int avgRev(Integer sellerIdx);

    List<SellDashBoardDTO> monthlySalesList(Integer sellerIdx);

    List<SellDashBoardDTO> categorySalesList(Integer sellerIdx);

    List<SellDashBoardDTO> bestSellCountList(Integer sellerIdx);

    List<SellDashBoardDTO> bestSellRevList(Integer sellerIdx);

    // 변재혁
    int sellerRegister(RegisterFormDTO registerFormDTO);
    int idDuplicationCheck(String id);
    int increaseSellerIncome(Integer money, Integer sellerIdx);
}
