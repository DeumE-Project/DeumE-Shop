package kr.co.chunjaeshop.product_comment.service;


import kr.co.chunjaeshop.product_comment.dto.CommentDTO;

import java.util.List;


public interface CommentService {

    public int save(CommentDTO commentDTO);

    public List<CommentDTO> commentList();
}
