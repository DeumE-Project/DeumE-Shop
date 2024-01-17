package kr.co.chunjaeshop.product_review.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class ProductReviewDTO {
        private Integer reviewIdx; // 기본키
        private Integer customerIdx; // 외래키
        private Integer productIdx; // 외래키
        private String reviewContent; // 리뷰 내용
        private Integer reviewStar; // 리뷰 별점 1 ~ 5
        private String reviewThumbSaved; // 리뷰 이미지 썸네일 서버에 저장된 파일명
        private String reviewImgOriginal; // 고객이 업로드한 이미지 원본 파일명
        private String reviewImgSaved; // 고객이 업로드한 이미지 서버에 저장된 파일명
        private LocalDateTime reviewDate; // 리뷰 일자

        public String getReviewDateStr() {
                if (this.reviewDate != null) {
                        return this.reviewDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                } else {
                        return "no date available";
                }
        }

        // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁
        private String customerId;
}
