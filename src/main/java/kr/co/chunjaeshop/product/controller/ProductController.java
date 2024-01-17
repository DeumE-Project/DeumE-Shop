package kr.co.chunjaeshop.product.controller;

import kr.co.chunjaeshop.pagination.dto.PageDTO;
import kr.co.chunjaeshop.product.dto.ProductDTO;
import kr.co.chunjaeshop.product.dto.ProductDetailImgUpdateDTO;
import kr.co.chunjaeshop.product.dto.ProductMainImgUpdateDTO;
import kr.co.chunjaeshop.product.dto.ProductSaveDTO;
import kr.co.chunjaeshop.product.service.ProductService;
import kr.co.chunjaeshop.security.LoginUserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
@Log4j2
public class ProductController {
    private final ProductService productService;

    // 남원우


    // 최경락
    private String getFolder() {                                            // 업로드 파일 저장 폴더
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  //ex) 2024-01-13
        Date date = new Date();
        String str = sdf.format(date);
        return str;
    }

    private boolean checkImageType(File file) {                             // 업로드 파일 타입 검사
        try {
            String contentType = Files.probeContentType(file.toPath());
            log.info("contentType={}", contentType);

            // MIME 유형이 jpg, png, jpeg 중 하나인지 확인
            return contentType != null && (contentType.equals("image/jpeg") || contentType.equals("image/png")
                                                                            || contentType.equals("image/jpg"));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    @GetMapping("/productSave")
    public String saveForm(@ModelAttribute ProductSaveDTO productSaveDTO) {
        return "/product/productSave";
    }

    // 상품 업로드
    @PostMapping("/productSave")
    //bindingResult는 자동으로 model에 담음
    public String save(@Validated @ModelAttribute ProductSaveDTO productSaveDTO,
                       BindingResult bindingResult, HttpServletRequest request,
                       Authentication auth) {

        LoginUserDTO loginUserDTO = (LoginUserDTO) auth.getPrincipal();
        Integer sellerIdx = loginUserDTO.getIdx();


        // 파일 저장 경로 : product/yyyy-MM-dd
        String uploadFolder = request.getServletContext().getRealPath("/product");

        String uploadFolderPath = getFolder(); // yyyy-MM-dd
        File uploadPath = new File(uploadFolder, uploadFolderPath);// /product/yyyy-MM-dd
        log.info("upload path={}", uploadPath);
        // 저장경로가 없으면 생성
        if (uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }
        if (productSaveDTO.getProductImg().getSize() > 10 * 1024 * 1024) { //ProductImg 업로드 용량 제한
            bindingResult.addError(new FieldError
                    ("productSaveDTO", "productImg", "이미지 파일 크기는 10MB 초과할 수 없습니다."));
        }
        if ( productSaveDTO.getProductImg().getSize() > 10 * 1024 * 1024) { //ProductDetailImg 업로드 용량 제한
            bindingResult.addError(new FieldError
                    ("productSaveDTO", "productDetailImg", "이미지 파일 크기는 10MB 초과할 수 없습니다."));
        }
        if (productSaveDTO.getProductImg() == null || productSaveDTO.getProductImg().isEmpty()) { //ProductImg 업로드 필수
            bindingResult.addError(new FieldError
                    ("productSaveDTO", "productImg", "상품 메인 이미지는 필수 선택입니다"));
        }
        if (productSaveDTO.getProductDetailImg() == null || productSaveDTO.getProductDetailImg().isEmpty()) { //ProductDetailImg 업로드 필수
            bindingResult.addError(new FieldError
                    ("productSaveDTO", "productDetailImg", "상품 설명 이미지는 필수 선택입니다"));
        }
        if (bindingResult.hasErrors()) { // 에러 발생시 productSave로 이동
            log.error("productSaveDTO has error");
            return "/product/productSave";
        }
        log.info("productSaveDTO= {} ", productSaveDTO);


        // 파일 업로드하는 로직
        UUID uuid = UUID.randomUUID(); //random uuid 생성

        // productSaveDTO에서 productImg의 파일 이름을 가져옴
        String fileImgOriginal
                = productSaveDTO.getProductImg().getOriginalFilename();
        log.info("fileImgOriginal={}", fileImgOriginal);

        // productSaveDTO에서 productDetailImg의 파일 이름을 가져옴
        String fileDetailOriginal
                = productSaveDTO.getProductDetailImg().getOriginalFilename();
        log.info("fileDetailOriginal=} ", fileDetailOriginal);

        // uuid_를 적용
        String fileImgSaved = uuid.toString()+ "_"+  fileImgOriginal;
        log.info("fileImgSaved={} ",fileImgSaved);

        // 저장 경로/fileImgSaved
        String fileImgSavedUploadPath = uploadPath + File.separator + fileImgSaved;
        log.info("fileImgSavedUploadPath={} ",fileImgSavedUploadPath);

        // uuid_를 적용
        String fileDetailSaved = uuid.toString()+ "_"+ fileDetailOriginal;
        log.info("fileDetailSaved={} ",fileDetailSaved);

        // 저장경로/fileDetailSaved
        String fileDetailSavedUploadPath = uploadPath + File.separator + fileDetailSaved;
        log.info("fileDetailSavedUploadPath={} ",fileDetailSavedUploadPath);
        try {
            //이미지를 지정된 경로에 전달
            productSaveDTO.getProductImg().transferTo(new File(fileImgSavedUploadPath));
            productSaveDTO.getProductDetailImg().transferTo(new File(fileDetailSavedUploadPath));

            // 썸네일 파일명 생성
            String thumbnailFilename = "thumb_" + uuid.toString() + productSaveDTO.getProductImg().getOriginalFilename();
            log.info("thumbnailFilename={} ",thumbnailFilename);
            // 썸네일 저장경로 생성
            String thumbnailFilePath = uploadPath + File.separator + thumbnailFilename;
            log.info("thumbnailFilePath={} ", thumbnailFilePath);

            // InputStream을 사용해서 썸네일 생성
            Thumbnails.of(productSaveDTO.getProductImg().getInputStream())
                    .size(100, 100) // 썸네일 크기 설정
                    .toFile(new File(thumbnailFilePath)); // 썸네일저장

            /*HttpSession session = request.getSession();
            Object sellerIdxObject = session.getAttribute("sellerIdx");
            Integer sellerIdx = null;

            if (sellerIdxObject != null) {
                try {
                    sellerIdx = Integer.parseInt(sellerIdxObject.toString());
                } catch (NumberFormatException e) {
                    //sellerIdx를 Integer로 변환할 수 없는 경우 처리할 내용
                    e.printStackTrace();
                }
            }*/

            // 이미지 파일 형식 확인 후 허용되지 않은 형식이면 에러 추가 후 productSave로 redirection
            if (!checkImageType(new File(fileImgSaved)) || !checkImageType(new File(fileDetailSaved))) {
                bindingResult.addError(new FieldError("productSaveDTO", "",
                        "올바른 이미지 형식이 아닙니다. jpg, jpeg, png 형식의 이미지만 허용됩니다."));
                return "/product/productSave";
            }

            //ProductMapper 대응하는 ProductDTO를 생성해서 DB에 저장
            ProductDTO productDTO = new ProductDTO();

            productDTO.setSellerIdx(sellerIdx); //sellerIdx 설정
            productDTO.setProductName(productSaveDTO.getProductName()); // productName 설정
            productDTO.setCategoryIdx(productSaveDTO.getCategoryIdx()); //categoryIdx 설정
            productDTO.setProductExplain(productSaveDTO.getProductExplain()); // productExplain 설정
            productDTO.setProductPrice(productSaveDTO.getProductPrice()); // productPrice 설정
            productDTO.setProductStock(productSaveDTO.getProductStock()); // productStock 설정
            productDTO.setProductThumbSaved(uploadFolderPath + File.separator + thumbnailFilename); // productThumbSaved 설정
            productDTO.setProductImgOriginal(fileImgOriginal); //productImgOriginal 설정
            productDTO.setProductImgSaved(uploadFolderPath + "/" + fileImgSaved); // productImgSaved 설정
            productDTO.setProductDetailOriginal(fileDetailOriginal); // productDetailOriginal 설정
            productDTO.setProductDetailSaved(uploadFolderPath + "/" + fileDetailSaved); // productDetailSaved 설정
            log.info("productDTO={} ", productDTO);

            // productService를 통해 상품 정보 저장, 저장 결과를 saveResult에 저장.
            int saveResult = productService.productSave(productDTO);
            log.info("saveResult={} ",saveResult);
            if (saveResult > 0) {
//                return "redirect:/seller/myProduct?sellerIdx="+productDTO.getSellerIdx(); // 저장 성공 시 상품 목록 페이지로 redirect
                return "redirect:/seller/myProduct"; // 저장 성공 시 상품 목록 페이지로 redirect
            } else {
                log.error("/상품 등록에 실패했습니다."); // 상품 등록 실패 메시지 로깅

                bindingResult.addError(new FieldError("productSaveDTO", "",
                        "상품 등록에 실패했습니다. 다시 시도해주세요.")); // 실패 메시지 바인딩

                return "redirect:/product/productSave"; // 저장 실패 시 save 페이지로 리디렉션
            }
        } catch (IOException e) {
            log.error("파일 업로드에 실패했습니다.", e); // 파일 업로드 실패 메시지 로깅
            bindingResult.addError(new FieldError("productSaveDTO", "",
                    "파일 업로드에 실패했습니다. 다시 시도해주세요.")); // 파일 업로드 실패 메시지 바인딩

            return "/product/productSave"; // 저장 실패 시 save 페이지로 리디렉션
        }
    }

    //product/productDetail?sellerIdx=1&productIdx=1
    // null 값일때 에러 뜨게.
    @GetMapping("/productDetail")
    public String findByProductIdx(
//            @RequestParam("sellerIdx") Integer sellerIdx,
                                   @RequestParam("productIdx") Integer productIdx, Model model,
                                   Authentication auth) {
        LoginUserDTO loginUserDTO = (LoginUserDTO) auth.getPrincipal();
        Integer sellerIdx = loginUserDTO.getIdx();

        ProductDTO productDTO= productService.findByProductIdx(sellerIdx, productIdx);

        if (productDTO == null) {
            return "redirect:/seller/myProduct";
        }

        model.addAttribute("sellerIdx", sellerIdx);
        model.addAttribute("product", productDTO);
        return "/product/productDetail";
    }

    @GetMapping("/productInfoUpdate")
    public String productUpdateForm(
//            @RequestParam("sellerIdx") Integer sellerIdx,
            @RequestParam("productIdx") Integer productIdx,
            Model model,
            Authentication auth) {

        LoginUserDTO loginUserDTO = (LoginUserDTO) auth.getPrincipal();
        Integer sellerIdx = loginUserDTO.getIdx();

        ProductDTO productDTO = productService.findByProductIdx2(sellerIdx, productIdx);
        log.info("sellerIdx",sellerIdx );
        log.info("productIdx", productIdx);
        model.addAttribute("sellerIdx", sellerIdx);
        model.addAttribute("productIdx", productIdx);
        model.addAttribute("productDTO", productDTO);


        return "/product/productInfoUpdate";
    }

    @PostMapping("/productInfoUpdate")
    public String productInfoUpdate(@Validated @ModelAttribute("productDTO") ProductDTO productDTO,
                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.error("productDTO has error");
            return "/product/productInfoUpdate";
        }

            boolean result = productService.productInfoUpdate(productDTO);
            log.error("productDTO has error");
            if (result) {
                return "redirect:/product/productDetail?sellerIdx=" + productDTO.getSellerIdx() + "&productIdx=" + productDTO.getProductIdx();
            } else {
                log.error("productDTO has error");
            return "redirect:/seller/mySellerPage?sellerIdx=" + productDTO.getSellerIdx();
        }
    }
    @GetMapping("/productImgUpdate")
    public String productImgUpdateForm(
//            @RequestParam("sellerIdx") Integer sellerIdx,
                                       @RequestParam("productIdx") Integer productIdx, Model model,
                                       Authentication auth) {

        LoginUserDTO loginUserDTO = (LoginUserDTO) auth.getPrincipal();
        Integer sellerIdx = loginUserDTO.getIdx();

        ProductMainImgUpdateDTO productMainImgUpdateDTO = productService.findMainImg(sellerIdx, productIdx);
        log.info("sellerIdx",sellerIdx );
        log.info("productIdx", productIdx);
        model.addAttribute("sellerIdx", sellerIdx);
        model.addAttribute("productIdx", productIdx);
        model.addAttribute("productMainImgUpdateDTO", productMainImgUpdateDTO);

        return "/product/productImgUpdateForm";
    }

    @PostMapping("/productImgUpdate")
    public String productImgUpdate(@Validated @ModelAttribute ProductMainImgUpdateDTO productMainImgUpdateDTO,
                                   BindingResult bindingResult, HttpServletRequest request) {

        log.info("mainImg={}", productMainImgUpdateDTO);

        String uploadFolder = request.getServletContext().getRealPath("/product");

        String uploadFolderPath = getFolder(); // yyyy-MM-dd
        File uploadPath = new File(uploadFolder, uploadFolderPath);// /product/yyyy-MM-dd
        log.info("upload path = {}", uploadPath);
        // 저장경로가 없으면 생성
        if (uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }
        if ( productMainImgUpdateDTO.getMainImg().getSize() >  10 * 1024 * 1024) {
            bindingResult.addError(new FieldError
                    ("productMainImgUpdateDTO", "mainImg", "이미지 파일 크기는 10MB 초과할 수 없습니다."));
        }
        if (productMainImgUpdateDTO.getMainImg() == null || productMainImgUpdateDTO.getMainImg().isEmpty()) {
            bindingResult.addError(new FieldError
                    ("productMainImgUpdateDTO", "mainImg", "상품 이미지는 필수 선택입니다"));
        }
        if (bindingResult.hasErrors()) {
            log.error("productMainImgUpdateDTO has error");
            return "/product/productImgUpdateForm";
        }
        log.info("ProductMainImgUpdateDTO= {} ",productMainImgUpdateDTO);

        // 파일 업로드하는 로직
        UUID uuid = UUID.randomUUID();
        log.info("uuid={}",uuid);

        String fileImgOriginal
                = productMainImgUpdateDTO.getMainImg().getOriginalFilename();
        log.info("fileImgOriginal={}",fileImgOriginal);

        String fileImgSaved = uuid.toString() + "_" + fileImgOriginal;
        log.info("fileImgSaved={}",fileImgSaved);

        String fileImgSavedUploadPath = uploadPath + File.separator + fileImgSaved;
        log.info("fileImgSavedUploadPath={}",fileImgSavedUploadPath);
        try {
            //Original은 원본 이름을 저장하는데 사용.
            productMainImgUpdateDTO.getMainImg().transferTo(new File(fileImgSavedUploadPath));

            String thumbnailFilename = "thumb_" + uuid.toString() + productMainImgUpdateDTO.getMainImg().getOriginalFilename();
            log.info("thumbnailFilename={}",thumbnailFilename);
            String thumbnailFilePath = uploadPath + File.separator + thumbnailFilename;
            log.info("thumbnailFilePath={}",thumbnailFilePath);

            Thumbnails.of(productMainImgUpdateDTO.getMainImg().getInputStream())
                    .size(100, 100)
                    .toFile(new File(thumbnailFilePath));
            if (!checkImageType(new File(fileImgSaved))) {
                bindingResult.addError(new FieldError("productMainImgUpdateDTO", "",
                        "올바른 이미지 형식이 아닙니다. jpg, jpeg, png 형식의 이미지만 허용됩니다."));
                return "/product/productImgUpdateForm";
            }

            productMainImgUpdateDTO.setProductIdx(productMainImgUpdateDTO.getProductIdx());
            productMainImgUpdateDTO.setSellerIdx(productMainImgUpdateDTO.getSellerIdx());
            productMainImgUpdateDTO.setProductThumbSaved(uploadFolderPath + File.separator + thumbnailFilename);
            productMainImgUpdateDTO.setProductImgOriginal(fileImgOriginal);
            productMainImgUpdateDTO.setProductImgSaved(uploadFolderPath + "/" + fileImgSaved);

            boolean result = productService.productImgUpdate(productMainImgUpdateDTO);
            if (result) {
//                return "redirect:/product/productDetail?sellerIdx=" + productMainImgUpdateDTO.getSellerIdx() + "&productIdx=" + productMainImgUpdateDTO.getProductIdx();
                return "redirect:/product/productDetail?productIdx=" + productMainImgUpdateDTO.getProductIdx();
            } else {
                log.error("사진 등록에 실패했습니다.");
                bindingResult.addError(new FieldError("productMainImgUpdateDTO", "",
                        "사진 등록에 실패했습니다. 다시 시도해주세요."));
                // 에러 처리 로직 추가
                log.error("productMainImgUpdateDTO, sellerIdx, 또는 productIdx가 null입니다.");

                return "/product/productImgUpdateForm";
            }
        } catch (IOException e) {
            log.error("파일 업로드에 실패했습니다.", e); // 파일 업로드 실패 메시지 로깅
            bindingResult.addError(new FieldError("productMainImgUpdateDTO", "",
                    "파일 업로드에 실패했습니다. 다시 시도해주세요."));
            // 에러 처리 로직 추가
            log.error("파일 업로드에 실패했습니다.", e);

            return "/product/productImgUpdateForm";
        }
    }
    @GetMapping("/productDetailImgUpdate")
    public String productDetailImgUpdateForm(
//            @RequestParam("sellerIdx") Integer sellerIdx,
                                             @RequestParam("productIdx") Integer productIdx, Model model,
                                             Authentication auth) {

        LoginUserDTO loginUserDTO = (LoginUserDTO) auth.getPrincipal();
        Integer sellerIdx = loginUserDTO.getIdx();

        ProductDetailImgUpdateDTO productDetailImgUpdateDTO = productService.findDetailImg(sellerIdx, productIdx);

        log.info("sellerIdx",sellerIdx );
        log.info("productIdx", productIdx);
        model.addAttribute("sellerIdx", sellerIdx);
        model.addAttribute("productIdx", productIdx);
        model.addAttribute("productDetailImgUpdateDTO", productDetailImgUpdateDTO);

        return "/product/productDetail_ImgUpdateForm";
    }

    @PostMapping("/productDetailImgUpdate")
    public String productDetailImgUpdate(@Validated @ModelAttribute ProductDetailImgUpdateDTO productDetailImgUpdateDTO,
                                   BindingResult bindingResult, HttpServletRequest request) {
        log.info("detailImg={}", productDetailImgUpdateDTO);

        String uploadFolder = request.getServletContext().getRealPath("/product");

        String uploadFolderPath = getFolder(); // yyyy-MM-dd
        File uploadPath = new File(uploadFolder, uploadFolderPath);// /product/yyyy-MM-dd
        log.info("upload path = {}", uploadPath);
        // 저장경로가 없으면 생성
        if (uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }
        if (productDetailImgUpdateDTO.getDetailImg().getSize()>  10 * 1024 * 1024) {
            bindingResult.addError(new FieldError
                    ("productDetailImgUpdateDTO", "detailImg", "이미지 파일 크기는 10MB 초과할 수 없습니다."));
        }
        if (productDetailImgUpdateDTO.getDetailImg() == null || productDetailImgUpdateDTO.getDetailImg().isEmpty()) {
            bindingResult.addError(new FieldError
                    ("productDetailImgUpdateDTO", "detailImg", "상품 설명 이미지는 필수 선택입니다"));
        }
        if (bindingResult.hasErrors()) {
            log.error("productDetailImgUpdateDTO has error");
            return "/product/productDetail_ImgUpdateForm";
        }
        log.info("productDetailImgUpdateDTO= {} ",productDetailImgUpdateDTO);

        // 파일 업로드하는 로직
        UUID uuid = UUID.randomUUID();
        log.info("uuid = {}",uuid);

        String fileDetailOriginal
                = productDetailImgUpdateDTO.getDetailImg().getOriginalFilename();
        log.info("fileDetailOriginal = {}",fileDetailOriginal);

        String fileDetailSaved = uuid.toString() + "_" + fileDetailOriginal;
        log.info("fileDetailSaved = {}",fileDetailSaved);

        String fileDetailSavedUploadPath = uploadPath + File.separator + fileDetailSaved;
        log.info("filDetailSavedUploadPath = {}" ,fileDetailSavedUploadPath);

        try {
            //Original은 원본 이름을 저장하는데 사용.
            productDetailImgUpdateDTO.getDetailImg().transferTo(new File(fileDetailSavedUploadPath));

            if (!checkImageType(new File(fileDetailSaved))) {
                bindingResult.addError(new FieldError("productDetailImgUpdateDTO", "",
                        "올바른 이미지 형식이 아닙니다. jpg, jpeg, png 형식의 이미지만 허용됩니다."));
                return "/product/productDetail_ImgUpdateForm";
            }

            productDetailImgUpdateDTO.setProductIdx(productDetailImgUpdateDTO.getProductIdx());
            productDetailImgUpdateDTO.setSellerIdx(productDetailImgUpdateDTO.getSellerIdx());
            productDetailImgUpdateDTO.setProductDetailOriginal(fileDetailOriginal);
            productDetailImgUpdateDTO.setProductDetailSaved(uploadFolderPath + "/" + fileDetailSaved);

            boolean result = productService.productDetailImgUpdate(productDetailImgUpdateDTO);
            if (result) {
                return "redirect:/product/productDetail?sellerIdx=" + productDetailImgUpdateDTO.getSellerIdx() + "&productIdx=" + productDetailImgUpdateDTO.getProductIdx();
            } else {
                log.error("사진 등록에 실패했습니다.");
                bindingResult.addError(new FieldError("productDetailImgUpdateDTO", "",
                        "사진 등록에 실패했습니다. 다시 시도해주세요."));
                // 에러 처리 로직 추가
                log.error("productDetailImgUpdateDTO, sellerIdx, 또는 productIdx가 null입니다.");

                return "/product/productDetail_ImgUpdateForm";
            }
        } catch (IOException e) {
            log.error("파일 업로드에 실패했습니다.", e); // 파일 업로드 실패 메시지 로깅
            bindingResult.addError(new FieldError("productDetailImgUpdateDTO", "",
                    "파일 업로드에 실패했습니다. 다시 시도해주세요."));
            // 에러 처리 로직 추가
            log.error("파일 업로드에 실패했습니다.", e);
     
            return "/product/productDetail_ImgUpdateForm";
        }
    }

    @GetMapping("/productList")
    public String productList(@RequestParam("categoryIdx") Integer categoryIdx,
                                  @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                  @RequestParam(value = "searchField", required = false) String searchField,
                                  @RequestParam(value = "searchWord", required = false) String searchWord,
                                  Model model){
        List<ProductDTO> productListPaging = productService.productListPagingWithSearch(categoryIdx, page, searchField, searchWord);
        PageDTO productPageDTO = productService.productListPagingParam(page, categoryIdx);
        PageDTO productListSearchPageDTO = productService.productListPagingSearchParam(page, categoryIdx, searchField, searchWord);
        model.addAttribute("productListPaging", productListPaging);
        model.addAttribute("productPageDTO", productPageDTO);
        model.addAttribute("productListSearchPageDTO", productListSearchPageDTO);
        model.addAttribute("categoryIdx", categoryIdx);
        model.addAttribute("searchField", searchField);
        model.addAttribute("searchWord", searchWord);
        return "/product/productList";
    }


    // 이무현


    // 유지호


    // 변재혁
    private final ProductService pService;

    @GetMapping(value = "/detail")
    public String productDetailForm(@RequestParam(required = false) Integer productIdx,
                                    Model model,
                                    RedirectAttributes redirectAttributes) {
        log.info("productIdx = {}", productIdx);
        if (productIdx == null) {
            return "cart/productMainCart";
        }
        ProductDTO productDTO = pService.getProductInformationByProductIdx(productIdx);
        log.info("productDTO = {}", productDTO);

        if (productDTO == null) {
            redirectAttributes.addFlashAttribute("productNotExistErrorMsg", "상품을 찾을 수 없습니다");
            return "redirect:/main";
        }

        model.addAttribute("productDTO", productDTO);
        model.addAttribute("productIdx", productIdx);
        return "cart/productMainCart";
    }

    /*@ExceptionHandler(value = Exception.class)
    public String handleAllException(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("globalErrorMsg", "잘못된 요청입니다");
        return "redirect:/";
    }*/
}


