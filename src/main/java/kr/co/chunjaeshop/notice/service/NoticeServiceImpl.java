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

    int pageLimit = 10; // 한 페이지 게시물 갯수
    int blockLimit = 5; // 한 블록 당 최대 페이지
    @Override
    public List<NoticeDTO> noticePagingList(int page) {
        int pageStart = (page-1)*pageLimit; // 해당 페이지의 시작 idx
        Map<String, Integer> noticePagingParams = new HashMap<>();
        noticePagingParams.put("start", pageStart);
        noticePagingParams.put("limit", pageLimit);
        List<NoticeDTO> noticePagingList = noticeRepository.noticePagingList(noticePagingParams);

        return noticePagingList;
    }

    public NoticePageDTO noticePagingParam(int page) {
        int noticeCount = noticeRepository.noticeCount(); // 공지사항 게시물 전체 갯수
        int maxPage = (int)(Math.ceil((double) noticeCount / pageLimit)); // 총 페이지 수
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1; //시작 페이지
        int endPage = startPage + blockLimit - 1;
        // 마지막 페이지 처리
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        NoticePageDTO noticePageDTO = new NoticePageDTO();
        noticePageDTO.setPage(page);
        noticePageDTO.setMaxPage(maxPage);
        noticePageDTO.setStartPage(startPage);
        noticePageDTO.setEndPage(endPage);
        noticePageDTO.setTotalPage(noticeCount);
        noticePageDTO.setPageLimit(pageLimit);
        return noticePageDTO;
    }

    @Override
    public List<NoticeDTO> noticeSearchList(int page, String searchField, String searchWord) {
        int pageStart = (page-1)*pageLimit;
        Map<String, Object> noticeSearchParams = new HashMap<>();
        noticeSearchParams.put("start", pageStart);
        noticeSearchParams.put("limit", pageLimit);
        noticeSearchParams.put("searchField", searchField);
        noticeSearchParams.put("searchWord", searchWord);
        List<NoticeDTO> noticeSearchList = noticeRepository.noticeSearchList(noticeSearchParams);

        return noticeSearchList;
    }

    @Override
    public NoticePageDTO noticeSearchParam(int page, String searchField, String searchWord) {
        Map<String, String> countParams = new HashMap<>();
        countParams.put("searchField", searchField);
        countParams.put("searchWord", searchWord);

        int noticeSearchCount = noticeRepository.noticeSearchCount(countParams);
        int maxPage = (int)(Math.ceil((double) noticeSearchCount / pageLimit));
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
        noticePageDTO.setTotalPage(noticeSearchCount);
        noticePageDTO.setPageLimit(pageLimit);
        return noticePageDTO;
    }

    // 유지호


    // 변재혁

}
