package kr.co.chunjaeshop.product_comment.repository;


import kr.co.chunjaeshop.product_comment.dto.CommentDTO;

import java.util.List;



public interface CommentRepository {


    public int save(CommentDTO commentDTO) ;

    public List<CommentDTO> commentList() ;
}
