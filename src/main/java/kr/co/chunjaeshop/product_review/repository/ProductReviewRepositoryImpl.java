package kr.co.chunjaeshop.product_review.repository;

import kr.co.chunjaeshop.product_review.dto.ProductReviewDTO;
import kr.co.mapper_interface.customer.CustomerMapper;
import kr.co.mapper_interface.product_review.ProductReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<ProductReviewDTO> reviewList() {
     return productReviewMapper.reviewList();
   }
  // 최경락


    // 이무현


    // 유지호


    // 변재혁

}
