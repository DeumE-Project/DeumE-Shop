package kr.co.chunjaeshop.product_comment.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class CommentDTO {

    private Integer commentIdx;
    private Integer productIdx;
    private String commentWriter;
    private String commentContents;
    private LocalDateTime commentCreatedTime;

    //날짜 포맷을 설정 하기 위한 메서드
    public String getCommentCreatedTimeStr() {
        if (this.commentCreatedTime != null) {
            return this.commentCreatedTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } else {
            return "no date available";
        }
    }
}