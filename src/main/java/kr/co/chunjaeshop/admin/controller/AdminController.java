package kr.co.chunjaeshop.admin.controller;

import kr.co.chunjaeshop.customer.dto.CustomerDTO;
import kr.co.chunjaeshop.customer.service.CustomerService;
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
    public String recognizeForm(Model model) {
        List<SellerDTO> sellerDTOList = sellerService.getNotRecognizedList();
        log.info(sellerDTOList);
        model.addAttribute("notRecognizedList",sellerDTOList);
        return "/admin/recognizeForm";
    }

    @GetMapping("/accept")
    public String accept(@RequestParam("id") String id) {
        sellerService.updateRecognize(1, id);
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
