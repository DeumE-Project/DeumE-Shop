package kr.co.chunjaeshop.notice.repository;

import kr.co.chunjaeshop.notice.dto.NoticeDTO;
import kr.co.mapper_interface.notice.NoticeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Log4j2
public class NoticeRepositoryImpl implements NoticeRepository {


    // 남원우


    // 최경락


    // 이무현
    private final NoticeMapper noticeMapper;

    @Override
    public int noticeSave(NoticeDTO noticeDTO) {
        return noticeMapper.noticeSave(noticeDTO);
    }

    @Override
    public List<NoticeDTO> noticeAllList() {
        return noticeMapper.noticeAllList();
    }

    @Override
    public NoticeDTO findByIdx(Integer idx) {
        return noticeMapper.findByIdx(idx);
    }

    @Override
    public void delete(Integer idx) {
        noticeMapper.delete(idx);
    }

    @Override
    public void update(NoticeDTO noticeDTO) {
        noticeMapper.update(noticeDTO);
    }

    @Override
    public int noticeCount() {
        return noticeMapper.noticeCount();
    }

    @Override
    public List<NoticeDTO> noticePagingList(Map<String, Integer> noticePagingParams) {
        return noticeMapper.noticePagingList(noticePagingParams);
    }

    @Override
    public List<NoticeDTO> noticeSearchList(Map<String, Object> noticeSearchParams) {
        return noticeMapper.noticeSearchList(noticeSearchParams);
    }

    @Override
    public int noticeSearchCount(Map<String, String> countParams) {
        return noticeMapper.noticeSearchCount(countParams);
    }

    // 유지호


    // 변재혁

}
