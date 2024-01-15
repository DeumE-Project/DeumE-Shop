package kr.co.mapper_interface.product_review;

import kr.co.chunjaeshop.product_review.dto.ProductReviewDTO;
import kr.co.chunjaeshop.product_review.dto.ProductReviewSaveDTO;

import java.util.List;
import java.util.Map;

public interface ProductReviewMapper {
    // 남원우
    int reviewSave (ProductReviewDTO productReviewDTO);

    List<ProductReviewDTO> reviewList();

    ProductReviewDTO findByIdx(String reviewIdx);

    ProductReviewSaveDTO findByIdxReviewSaveDTO(String reviewIdx);
    int update(ProductReviewDTO productReviewDTO);

    void delete(String reviewIdx);

    int boardCount();

    List<ProductReviewDTO> pagingList(Map<String, Integer> pagingParams);


    // 최경락


    // 이무현


    // 유지호


    // 변재혁

}
