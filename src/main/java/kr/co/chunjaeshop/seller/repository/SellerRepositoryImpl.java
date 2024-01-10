package kr.co.chunjaeshop.seller.repository;

import kr.co.chunjaeshop.seller.dto.SellerDTO;
import kr.co.mapper_interface.seller.SellerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    @Override
    public int notRecognizeCount() {
        return sellerMapper.notRecognizeCount();
    }

    @Override
    public List<SellerDTO> getNotRecognizedSellerList(Map<String, Integer> notRecognizedSellerPagingParam) {
        return sellerMapper.getNotRecognizedSellerList(notRecognizedSellerPagingParam);
    }

    @Override
    public void insertRejectReason(Map<String, Object> reason) {
        sellerMapper.insertRejectReason(reason);
    }


    // 유지호


    // 변재혁

}
