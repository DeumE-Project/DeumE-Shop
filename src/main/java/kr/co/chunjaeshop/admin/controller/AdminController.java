package kr.co.chunjaeshop.admin.controller;

import kr.co.chunjaeshop.admin.dto.NotRecognizePageDTO;
import kr.co.chunjaeshop.customer.dto.CustomerDTO;
import kr.co.chunjaeshop.customer.service.CustomerService;
import kr.co.chunjaeshop.notice.dto.NoticeDTO;
import kr.co.chunjaeshop.notice.dto.NoticePageDTO;
import kr.co.chunjaeshop.seller.dto.SellerDTO;
import kr.co.chunjaeshop.seller.service.SellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Log4j2
public class AdminController {
    // 남원우


    // 최경락


    // 이무현
    private final SellerService sellerService;

    //페이징 처리 전
//    @GetMapping("/recognize")
//    public String recognizeForm(Model model) {
//        List<SellerDTO> sellerDTOList = sellerService.getNotRecognizedList();
//        log.info(sellerDTOList);
//        model.addAttribute("notRecognizedList",sellerDTOList);
//        return "/admin/recognizeForm";
//    }
    @GetMapping("/recognize")
    public String recognizeForm(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                Model model) {
        List<SellerDTO> notRecognizedSellerList = sellerService.getNotRecognizedSellerList(page);
        NotRecognizePageDTO notRecognizePageDTO = sellerService.notRecognizedSellerPagingParam(page);
        model.addAttribute("notRecognizedList", notRecognizedSellerList);
        model.addAttribute("paging", notRecognizePageDTO);
        return "/admin/recognizeForm";
    }

    @GetMapping("/accept")
    public String accept(@RequestParam("id") String id) {
        sellerService.updateRecognize(1, id);

        //승인 또는 거절 누른 후 해당 페이지에 남아있을지?
        //@RequestParam(value = "page", required = false, defaultValue = "1") int page
        //return "redirect:/admin/recognize?page=" + page;
        return "redirect:/admin/recognize";
    }
    @GetMapping("/reject")
    public String reject(@RequestParam("id") String id) {
        sellerService.updateRecognize(2, id);
        return "redirect:/admin/recognize";
    }


    // 유지호


    // 변재혁
}
