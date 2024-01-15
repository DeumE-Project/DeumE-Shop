package kr.co.chunjaeshop.product_review.repository;

import kr.co.chunjaeshop.product_review.dto.ProductReviewDTO;
import kr.co.chunjaeshop.product_review.dto.ProductReviewSaveDTO;
import kr.co.mapper_interface.customer.CustomerMapper;
import kr.co.mapper_interface.product_review.ProductReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Log4j2
public class ProductReviewRepositoryImpl implements ProductReviewRepository {

  private final ProductReviewMapper productReviewMapper;
    // 남원우
    @Override
    public int reviewSave(ProductReviewDTO productReviewDTO) {
      return productReviewMapper.reviewSave(productReviewDTO);
    }

    @Override
    public ProductReviewSaveDTO findByIdxReviewSaveDTO(String reviewIdx) {
        return productReviewMapper.findByIdxReviewSaveDTO(reviewIdx);
    }

    @Override
    public List<ProductReviewDTO> reviewList() {
     return productReviewMapper.reviewList();
   }

    @Override
    public ProductReviewDTO findByIdx(String reviewIdx) {
      return productReviewMapper.findByIdx(reviewIdx);
    }

    @Override
    public int update(ProductReviewDTO productReviewDTO) {
      return productReviewMapper.update(productReviewDTO);
    }

    @Override
    public void delete(String reviewIdx) {
      productReviewMapper.delete(reviewIdx);
    }

    @Override
    public List<ProductReviewDTO> pagingList(Map<String, Integer> pagingParams) {
    return productReviewMapper.pagingList(pagingParams);
  }

   @Override
    public int boardCount() {
    return productReviewMapper.boardCount();
  }

  // 최경락


    // 이무현


    // 유지호


    // 변재혁

}
