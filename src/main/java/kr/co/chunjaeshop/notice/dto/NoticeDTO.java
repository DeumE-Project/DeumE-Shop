package kr.co.chunjaeshop.notice.dto;

import lombok.Data;
import org.checkerframework.checker.units.qual.min;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class NoticeDTO {
    private Integer noticeIdx;

    @NotBlank(message = "제목을 입력하세요.")
    @Size(min=0, max = 60, message = "제목은 60자 이내로 입력")
    private String noticeTitle;

    @NotBlank(message = "내용을 입력하세요.")
    @Size(min=0, max = 60, message = "내용은 500자 이내로 입력")
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
