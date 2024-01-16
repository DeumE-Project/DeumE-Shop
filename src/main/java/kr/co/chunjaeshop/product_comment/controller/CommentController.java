package kr.co.chunjaeshop.product_comment.controller;

import kr.co.chunjaeshop.product_comment.dto.CommentDTO;
import kr.co.chunjaeshop.product_comment.dto.CommentPageDTO;
import kr.co.chunjaeshop.product_comment.dto.CommentSaveDTO;
import kr.co.chunjaeshop.product_comment.service.CommentService;
import kr.co.chunjaeshop.product_review.dto.ProductReviewDTO;
import kr.co.chunjaeshop.product_review.dto.ProductReviewPageDTO;
import kr.co.chunjaeshop.product_review.dto.ProductReviewSaveDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/product/comment")
@Log4j2
public class CommentController {

    private final CommentService commentService;
    @GetMapping("/save")
    public String saveForm(@ModelAttribute CommentSaveDTO commentSaveDTO){
        return "comment/commentSave";
    }

    @PostMapping("/save")
    public String save(@Validated @ModelAttribute CommentSaveDTO commentSaveDTO, BindingResult bindingResult , HttpServletRequest httpServletRequest, Model model) {

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();

            return "comment/commentSave";
        }

        CommentDTO commentDTO = new CommentDTO();

        //테스트용
        commentDTO.setProductIdx(2);
        commentDTO.setCommentWriter(commentSaveDTO.getCommentWriter());
        commentDTO.setCommentContents(commentSaveDTO.getCommentContents());

        log.info("commentDTO = {} " + commentDTO);

        int saveResult = commentService.save(commentDTO);
        log.info("saveResult = {} " + saveResult);

        if (saveResult > 0) {
            return "redirect:/product/comment/paging";  // 저장 성공 시 detail 페이지로 redirect
        } else {
            log.error("/리뷰 등록에 실패했습니다."); // 상품 등록 실패 메시지 로깅

            bindingResult.addError(new FieldError("commentSaveDTO", "",
                    "리뷰 등록에 실패했습니다. 다시 시도해주세요.")); // 실패 메시지 바인딩

            return "comment/commentSave"; // 저장 실패 시 save 페이지로 리디렉션
        }
    }
    @GetMapping("/list")
    public String commentList(HttpServletResponse httpServletResponse, Model model ) {
        List<CommentDTO> commentDTOList = commentService.commentList();
        log.info(commentDTOList);
        model.addAttribute("commentList", commentDTOList);

        return "comment/commentList";
    }

    @GetMapping("/paging")
    public String paging(Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        // BoardService를 사용하여 지정된 페이지의 게시글 목록을 가져옵니다.
        List<CommentDTO> pagingList = commentService.pagingList(page);
        // BoardService를 사용하여 페이징 정보를 가져옵니다.
        CommentPageDTO pageDTO = commentService.pagingParam(page);
        // 페이징된 목록 및 페이징 정보를 뷰에서 렌더링하기 위해 모델에 추가합니다.
        model.addAttribute("pagingList", pagingList);
        model.addAttribute("paging", pageDTO);
        // 뷰 이름을 반환합니다.
        return "comment/commentPaging";
    }
}