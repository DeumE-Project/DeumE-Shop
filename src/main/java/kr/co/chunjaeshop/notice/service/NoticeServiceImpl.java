package kr.co.chunjaeshop.notice.service;

import kr.co.chunjaeshop.notice.dto.NoticeDTO;
import kr.co.chunjaeshop.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

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

    // 유지호


    // 변재혁

}
