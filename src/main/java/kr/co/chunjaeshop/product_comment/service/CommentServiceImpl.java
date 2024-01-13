package kr.co.chunjaeshop.product_comment.service;


import kr.co.chunjaeshop.product_comment.dto.CommentDTO;
import kr.co.chunjaeshop.product_comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Log4j2
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;


    @Override
    public int save(CommentDTO commentDTO) {
       return commentRepository.save(commentDTO);
    }

    @Override
    public List<CommentDTO> commentList() {
        return commentRepository.commentList();
    }
}
