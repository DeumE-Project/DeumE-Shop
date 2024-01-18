package kr.co.chunjaeshop.product_comment.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CommentSaveDTO {


    @NotBlank(message = "리뷰 내용은 필수 입력값입니다")
    @Size(min=0, max = 7, message = "작성자는 7자 이내로 입력")
    private String commentWriter;


    @NotBlank(message = "리뷰 내용은 필수 입력값입니다")
    @Size(min=0, max = 30, message = "내용은 30자 이내로 입력")
    private String commentContents;

    private Integer orderDetailIdx;
}