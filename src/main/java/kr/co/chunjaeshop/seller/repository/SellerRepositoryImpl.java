package kr.co.chunjaeshop.seller.repository;

import kr.co.chunjaeshop.seller.dto.SellerDTO;
import kr.co.mapper_interface.seller.SellerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Log4j2
public class SellerRepositoryImpl implements SellerRepository {

    // 남원우


    // 최경락


    // 이무현
    private final SellerMapper sellerMapper;
    @Override
    public List<SellerDTO> getNotRecognizedList() {
        return sellerMapper.getNotRecognizedList();
    }

    @Override
    public void updateRecognize(int i, String id) {
        sellerMapper.updateRecognize(i,id);
    }


    // 유지호


    // 변재혁

}
