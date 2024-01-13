package kr.co.mapper_interface.seller;

import kr.co.chunjaeshop.seller.dto.SellDashBoardDTO;
import kr.co.chunjaeshop.seller.dto.SellerDTO;
import org.apache.ibatis.annotations.Param;

import kr.co.chunjaeshop.security.LoginUserDTO;
import kr.co.chunjaeshop.security.RegisterFormDTO;

import java.util.List;

public interface SellerMapper {
    // 남원우


    // 최경락


    // 이무현


    // 유지호
    SellerDTO mySellerInfoByIdx(Integer sellerIdx);

    int getMyTotalRev(Integer sellerIdx);

    int getDateRev(@Param("sellerIdx") Integer sellerIdx, @Param("thisMonth") String thisMonth);

    /*int getDateRevLast(@Param("sellerIdx") Integer sellerIdx, @Param("lastMonth") String lastMonth);*/

    int avgRev(Integer sellerIdx);

    List<SellDashBoardDTO> monthlySalesList(Integer sellerIdx);

    List<SellDashBoardDTO> categorySalesList(Integer sellerIdx);

    // 변재혁
    int sellerRegister(RegisterFormDTO registerFormDTO);
    int idDuplicationCheck(String id);
    LoginUserDTO.UserVO loginSeller(String username);



}
