package kr.co.chunjaeshop.product_comment.repository;


import kr.co.chunjaeshop.product_comment.dto.CommentDTO;
import kr.co.mapper_interface.product_comment.ProductCommentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
@Log4j2
public class CommentRepositoryImpl implements CommentRepository {

    private final ProductCommentMapper productCommentMapper;

    public int save(CommentDTO commentDTO) {
        return productCommentMapper.commentSave(commentDTO);
    }

    public List<CommentDTO> commentList() {
        return (List<CommentDTO>) productCommentMapper.commentList();
    }
}
