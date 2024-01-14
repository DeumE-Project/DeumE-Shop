package kr.co.mapper_interface.product_comment;

import kr.co.chunjaeshop.product_comment.dto.CommentDTO;

import java.util.List;

public interface ProductCommentMapper {

    int commentSave (CommentDTO commentDTO);
    List<CommentDTO> commentList();


}
