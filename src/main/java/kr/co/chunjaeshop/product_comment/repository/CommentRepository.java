package kr.co.chunjaeshop.product_comment.repository;


import kr.co.chunjaeshop.product_comment.dto.CommentDTO;
import kr.co.chunjaeshop.product_review.dto.ProductReviewDTO;

import java.util.List;
import java.util.Map;


public interface CommentRepository {


    public int save(CommentDTO commentDTO) ;

    public List<CommentDTO> commentList() ;

    int boardCount(Integer productIdx);
    List<CommentDTO> pagingList(Map<String, Integer> pagingParams);

}
