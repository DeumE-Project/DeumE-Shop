package kr.co.chunjaeshop.seller.service;


import kr.co.chunjaeshop.product.dto.ProductDTO;
import kr.co.chunjaeshop.product.repository.ProductRepository;
import kr.co.chunjaeshop.seller.dto.SellerDTO;

import kr.co.chunjaeshop.security.RegisterFormDTO;

import kr.co.chunjaeshop.seller.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;

    private final ProductRepository productRepository;


    // 남원우


    // 최경락


    // 이무현


    // 유지호
    @Override
    public SellerDTO mySellerInfoByIdx(Integer sellerIdx) {
        SellerDTO sellerDTO = sellerRepository.mySellerInfoByIdx(sellerIdx);
        return sellerDTO;
    }

    @Override
    public int getMyTotalRev(Integer sellerIdx) {
        int myRev = sellerRepository.getMyTotalRev(sellerIdx);
        return myRev;
    }


    @Override
    public int getDateRev(Integer sellerIdx, String thisMonth) {
        LocalDate currentDate = LocalDate.now();

        // 현재 년도와 월 얻기
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        String yearMonthString = String.format("%04d-%02d", currentYear, currentMonth);

        int dateRev = sellerRepository.getDateRev(sellerIdx, yearMonthString);
        return dateRev;
    }

/*    @Override
    public int getDateRevLast(Integer sellerIdx, String lastMonth) {
        LocalDate currentDate = LocalDate.now();
        // 현재 년도와 월 얻기
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        // 이전 달 계산
        int lastM = currentMonth - 1;
        int lastYear = currentYear;
        if (lastM == 0) {
            lastM = 12;
            lastYear--;
        }

        String lastMonthString = String.format("%04d-%02d", lastYear, lastM);

        int lastDateRev = sellerRepository.getDateRevLast(sellerIdx, lastMonthString);
        return lastDateRev;
    }*/

    @Override
    public int avgRev(Integer sellerIdx) {
        int avgRev = sellerRepository.avgRev(sellerIdx);
        return avgRev;
    }

    @Override
    public List<ProductDTO> myProduct(Integer sellerIdx) {
        List<ProductDTO> productDTOList = productRepository.myProduct(sellerIdx);
        return productDTOList;
    }


    // 변재혁
    @Override
    public boolean sellerRegister(RegisterFormDTO registerFormDTO) {
        int result = sellerRepository.sellerRegister(registerFormDTO);
        return (result == 1) ? true : false;
    }

    @Override
    public boolean idDuplicationCheck(String id) {
        int result = sellerRepository.idDuplicationCheck(id);
        return (result == 1) ? true : false;
    }

}

