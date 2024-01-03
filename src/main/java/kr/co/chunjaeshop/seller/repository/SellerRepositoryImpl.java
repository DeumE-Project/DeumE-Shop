package kr.co.chunjaeshop.seller.repository;

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
