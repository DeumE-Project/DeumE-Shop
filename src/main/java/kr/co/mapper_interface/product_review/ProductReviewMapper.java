package kr.co.mapper_interface.product_review;

import kr.co.chunjaeshop.product_review.dto.ProductReviewDTO;
import kr.co.chunjaeshop.product_review.dto.ProductReviewSaveDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Map;

public interface ProductReviewMapper {
    // 남원우
    int reviewSave (ProductReviewDTO productReviewDTO);

    List<ProductReviewDTO> reviewList();

    ProductReviewDTO findByIdx(String reviewIdx);

    ProductReviewSaveDTO findByIdxReviewSaveDTO(@Param("customerIdx") Integer customerIdx, @Param("reviewIdx") String reviewIdx);
    int update(ProductReviewDTO productReviewDTO);

    void delete(String reviewIdx);

    int boardCount(Integer productIdx);

    List<ProductReviewDTO> pagingList(Map<String, Integer> pagingParams);



    // 최경락


    // 이무현


    // 유지호


    // 변재혁
    int checkIfCustomerHasReviewIdx(@Param("customerIdx") Integer customerIdx,
                                    @Param("reviewIdx") Integer reviewIdx);

}
