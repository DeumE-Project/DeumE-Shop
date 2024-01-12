package kr.co.chunjaeshop.product_review.dto;

import kr.co.chunjaeshop.product_review.vaild.ValidFile;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ProductReviewSaveDTO {


    @NotNull(message = "리뷰 별점은 필수 선택사항입니다")
    private Integer reviewStar;

    @NotBlank(message = "리뷰 내용은 필수 입력값입니다")
    @Size(min=0, max = 500, message = "내용은 500자 이내로 입력")
    private String reviewContent;

    @ValidFile(message = "리뷰 사진은 필수 입니다.")
    private MultipartFile reviewImg;

  }
