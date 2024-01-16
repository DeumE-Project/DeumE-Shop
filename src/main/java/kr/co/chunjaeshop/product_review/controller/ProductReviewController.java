package kr.co.chunjaeshop.product_review.controller;

import kr.co.chunjaeshop.notice.dto.NoticeDTO;
import kr.co.chunjaeshop.product_review.dto.ProductReviewDTO;
import kr.co.chunjaeshop.product_review.dto.ProductReviewPageDTO;
import kr.co.chunjaeshop.product_review.dto.ProductReviewSaveDTO;
import kr.co.chunjaeshop.product_review.service.ProductReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/product/review")
@Log4j2
@RequiredArgsConstructor
public class ProductReviewController {

    // 남원우
  private final ProductReviewService productReviewService;
  private String getFolder(){
    SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    String str = sdf.format(date);
    return str;
  }

  private boolean checkImageType(File file) {
    try {
      String contentType = Files.probeContentType(file.toPath());
      log.info("contentType = {}", contentType);

      // MIME 유형이 jpg, png, jpeg 중 하나인지 확인
      return contentType != null && (contentType.equals("image/jpeg") || contentType.equals("image/png")
              || contentType.equals("image/jpg"));
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    return false;
  }

    @GetMapping("/save")
    public String saveForm(@ModelAttribute ProductReviewSaveDTO productReviewSaveDTO){
      return "review/reviewSave";
    }

    @PostMapping("/save")
    public String save(@Validated @ModelAttribute ProductReviewSaveDTO productReviewSaveDTO, BindingResult bindingResult , HttpServletRequest httpServletRequest, Model model) {
      // 파일 저장 경로
      String uploadFolder = httpServletRequest.getServletContext().getRealPath("/review");
      String uploadFolderPath = getFolder();
      File uploadPath = new File(uploadFolder, uploadFolderPath);

      // 저장경로가 없으면 생성
      if (uploadPath.exists() == false) {
        uploadPath.mkdir();
      }

      // 파일 크기 제한
      if (productReviewSaveDTO.getReviewImg().getSize() > 1 * 1024 * 1024) {
        bindingResult.addError(new FieldError("productReviewSaveDTO", "reviewImg",
                "이미지 파일 크기는 1MB 초과할 수 없습니다."));
        return "/review/reviewSave";
      }

      if (bindingResult.hasErrors()) {
        FieldError fieldError = bindingResult.getFieldError();
        log.info("filederror={}",fieldError.getDefaultMessage());
        return "/review/reviewSave";
      }


      // 파일 업로드하는 로직
      UUID uuid = UUID.randomUUID();
      String fileImgOriginal
              = productReviewSaveDTO.getReviewImg().getOriginalFilename();
      log.info("fileImgOriginal={}", fileImgOriginal);
      String fileImgSaved = uuid.toString()+ "_"+  fileImgOriginal;
      log.info("fileImgSaved = {} " + fileImgSaved);
      String fileImgSavedUploadPath = uploadPath + File.separator + fileImgSaved;
      log.info("fileImgSavedUploadPath = {} " + fileImgSavedUploadPath);

      if (!checkImageType(new File(fileImgSaved))) {
        log.error("Invalid image type detected. File: {}", fileImgSaved);
        bindingResult.addError(new FieldError("productReviewSaveDTO", "reviewImg",
                "올바른 이미지 형식이 아닙니다. jpg, jpeg, png 형식의 이미지만 허용됩니다."));
        return "/review/reviewSave"; // 허용되지 않는 이미지 형식일 경우 reviewSave 페이지
      }

      try {
        //Original은 원본 이름을 저장하는데 사용.
        productReviewSaveDTO.getReviewImg().transferTo(new File(fileImgSavedUploadPath));

        // 썸네일 저장 및 생성

        String thumbnailFilename = "thumb_" + uuid.toString() + productReviewSaveDTO.getReviewImg().getOriginalFilename();
        log.info("thumbnailFilename = {} " + thumbnailFilename);
        String thumbnailFilePath = uploadPath + File.separator + thumbnailFilename;
        log.info("thumbnailFilePath = {} " + thumbnailFilePath);
        Thumbnails.of(productReviewSaveDTO.getReviewImg().getInputStream())
                .size(100, 100)
                .toFile(new File(thumbnailFilePath));
        HttpSession session = httpServletRequest.getSession();

        ProductReviewDTO productReviewDTO = new ProductReviewDTO();

        //테스트용
        productReviewDTO.setCustomerIdx(1);
        productReviewDTO.setProductIdx(2);
        productReviewDTO.setReviewContent(productReviewSaveDTO.getReviewContent());
        productReviewDTO.setReviewStar(productReviewSaveDTO.getReviewStar());
        productReviewDTO.setReviewThumbSaved(thumbnailFilename);
        productReviewDTO.setReviewImgOriginal(fileImgOriginal);
        productReviewDTO.setReviewImgSaved(uploadFolderPath + "/" + fileImgSaved);

        log.info("productReviewDTO = {} " + productReviewDTO);

        int saveResult = productReviewService.reviewSave(productReviewDTO);
        log.info("saveResult = {} " + saveResult);



        if (saveResult > 0) {
          return "redirect:/product/review/paging"; // 저장 성공 시 paging 페이지로 redirect
        } else {
          log.error("/리뷰 등록에 실패했습니다."); // 상품 등록 실패 메시지 로깅

          bindingResult.addError(new FieldError("productReviewSaveDTO", "",
                  "리뷰 등록에 실패했습니다. 다시 시도해주세요.")); // 실패 메시지 바인딩

          return "/review/reviewSave"; // 저장 실패 시 save 페이지로 리디렉션
        }
      } catch (IOException e) {
      return "/review/reviewSave"; // 저장 실패 시 save 페이지로 리디렉션
      }

    }
    @GetMapping(value = "/list")
    public String reviewList(HttpServletResponse httpServletResponse, Model model ) {
      List<ProductReviewDTO> reviewDTOList = productReviewService.reviewList();
      log.info(reviewDTOList);
      model.addAttribute("reviewList",reviewDTOList);

      return "review/reviewList";
    }

  // 페이징 메서드
  @GetMapping("/paging")
  public String paging(Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
    // productReviewService 사용하여 지정된 페이지의 게시글 목록을 가져옵니다.
    List<ProductReviewDTO> pagingList = productReviewService.pagingList(page);
    // productReviewService 사용하여 페이징 정보를 가져옵니다.
    ProductReviewPageDTO pageDTO = productReviewService.pagingParam(page);
    // 페이징된 목록 및 페이징 정보를 뷰에서 렌더링하기 위해 모델에 추가합니다.
    model.addAttribute("pagingList", pagingList);
    model.addAttribute("paging", pageDTO);
    // 뷰 이름을 반환합니다.
    return "review/reviewPaging";
  }

  @GetMapping
  public String findById(@RequestParam("reviewIdx") String reviewIdx, Model model) {
    ProductReviewDTO productReviewDTO = productReviewService.findByIdx(reviewIdx);
    model.addAttribute("productReview", productReviewDTO);
    return "review/reviewDetail";
  }
  @GetMapping("/update")
  public String updateForm(@RequestParam("reviewIdx") String reviewIdx, Model model) {

    ProductReviewSaveDTO productReviewSaveDTO = productReviewService.findByIdxReviewSaveDTO(reviewIdx);
    model.addAttribute("productReviewSaveDTO", productReviewSaveDTO);
    //model.addAttribute("reviewIdx",productReviewDTO.getReviewIdx());
    //log.info(productReviewDTO.getReviewIdx());
    return "review/reviewUpdate";
  }
  //수정
  @PostMapping("/update")
  public String update(@Validated @ModelAttribute ProductReviewSaveDTO productReviewSaveDTO, BindingResult bindingResult , HttpServletRequest httpServletRequest, Model model) {

    log.info("productReviewSaveDTO = {}", productReviewSaveDTO);

    // 파일 저장 경로
    String uploadFolder = httpServletRequest.getServletContext().getRealPath("/review");
    String uploadFolderPath = getFolder();
    File uploadPath = new File(uploadFolder, uploadFolderPath);

    // 저장경로가 없으면 생성
    if (uploadPath.exists() == false) {
      uploadPath.mkdir();
    }
    // 파일 크기 제한
    if (productReviewSaveDTO.getReviewImg().getSize() > 1 * 1024 * 1024) {
      bindingResult.addError(new FieldError("productReviewSaveDTO", "reviewImg",
              "이미지 파일 크기는 1MB 초과할 수 없습니다."));
      return "/review/reviewSave";
    }

    if (bindingResult.hasErrors()) {
      FieldError fieldError = bindingResult.getFieldError();
      log.info("filederror={}",fieldError.getDefaultMessage());
      return "/review/reviewUpdate";
    }

    // 파일 업로드하는 로직
    UUID uuid = UUID.randomUUID();
    String fileImgOriginal
            = productReviewSaveDTO.getReviewImg().getOriginalFilename();
    log.info("fileImgOriginal={}", fileImgOriginal);
    String fileImgSaved = uuid.toString()+ "_"+  fileImgOriginal;
    log.info("fileImgSaved = {} " + fileImgSaved);
    String fileImgSavedUploadPath = uploadPath + File.separator + fileImgSaved;
    log.info("fileImgSavedUploadPath = {} " + fileImgSavedUploadPath);

    if (!checkImageType(new File(fileImgSaved))) {
      log.error("Invalid image type detected. File: {}", fileImgSaved);
      bindingResult.addError(new FieldError("productReviewSaveDTO", "reviewImg",
              "올바른 이미지 형식이 아닙니다. jpg, jpeg, png 형식의 이미지만 허용됩니다."));
      return "/review/reviewUpdate"; // 허용되지 않는 이미지 형식일 경우 save 페이지
    }

    try {
      //Original은 원본 이름을 저장하는데 사용.
      productReviewSaveDTO.getReviewImg().transferTo(new File(fileImgSavedUploadPath));

      // 썸네일 저장 및 생성

      String thumbnailFilename = "thumb_" + uuid.toString() + productReviewSaveDTO.getReviewImg().getOriginalFilename();
      log.info("thumbnailFilename = {} " + thumbnailFilename);
      String thumbnailFilePath = uploadPath + File.separator + thumbnailFilename;
      log.info("thumbnailFilePath = {} " + thumbnailFilePath);
      Thumbnails.of(productReviewSaveDTO.getReviewImg().getInputStream())
              .size(100, 100)
              .toFile(new File(thumbnailFilePath));
      HttpSession session = httpServletRequest.getSession();

      ProductReviewDTO productReviewDTO = new ProductReviewDTO();

      //테스트용
      productReviewDTO.setCustomerIdx(1);
      productReviewDTO.setProductIdx(2);
      productReviewDTO.setReviewIdx(productReviewSaveDTO.getReviewIdx());
      productReviewDTO.setReviewContent(productReviewSaveDTO.getReviewContent());
      productReviewDTO.setReviewStar(productReviewSaveDTO.getReviewStar());
      productReviewDTO.setReviewThumbSaved(thumbnailFilename);
      productReviewDTO.setReviewImgOriginal(fileImgOriginal);
      productReviewDTO.setReviewImgSaved(uploadFolderPath + "/" + fileImgSaved);

      log.info("productReviewDTO = {} " + productReviewDTO);

      boolean updateResult = productReviewService.update(productReviewDTO);
      log.info("saveResult = {} " + updateResult);



      if (updateResult) {
        return "redirect:/product/review/paging"; // 저장 성공 시 detail 페이지로 redirect
      } else {
        log.error("/리뷰 등록에 실패했습니다."); // 상품 등록 실패 메시지 로깅

        bindingResult.addError(new FieldError("productReviewSaveDTO", "",
                "리뷰 등록에 실패했습니다. 다시 시도해주세요.")); // 실패 메시지 바인딩

        return "/review/reviewUpdate";// 저장 실패 시 save 페이지로 리디렉션
      }
    } catch (IOException e) {
      return "/review/reviewUpdate"; // 저장 실패 시 save 페이지로 리디렉션
    }

  }


  @GetMapping("/delete")
  public String delete(@RequestParam("reviewIdx") String reviewIdx) {
    productReviewService.delete(reviewIdx);
    return "redirect:/product/review/paging";

  }
  // 최경락


    // 이무현


    // 유지호


    // 변재혁

}
