package kr.co.chunjaeshop.notice.service;

import kr.co.chunjaeshop.notice.dto.NoticeDTO;
import kr.co.chunjaeshop.notice.dto.NoticePageDTO;

import java.util.List;

public interface NoticeService {


    // 남원우


    // 최경락


    // 이무현
    int noticeSave(NoticeDTO noticeDTO);

    List<NoticeDTO> noticeAllList();

    NoticeDTO findByIdx(Integer idx);

    void delete(Integer idx);

    void update(NoticeDTO noticeDTO);

    List<NoticeDTO> noticePagingList(int page);

    NoticePageDTO noticePagingParam(int page);

    List<NoticeDTO> noticeSearchList(int page, String field, String word);

    NoticePageDTO noticeSearchParam(int page, String searchField, String searchWord);


    // 유지호


    // 변재혁

}
