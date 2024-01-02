package kr.co.chunjaeshop.product_review.controller;

import kr.co.chunjaeshop.product_review.dto.ProductReviewDTO;
import kr.co.chunjaeshop.product_review.service.ProductReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/product/review")
@Log4j2
@RequiredArgsConstructor
public class ProductReviewController {

    // 남원우
    private final ProductReviewService productReviewService;

    @GetMapping("/save")
    public String saveForm(){
      return "review/reviewSave";
    }


    @PostMapping("/save")
    public String save(@ModelAttribute @Valid ProductReviewDTO productReviewDTO, BindingResult bindingResult) {

      if (bindingResult.hasErrors()) {

        return "reviewSave";
      }

      int saveResult = productReviewService.reviewSave(productReviewDTO);
      if (saveResult > 0) {

        return "redirect:/product/paging";
      } else {

        return "reviewSave";
      }
    }
    @GetMapping(value = "/list")
    public String reviewList(HttpServletResponse httpServletResponse, Model model ) {
      List<ProductReviewDTO> reviewDTOList = productReviewService.reviewList();
      log.info(reviewDTOList);
      model.addAttribute("reviewList",reviewDTOList);

      return "review/reviewList";
    }

    // 최경락


    // 이무현


    // 유지호


    // 변재혁

}
