package kr.co.mapper_interface.notice;

import kr.co.chunjaeshop.notice.dto.NoticeDTO;

import java.util.List;
import java.util.Map;

public interface NoticeMapper {

    // 남원우


    // 최경락


    // 이무현
    int noticeSave(NoticeDTO noticeDTO);

    List<NoticeDTO> noticeAllList();

    NoticeDTO findByIdx(Integer idx);

    void delete(Integer idx);

    void update(NoticeDTO noticeDTO);

    int noticeCount();

    List<NoticeDTO> noticePagingList(Map<String, Integer> noticePagingParams);

    List<NoticeDTO> noticeSearchList(Map<String, Object> noticeSearchParams);

    int noticeSearchCount(Map<String, String> countParams);

    // 유지호


    // 변재혁

}
