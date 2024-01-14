package kr.co.chunjaeshop.product_comment.controller;

import kr.co.chunjaeshop.product_comment.dto.CommentDTO;
import kr.co.chunjaeshop.product_comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/product/comment")
@Log4j2
public class CommentController {

    private final CommentService commentService;
 /*   @PostMapping("/save")
    public @ResponseBody List<CommentDTO> save(@ModelAttribute CommentDTO commentDTO) {
        System.out.println("commentDTO = " + commentDTO);
        commentService.save(commentDTO);
        // 해당 게시글에 작성된 댓글 리스트를 가져옴
        List<CommentDTO> commentDTOList = commentService.commentList();
        return commentDTOList;
    }*/
    @GetMapping("/list")
    public String commentList(HttpServletResponse httpServletResponse, Model model ) {
        List<CommentDTO> commentDTOList = commentService.commentList();
        log.info(commentDTOList);
        model.addAttribute("commentList", commentDTOList);

        return "comment/commentList";
    }
}