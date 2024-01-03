package kr.co.chunjaeshop.notice.controller;

import kr.co.chunjaeshop.notice.dto.NoticeDTO;
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

    @GetMapping("/")
    public String noticeAllList(Model model) {
        List<NoticeDTO> noticeDTOList = noticeService.noticeAllList();
        model.addAttribute("noticeList", noticeDTOList);
        return "/notice/noticeList";
    }
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
    public String findByIdx(@RequestParam("idx") Integer idx, Model model) {
        NoticeDTO noticeDTO = noticeService.findByIdx(idx);
        model.addAttribute("notice", noticeDTO);
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

    // 유지호


    // 변재혁

}
