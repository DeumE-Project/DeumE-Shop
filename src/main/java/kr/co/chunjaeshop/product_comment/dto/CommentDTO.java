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

    public String getCommentCreatedTimeStr() {
        if (this.commentCreatedTime != null) {
            return this.commentCreatedTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } else {
            return "no date available";
        }
    }
}