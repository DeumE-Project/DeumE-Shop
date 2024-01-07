package kr.co.chunjaeshop.notice.service;

import kr.co.chunjaeshop.notice.dto.NoticeDTO;
import kr.co.chunjaeshop.notice.dto.NoticePageDTO;
import kr.co.chunjaeshop.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    // 남원우


    // 최경락


    // 이무현
    @Override
    public int noticeSave(NoticeDTO noticeDTO) {
        return noticeRepository.noticeSave(noticeDTO);
    }

    @Override
    public List<NoticeDTO> noticeAllList() {
        return noticeRepository.noticeAllList();
    }

    @Override
    public NoticeDTO findByIdx(Integer idx) {
        return noticeRepository.findByIdx(idx);
    }

    @Override
    public void delete(Integer idx) {
        noticeRepository.delete(idx);
    }

    @Override
    public void update(NoticeDTO noticeDTO) {
        noticeRepository.update(noticeDTO);
    }

    int pageLimit = 5;
    int blockLimit = 3;
    @Override
    public List<NoticeDTO> noticePagingList(int page) {
        int pageStart = (page-1)*pageLimit;
        Map<String, Integer> noticePagingParams = new HashMap<>();
        noticePagingParams.put("start", pageStart);
        noticePagingParams.put("limit", pageLimit);
        List<NoticeDTO> noticePagingList = noticeRepository.noticePagingList(noticePagingParams);

        return noticePagingList;
    }

    public NoticePageDTO noticePagingParam(int page) {
        int noticeCount = noticeRepository.noticeCount();
        int maxPage = (int)(Math.ceil((double) noticeCount / pageLimit));
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        NoticePageDTO noticePageDTO = new NoticePageDTO();
        noticePageDTO.setPage(page);
        noticePageDTO.setMaxPage(maxPage);
        noticePageDTO.setStartPage(startPage);
        noticePageDTO.setEndPage(endPage);
        return noticePageDTO;
    }

    // 유지호


    // 변재혁

}
