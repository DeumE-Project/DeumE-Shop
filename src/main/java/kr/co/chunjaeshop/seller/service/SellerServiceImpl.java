package kr.co.chunjaeshop.seller.service;

import kr.co.chunjaeshop.seller.dto.SellerDTO;
import kr.co.chunjaeshop.seller.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class SellerServiceImpl implements SellerService {


    // 남원우


    // 최경락


    // 이무현
    private final SellerRepository sellerRepository;
    @Override
    public List<SellerDTO> getNotRecognizedList() {
        return sellerRepository.getNotRecognizedList();
    }

    @Override
    public void updateRecognize(int i, String id) {
        sellerRepository.updateRecognize(i,id);
    }

    // 유지호


    // 변재혁

}
