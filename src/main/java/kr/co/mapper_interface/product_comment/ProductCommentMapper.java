package kr.co.mapper_interface.product_comment;

import kr.co.chunjaeshop.product_comment.dto.CommentDTO;
import kr.co.chunjaeshop.product_review.dto.ProductReviewDTO;

import java.util.List;
import java.util.Map;

public interface ProductCommentMapper {

    int commentSave (CommentDTO commentDTO);
    List<CommentDTO> commentList();
    int boardCount();
    List<CommentDTO> pagingList(Map<String, Integer> pagingParams);

}
