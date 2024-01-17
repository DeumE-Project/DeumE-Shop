package kr.co.chunjaeshop.product_review.repository;

import kr.co.chunjaeshop.product_review.dto.ProductReviewDTO;
import kr.co.chunjaeshop.product_review.dto.ProductReviewSaveDTO;

import java.util.List;
import java.util.Map;

public interface ProductReviewRepository {

    // 남원우
    public int reviewSave(ProductReviewDTO productReviewDTO);
    public List<ProductReviewDTO> reviewList();
    ProductReviewDTO findByIdx(String reviewIdx);
    int update(ProductReviewDTO productReviewDTO);
    void delete(String reviewIdx);

    int boardCount(Integer productIdx);
    List<ProductReviewDTO> pagingList(Map<String, Integer> pagingParams);
    ProductReviewSaveDTO findByIdxReviewSaveDTO(Integer customerIdx, String reviewIdx);


    // 최경락


    // 이무현


    // 유지호


    // 변재혁
    int checkIfCustomerHasReviewIdx(Integer customerIdx, Integer reviewIdx);

}
