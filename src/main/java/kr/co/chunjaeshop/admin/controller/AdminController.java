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

    @GetMapping("/recognize")
    public String recognizeForm(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                Model model) {
        List<SellerDTO> notRecognizedSellerList = sellerService.getNotRecognizedSellerList(page);
        NotRecognizePageDTO notRecognizePageDTO = sellerService.notRecognizedSellerPagingParam(page);
        model.addAttribute("notRecognizedList", notRecognizedSellerList);
        model.addAttribute("paging", notRecognizePageDTO);
        return "/admin/recognizeForm";
    }
    @GetMapping("/change")
    public String change(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                         Model model) {
        List<SellerDTO> rejectSellerList = sellerService.getRejectSellerList(page);
        NotRecognizePageDTO rejectSellerPageDTO = sellerService.rejectSellerPagingParam(page);

        model.addAttribute("rejectSellerList", rejectSellerList);
        model.addAttribute("paging", rejectSellerPageDTO);
        return "/admin/rejectSellerList";
    }

    @GetMapping("/accept")
    public String accept(@RequestParam("id") String id,
                         @RequestParam("type") int type) {
        sellerService.updateRecognize(1, id);
        if (type == 1) {
            return "redirect:/admin/recognize";
        } else {
            return "redirect:/admin/change";
        }
    }
    @GetMapping("/reject")
    public String reject(@RequestParam("id") String id,
                         @RequestParam("rejectReason") String reason) {
        log.info(id);
        log.info(reason);
        sellerService.updateRecognize(2, id);
        sellerService.insertRejectReason(reason,id);
        return "redirect:/admin/recognize";
    }

    // 유지호


    // 변재혁
}
