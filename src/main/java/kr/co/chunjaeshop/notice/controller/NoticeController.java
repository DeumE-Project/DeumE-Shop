package kr.co.chunjaeshop.notice.controller;

import kr.co.chunjaeshop.notice.dto.NoticeDTO;
import kr.co.chunjaeshop.notice.dto.NoticePageDTO;
import kr.co.chunjaeshop.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    //페이징 처리 전
//    @GetMapping("/")
//    public String noticeAllList(Model model) {
//        List<NoticeDTO> noticeDTOList = noticeService.noticeAllList();
//        model.addAttribute("noticeList", noticeDTOList);
//        return "/notice/noticeList";
//    }
    @GetMapping("/save")
    public String saveForm(@ModelAttribute NoticeDTO noticeDTO){
        return "/notice/noticeSaveForm";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute NoticeDTO noticeDTO) {
        int saveResult = noticeService.noticeSave(noticeDTO);
        if (saveResult > 0) {
            return "redirect:/notice/";
        } else {
            return "/notice/noticeSaveForm";
        }
    }

    @GetMapping
    public String findByIdx(@RequestParam("idx") Integer idx,
                            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                            Model model) {
        NoticeDTO noticeDTO = noticeService.findByIdx(idx);
        model.addAttribute("notice", noticeDTO);
        model.addAttribute("page", page);
        return "/notice/noticeDetail";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("idx") Integer idx) {
        noticeService.delete(idx);
        return "redirect:/notice/";
    }

    @GetMapping("/update")
    public String updateForm(@RequestParam("idx") Integer idx,
                             Model model) {
        NoticeDTO noticeDTO = noticeService.findByIdx(idx);
        model.addAttribute("noticeDTO", noticeDTO);
        return "/notice/noticeUpdateForm";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute NoticeDTO noticeDTO, Model model) {
        noticeService.update(noticeDTO);
        return "redirect:/notice?idx="+noticeDTO.getNoticeIdx();
    }

    @GetMapping("/")
    public String paging(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                         Model model) {
//        System.out.println("page = " + page);
        List<NoticeDTO> noticePagingList = noticeService.noticePagingList(page);
//        System.out.println("pagingList = " + noticePagingList);
        NoticePageDTO noticePageDTO = noticeService.noticePagingParam(page);
        model.addAttribute("noticeList", noticePagingList);
        model.addAttribute("paging", noticePageDTO);
        return "/notice/noticeList";
    }
    @GetMapping("/search")
    public String search(
//            @RequestParam("page") int page,
                         @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                         @RequestParam("searchField") String searchField,
                         @RequestParam("searchWord") String searchWord,
                         Model model) {
        List<NoticeDTO> noticeSearchList = noticeService.noticeSearchList(page,searchField,searchWord);
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
