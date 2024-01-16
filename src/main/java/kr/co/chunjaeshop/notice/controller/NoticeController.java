package kr.co.chunjaeshop.notice.controller;

import kr.co.chunjaeshop.notice.dto.NoticeDTO;
import kr.co.chunjaeshop.notice.dto.NoticePageDTO;
import kr.co.chunjaeshop.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
@Log4j2
public class NoticeController {

    // 남원우


    // 최경락


    // 이무현
    private final NoticeService noticeService;

    /* 페이징 처리 전
    @GetMapping("/")
    public String noticeAllList(Model model) {
        List<NoticeDTO> noticeDTOList = noticeService.noticeAllList();
        model.addAttribute("noticeList", noticeDTOList);
        return "/notice/noticeList";
    }
    */

    @GetMapping("/save") // 공지사항 글작성 폼 가기
    public String saveForm(@ModelAttribute NoticeDTO noticeDTO){
        return "/notice/noticeSaveForm";
    }
    @PostMapping("/save") // 공지사항 글작성
    public String save(@Validated @ModelAttribute NoticeDTO noticeDTO,
                       BindingResult bindingResult,
                       Model model) {

        // 유효성 검사를 통과하지 못했을 때 처리
        if (bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            model.addAttribute("error", fieldError.getDefaultMessage());
            return "/notice/noticeSaveForm";
        }

        // 공지사항 글작성 처리
        int saveResult = noticeService.noticeSave(noticeDTO);
        if (saveResult > 0) {
            return "redirect:/notice/";
        } else {
            return "/notice/noticeSaveForm";
        }
    }

    @GetMapping // 공지사항 특정 글 상세보기
    public String findByIdx(@RequestParam("idx") Integer idx,
                            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                            Model model) {
        // 쿼리로 받은 idx로 해당 글 정보 받아오기
        NoticeDTO noticeDTO = noticeService.findByIdx(idx);
        model.addAttribute("notice", noticeDTO);
        model.addAttribute("page", page);
        return "/notice/noticeDetail";
    }

    @GetMapping("/delete") // 공지사항 특정 글 삭제
    public String delete(@RequestParam("idx") Integer idx) {
        noticeService.delete(idx);

        // 삭제 후 공지사항 첫 페이지로 돌아가기
        return "redirect:/notice/";
    }

    @GetMapping("/update") // 공지사항 수정 폼
    public String updateForm(@RequestParam("idx") Integer idx,
                             Model model) {
        // 쿼리로 받은 idx로 해당 글 정보 받아오기, 수정 전 내용을 뿌려주기 위함
        NoticeDTO noticeDTO = noticeService.findByIdx(idx);
        model.addAttribute("noticeDTO", noticeDTO);
        return "/notice/noticeUpdateForm";
    }

    @PostMapping("/update") // 공지사항 수정
    public String update(@Validated @ModelAttribute NoticeDTO noticeDTO,
                         BindingResult bindingResult,
                         Model model) {

        // 유효성 검사를 통과하지 못했을 때 처리
        if (bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            model.addAttribute("error", fieldError.getDefaultMessage());
            return "/notice/noticeUpdateForm";
        }
        // 공지사항 수정 처리
        noticeService.update(noticeDTO);

        // 수정 후 해당 글 상세보기로 돌아가기
        return "redirect:/notice?idx="+noticeDTO.getNoticeIdx();
    }

    @GetMapping("/") // 공지사항 목록
    public String paging(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                         Model model) {
        // 첫 화면은 default로 1페이지
        // 현재 페이지에 대한 글 불러오기
        List<NoticeDTO> noticePagingList = noticeService.noticePagingList(page);
        // 현재 페이지에 대한 정보 불러오기
        NoticePageDTO noticePageDTO = noticeService.noticePagingParam(page);
        model.addAttribute("noticeList", noticePagingList);
        model.addAttribute("paging", noticePageDTO);
        return "/notice/noticeList";
    }
    @GetMapping("/search") // 공지사항 검색
    public String search(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                         @RequestParam("searchField") String searchField,
                         @RequestParam("searchWord") String searchWord,
                         Model model) {
        // 검색어와 검색할 필드를 받고 해당하는 글 불러오기
        List<NoticeDTO> noticeSearchList = noticeService.noticeSearchList(page,searchField,searchWord);
        // 검색 후 해당하는 글에 대한 페이징 정보 불러오기
        NoticePageDTO noticeSearchPageDTO = noticeService.noticeSearchParam(page,searchField,searchWord);

        model.addAttribute("noticeList", noticeSearchList);
        model.addAttribute("paging", noticeSearchPageDTO);
        model.addAttribute("searchField", searchField);
        model.addAttribute("searchWord", searchWord);
        return "/notice/noticeList";
    }

    // 유지호


    // 변재혁

}
