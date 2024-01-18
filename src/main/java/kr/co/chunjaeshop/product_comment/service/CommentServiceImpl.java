package kr.co.chunjaeshop.product_comment.service;


import kr.co.chunjaeshop.product_comment.dto.CommentDTO;
import kr.co.chunjaeshop.product_comment.dto.CommentPageDTO;
import kr.co.chunjaeshop.product_comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Log4j2
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    int pageLimit = 5; // 한 페이지당 보여줄 글 갯수
    int blockLimit = 3; // 하단에 보여줄 페이지 번호 갯수

    @Override
    public int save(CommentDTO commentDTO) {
       return commentRepository.save(commentDTO);
    }

    @Override
    public List<CommentDTO> commentList() {
        return commentRepository.commentList();
    }

    @Override
    public List<CommentDTO> pagingList(int page,Integer productIdx) {
        /*
        1페이지당 보여지는 글 갯수 3
            1page => 0
            2page => 3
            3page => 6
         */
        int pagingStart = (page - 1) * pageLimit;
        Map<String, Integer> pagingParams = new HashMap<>();
        pagingParams.put("start", pagingStart);
        pagingParams.put("limit", pageLimit);
        pagingParams.put("productIdx", productIdx);
        List<CommentDTO> pagingList = commentRepository.pagingList(pagingParams);

        return pagingList;
    }

    @Override
    public CommentPageDTO pagingParam(int page, Integer productIdx) {
        // 전체 글 갯수 조회
        int boardCount = commentRepository.boardCount(productIdx);
        // 전체 페이지 갯수 계산(10/3=3.33333 => 4)
        int maxPage = (int) (Math.ceil((double) boardCount / pageLimit));
        // 시작 페이지 값 계산(1, 4, 7, 10, ~~~~)
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        // 끝 페이지 값 계산(3, 6, 9, 12, ~~~~)
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        CommentPageDTO pageDTO = new CommentPageDTO();
        pageDTO.setPage(page);
        pageDTO.setMaxPage(maxPage);
        pageDTO.setStartPage(startPage);
        pageDTO.setEndPage(endPage);
        return pageDTO;
    }
}


