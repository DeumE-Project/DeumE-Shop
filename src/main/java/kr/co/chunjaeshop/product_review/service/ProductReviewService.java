package kr.co.chunjaeshop.product_review.service;

import kr.co.chunjaeshop.product_review.dto.ProductReviewDTO;
import kr.co.chunjaeshop.product_review.dto.ProductReviewPageDTO;
import kr.co.chunjaeshop.product_review.dto.ProductReviewSaveDTO;

import java.util.List;

public interface ProductReviewService {


    // 남원우

    public int reviewSave(ProductReviewDTO productReviewDTO);
    List<ProductReviewDTO> reviewList();
    ProductReviewDTO findByIdx(String reviewIdx);
    boolean update(ProductReviewDTO productReviewDTO);
    void delete(String reviewIdx);
    List<ProductReviewDTO> pagingList(int page, Integer productIdx);

    public ProductReviewPageDTO pagingParam(int page, Integer productIdx);

    ProductReviewSaveDTO findByIdxReviewSaveDTO(Integer customerIdx, String reviewIdx);


    // 최경락


    // 이무현


    // 유지호


    // 변재혁
    boolean checkIfCustomerHasReviewIdx(Integer customerIdx, Integer reviewIdx);

}
