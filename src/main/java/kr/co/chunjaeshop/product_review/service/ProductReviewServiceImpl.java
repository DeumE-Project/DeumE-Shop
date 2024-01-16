package kr.co.chunjaeshop.product_review.service;

import kr.co.chunjaeshop.product_review.dto.ProductReviewDTO;
import kr.co.chunjaeshop.product_review.dto.ProductReviewPageDTO;
import kr.co.chunjaeshop.product_review.dto.ProductReviewSaveDTO;
import kr.co.chunjaeshop.product_review.repository.ProductReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductReviewServiceImpl implements ProductReviewService {

  private final ProductReviewRepository productReviewRepository;
  int pageLimit = 5; // 한 페이지당 보여줄 글 갯수
  int blockLimit = 3; // 하단에 보여줄 페이지 번호 갯수


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
  public ProductReviewSaveDTO findByIdxReviewSaveDTO(String reviewIdx) {
    return productReviewRepository.findByIdxReviewSaveDTO(reviewIdx);
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


  @Override
  public List<ProductReviewDTO> pagingList(int page) {
        /*
        1페이지당 보여지는 글 갯수 3
            1page => 0
            2page => 3
            3page => 6
         */
    int pagingStart = (page - 1) * pageLimit;
    Map<String, Integer> pagingParams = new HashMap<>();
    pagingParams.put("start", pagingStart);
    pagingParams.put("limit", pageLimit);
    List<ProductReviewDTO> pagingList = productReviewRepository.pagingList(pagingParams);

    return pagingList;
  }

  @Override
  public ProductReviewPageDTO pagingParam(int page) {
    // 전체 글 갯수 조회
    int boardCount = productReviewRepository.boardCount();
    // 전체 페이지 갯수 계산(10/3=3.33333 => 4)
    int maxPage = (int) (Math.ceil((double) boardCount / pageLimit));
    // 시작 페이지 값 계산(1, 4, 7, 10, ~~~~)
    int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
    // 끝 페이지 값 계산(3, 6, 9, 12, ~~~~)
    int endPage = startPage + blockLimit - 1;
    if (endPage > maxPage) {
      endPage = maxPage;
    }
    ProductReviewPageDTO pageDTO = new ProductReviewPageDTO();
    pageDTO.setPage(page);
    pageDTO.setMaxPage(maxPage);
    pageDTO.setStartPage(startPage);
    pageDTO.setEndPage(endPage);
    return pageDTO;
  }

    // 최경락


    // 이무현


    // 유지호


    // 변재혁

}
