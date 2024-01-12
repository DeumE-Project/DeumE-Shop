package kr.co.mapper_interface.seller;

import kr.co.chunjaeshop.seller.dto.SellerDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import kr.co.chunjaeshop.security.LoginUserDTO;
import kr.co.chunjaeshop.security.RegisterFormDTO;

public interface SellerMapper {

    // 남원우


    // 최경락


    // 이무현
    List<SellerDTO> getNotRecognizedList();

    void updateRecognize(@Param("i") int i,@Param("id") String id);

    int notRecognizeCount();

    List<SellerDTO> getNotRecognizedSellerList(Map<String, Integer> notRecognizedSellerPagingParam);

    void insertRejectReason(Map<String, Object> reason);

    // 유지호
    SellerDTO mySellerInfoByIdx(Integer sellerIdx);

    int getMyTotalRev(Integer sellerIdx);

    int getDateRev(@Param("sellerIdx") Integer sellerIdx, @Param("thisMonth") String thisMonth);

    /*int getDateRevLast(@Param("sellerIdx") Integer sellerIdx, @Param("lastMonth") String lastMonth);*/

    int avgRev(Integer sellerIdx);

    // 변재혁
    int sellerRegister(RegisterFormDTO registerFormDTO);
    int idDuplicationCheck(String id);
    LoginUserDTO.UserVO loginSeller(String username);

}
