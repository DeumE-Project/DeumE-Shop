package kr.co.chunjaeshop.product_review.service;

import kr.co.chunjaeshop.product_review.dto.ProductReviewDTO;
import kr.co.chunjaeshop.product_review.repository.ProductReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductReviewServiceImpl implements ProductReviewService {

  private final ProductReviewRepository productReviewRepository;



  // 남원우

    @Override
    public int reviewSave(ProductReviewDTO productReviewDTO) {
      return productReviewRepository.reviewSave(productReviewDTO);

    }
    @Override
    public List<ProductReviewDTO> reviewList() {
      return productReviewRepository.reviewList();
    }
    @Override
    public ProductReviewDTO findByIdx(String reviewIdx) {
     return productReviewRepository.findByIdx(reviewIdx);
    }

  @Override
  public boolean update(ProductReviewDTO productReviewDTO) {
    int result = productReviewRepository.update(productReviewDTO);
    if (result>0){
      return true;
    }else {
      return false;
    }
  }

  @Override
  public void delete(String reviewIdx) {
    productReviewRepository.delete(reviewIdx);
  }
  // 최경락


    // 이무현


    // 유지호


    // 변재혁

}
