package kr.co.mapper_interface.seller;

import kr.co.chunjaeshop.seller.dto.SellDashBoardDTO;
import kr.co.chunjaeshop.seller.dto.SellerDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import kr.co.chunjaeshop.security.LoginUserDTO;
import kr.co.chunjaeshop.security.RegisterFormDTO;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface SellerMapper {

    // 남원우


    // 최경락


    // 이무현
    List<SellerDTO> getNotRecognizedList();

    void updateRecognize(@Param("i") int i,@Param("id") String id);

    int notRecognizeCount();

    List<SellerDTO> getNotRecognizedSellerList(Map<String, Integer> notRecognizedSellerPagingParam);

    void insertRejectReason(Map<String, Object> reason);

    List<SellerDTO> getRejectSellerList(Map<String, Integer> rejectSellerPagingParam);

    int rejectSellerCount();

    SellerDTO getInfoBySellerId(String id);


    // 유지호
    SellerDTO mySellerInfoByIdx(Integer sellerIdx);

    int getMyTotalRev(Integer sellerIdx);

    int getDateRev(@Param("sellerIdx") Integer sellerIdx, @Param("thisMonth") String thisMonth);

    /*int getDateRevLast(@Param("sellerIdx") Integer sellerIdx, @Param("lastMonth") String lastMonth);*/

    int avgRev(Integer sellerIdx);

    List<SellDashBoardDTO> monthlySalesList(Integer sellerIdx);

    List<SellDashBoardDTO> categorySalesList(Integer sellerIdx);

    List<SellDashBoardDTO> bestSellCountList(Integer sellerIdx);

    List<SellDashBoardDTO> bestSellRevList(Integer sellerIdx);

    // 변재혁
    int sellerRegister(RegisterFormDTO registerFormDTO);
    int idDuplicationCheck(String id);
    LoginUserDTO.UserVO loginSeller(String username);
    List<SellerDTO> getRecognizedSellerList(Map<String, Integer> recognizedSellerPagingParam);
    int recognizedSellerCount();
    int increaseSellerIncome(@Param("money") Integer money,
                             @Param("sellerIdx") Integer sellerIdx);
}
