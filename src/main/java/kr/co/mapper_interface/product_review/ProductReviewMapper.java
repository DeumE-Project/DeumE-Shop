package kr.co.mapper_interface.product_review;

import kr.co.chunjaeshop.product_review.dto.ProductReviewDTO;

import java.util.List;

public interface ProductReviewMapper {
    // 남원우
    int reviewSave (ProductReviewDTO productReviewDTO);

    List<ProductReviewDTO> reviewList();

    ProductReviewDTO findByIdx(String reviewIdx);

    int update(ProductReviewDTO productReviewDTO);

    void delete(String reviewIdx);

    // 최경락


    // 이무현


    // 유지호


    // 변재혁

}
