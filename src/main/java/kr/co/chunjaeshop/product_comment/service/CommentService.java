package kr.co.chunjaeshop.product_comment.service;


import kr.co.chunjaeshop.product_comment.dto.CommentDTO;
import kr.co.chunjaeshop.product_comment.dto.CommentPageDTO;
import kr.co.chunjaeshop.product_review.dto.ProductReviewDTO;
import kr.co.chunjaeshop.product_review.dto.ProductReviewPageDTO;

import java.util.List;


public interface CommentService {

    public int save(CommentDTO commentDTO);

    public List<CommentDTO> commentList();

    List<CommentDTO> pagingList(int page);

    public CommentPageDTO pagingParam(int page);
}
