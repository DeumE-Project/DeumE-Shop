package kr.co.chunjaeshop.admin.controller;

import kr.co.chunjaeshop.admin.dto.AdminPageDTO;
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

    @GetMapping("/recognize") // 판매자 가입 승인 페이지
    public String recognizeForm(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                Model model) {
        // 아직 승인 또는 거절 되지 않은 판매자 정보 불러오기
        List<SellerDTO> notRecognizedSellerList = sellerService.getNotRecognizedSellerList(page);
        AdminPageDTO notRecognizePageDTO = sellerService.notRecognizedSellerPagingParam(page);

        model.addAttribute("notRecognizedList", notRecognizedSellerList);
        model.addAttribute("paging", notRecognizePageDTO);

        return "/admin/recognizeForm";
    }

    @GetMapping("/change") // 거절 또는 정지 당한 판매자 목록
    public String change(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                         Model model) {
        // 거절 또는 정지 당한 판매자 목록 불러오기
        List<SellerDTO> rejectSellerList = sellerService.getRejectSellerList(page);
        AdminPageDTO rejectSellerPageDTO = sellerService.rejectSellerPagingParam(page);

        model.addAttribute("rejectSellerList", rejectSellerList);
        model.addAttribute("paging", rejectSellerPageDTO);

        return "/admin/rejectSellerList";
    }

    @GetMapping("/manage") // 가입 승인된 판매자 목록
    public String manageSeller(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                               Model model) {
        // 가입 승인된 판매자 목록 불러오기
        List<SellerDTO> recognizedSellerList = sellerService.getRecognizedSellerList(page);
        AdminPageDTO recognizedSellerPageDTO = sellerService.recognizedSellerPagingParam(page);

        model.addAttribute("recognizedSellerList", recognizedSellerList);
        model.addAttribute("paging", recognizedSellerPageDTO);

        return "/admin/manageSellerList";
    }

    @GetMapping("/accept") // 판매자 승인
    public String accept(@RequestParam("id") String id,
                         @RequestParam("type") int type) {
        // 판매자 승인 상태로 변경
        sellerService.updateRecognize(1, id);

        // type : 1 = 가입 승인
        if (type == 1) {
            return "redirect:/admin/recognize";
        }
        // type : 2 = 거절 됬거나 정지 당한 판매자 승인 상태로 변경
        else {
            return "redirect:/admin/change";
        }
    }

    @GetMapping("/reject") // 판매자 거절
    public String reject(@RequestParam("id") String id,
                         @RequestParam("rejectReason") String reason,
                         @RequestParam("type") int type) {
        // 판매자 거절 또는 정지 상태로 변경
        sellerService.updateRecognize(2, id);
        // 거절 또는 정지 이유 저장
        sellerService.insertRejectReason(reason, id);

        // type : 1 = 가입 거절
        if (type == 1) {
            return "redirect:/admin/recognize";
        }
        // type : 2 = 판매자 정지
        else {
            return "redirect:/admin/manage";
        }

    }

    @GetMapping("/info") // 승인된 판매자의 상세 정보
    public String info(@RequestParam("id") String id,
                       @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                       Model model) {
        // 쿼리로 넘겨받은 id에 해당하는 판매자 정보 불러오기
        SellerDTO sellerDTO = sellerService.getInfoBySellerId(id);

        model.addAttribute("seller", sellerDTO);
        model.addAttribute("page", page);

        return "/admin/sellerManageDetail";
    }


    // 유지호


    // 변재혁
}
