package kr.co.chunjaeshop.notice.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class NoticeDTO {
    private Integer noticeIdx;
    private String noticeTitle;
    private String noticeContent;
    private LocalDateTime noticeDate;

    public String getNoticeDateStr() {
        if (this.noticeDate != null) {
            return this.noticeDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            return "no date available";
        }
    }

    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁

}
