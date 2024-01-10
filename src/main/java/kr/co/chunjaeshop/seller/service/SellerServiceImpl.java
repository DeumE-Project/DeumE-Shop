package kr.co.chunjaeshop.seller.service;

import kr.co.chunjaeshop.admin.dto.NotRecognizePageDTO;
import kr.co.chunjaeshop.seller.dto.SellerDTO;
import kr.co.chunjaeshop.seller.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    int pageLimit = 3;
    int blockLimit = 3;
    @Override
    public List<SellerDTO> getNotRecognizedSellerList(int page) {
        int pageStart = (page-1)*pageLimit;
        Map<String, Integer> notRecognizedSellerPagingParam = new HashMap<>();
        notRecognizedSellerPagingParam.put("start", pageStart);
        notRecognizedSellerPagingParam.put("limit", pageLimit);
        List<SellerDTO> notRecognizedSellerList = sellerRepository.getNotRecognizedSellerList(notRecognizedSellerPagingParam);

        return notRecognizedSellerList;
    }

    @Override
    public void insertRejectReason(String reason, String id) {
        Map<String, Object> rejectParam = new HashMap<>();
        rejectParam.put("reason", reason);
        rejectParam.put("id", id);
        sellerRepository.insertRejectReason(rejectParam);
    }

    @Override
    public NotRecognizePageDTO notRecognizedSellerPagingParam(int page) {
        int notRecognizeCount = sellerRepository.notRecognizeCount();
        int maxPage = (int)(Math.ceil((double) notRecognizeCount / pageLimit));
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        NotRecognizePageDTO notRecognizePageDTO = new NotRecognizePageDTO();
        notRecognizePageDTO.setPage(page);
        notRecognizePageDTO.setMaxPage(maxPage);
        notRecognizePageDTO.setStartPage(startPage);
        notRecognizePageDTO.setEndPage(endPage);
        return notRecognizePageDTO;

    }



    // 유지호


    // 변재혁

}
