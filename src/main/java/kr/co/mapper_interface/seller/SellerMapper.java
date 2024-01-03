package kr.co.mapper_interface.seller;

import kr.co.chunjaeshop.seller.dto.SellerDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SellerMapper {

    // 남원우


    // 최경락


    // 이무현
    List<SellerDTO> getNotRecognizedList();

    void updateRecognize(@Param("i") int i,@Param("id") String id);

    // 유지호


    // 변재혁

}
