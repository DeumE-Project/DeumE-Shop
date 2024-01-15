package kr.co.chunjaeshop.product.controller;

import kr.co.chunjaeshop.product.dto.ProductDTO;

import kr.co.chunjaeshop.product.dto.ProductDetailImgUpdateDTO;
import kr.co.chunjaeshop.product.dto.ProductMainImgUpdateDTO;

import kr.co.chunjaeshop.product.dto.ProductSaveDTO;
import kr.co.chunjaeshop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
@Log4j2
public class ProductController {
    private final ProductService productService;

    // 남원우


    // 최경락
    private String getFolder() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //ex) 2024-01-13
        Date date = new Date();
        String str = sdf.format(date);
        return str;
    }

    private boolean checkImageType(File file) {
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

    @PostMapping("/productSave")
    //bindingResult는 자동으로 model에 담음
    public String save(@Validated @ModelAttribute ProductSaveDTO productSaveDTO,
                       BindingResult bindingResult, HttpServletRequest request) {
        // 파일 저장 경로 : product/yyyy-MM-dd
        String uploadFolder = request.getServletContext().getRealPath("/product");

        String uploadFolderPath = getFolder(); // yyyy-MM-dd
        File uploadPath = new File(uploadFolder, uploadFolderPath);// /product/yyyy-MM-dd
        log.info("upload path={}", uploadPath);
        // 저장경로가 없으면 생성
        if (uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }
        if (productSaveDTO.getProductImg() == null || productSaveDTO.getProductImg().isEmpty()) {
            bindingResult.addError(new FieldError
                    ("productSaveDTO", "productImg", "상품 메인 이미지는 필수 선택입니다"));
        }
        if (productSaveDTO.getProductDetailImg() == null || productSaveDTO.getProductDetailImg().isEmpty()) {
            bindingResult.addError(new FieldError
                    ("productSaveDTO", "productDetailImg", "상품 설명 이미지는 필수 선택입니다"));
        }
        if (bindingResult.hasErrors()) {
            log.error("productSaveDTO has error");
        }
        log.info("productSaveDTO= {} ", productSaveDTO);


        // 파일 업로드하는 로직
        UUID uuid = UUID.randomUUID(); //random uuid 생성

        String fileImgOriginal
                = productSaveDTO.getProductImg().getOriginalFilename();
        log.info("fileImgOriginal={}", fileImgOriginal);

        String fileDetailOriginal
                = productSaveDTO.getProductDetailImg().getOriginalFilename();
        log.info("fileDetailOriginal=} ", fileDetailOriginal);

        String fileImgSaved = uuid.toString()+ "_"+  fileImgOriginal;
        log.info("fileImgSaved={} ",fileImgSaved);

        String fileImgSavedUploadPath = uploadPath + File.separator + fileImgSaved;
        log.info("fileImgSavedUploadPath={} ",fileImgSavedUploadPath);

        String fileDetailSaved = uuid.toString()+ "_"+ fileDetailOriginal;
        log.info("fileDetailSaved={} ",fileDetailSaved);

        String fileDetailSavedUploadPath = uploadPath + File.separator + fileDetailSaved;
        log.info("fileDetailSavedUploadPath={} ",fileDetailSavedUploadPath);
        try {
            //Original은 원본 이름을 저장하는데 사용.
            productSaveDTO.getProductImg().transferTo(new File(fileImgSavedUploadPath));
            productSaveDTO.getProductDetailImg().transferTo(new File(fileDetailSavedUploadPath));

            // 썸네일 생성 및 저장
            String thumbnailFilename = "thumb_" + uuid.toString() + productSaveDTO.getProductImg().getOriginalFilename();
            log.info("thumbnailFilename={} ",thumbnailFilename);
            String thumbnailFilePath = uploadPath + File.separator + thumbnailFilename;
            log.info("thumbnailFilePath={} ", thumbnailFilePath);

            Thumbnails.of(productSaveDTO.getProductImg().getInputStream())
                    .size(100, 100)
                    .toFile(new File(thumbnailFilePath));
            HttpSession session = request.getSession();
            Object sellerIdxObject = session.getAttribute("sellerIdx");
            Integer sellerIdx = null;
            if (sellerIdxObject != null) {
                try {
                    sellerIdx = Integer.parseInt(sellerIdxObject.toString());
                } catch (NumberFormatException e) {
                    //sellerIdx를 Integer로 변환할 수 없는 경우 처리할 내용
                    e.printStackTrace();
                }
            }
            if (!checkImageType(new File(fileImgSaved)) || !checkImageType(new File(fileDetailSaved))) {
                bindingResult.addError(new FieldError("productSaveDTO", "",
                        "올바른 이미지 형식이 아닙니다. jpg, jpeg, png 형식의 이미지만 허용됩니다."));
                return "/product/productSave"; // 허용되지 않는 이미지 형식일 경우 save 페이지로 리디렉션
            }

            //ProductMapper 대응하는 ProductDTO를 생성해서 DB에 저장
            ProductDTO productDTO = new ProductDTO();

            //productDTO.setSellerIdx(sellerIdx);
            //테스트용
            productDTO.setSellerIdx(1);
            productDTO.setProductName(productSaveDTO.getProductName());
            productDTO.setCategoryIdx(productSaveDTO.getCategoryIdx());
            productDTO.setProductExplain(productSaveDTO.getProductExplain());
            productDTO.setProductPrice(productSaveDTO.getProductPrice());
            productDTO.setProductStock(productSaveDTO.getProductStock());
            productDTO.setProductThumbSaved(thumbnailFilename);
            productDTO.setProductImgOriginal(fileImgOriginal);
            productDTO.setProductImgSaved(uploadFolderPath + "/" + fileImgSaved);
            productDTO.setProductDetailOriginal(fileDetailOriginal);
            productDTO.setProductDetailSaved(uploadFolderPath + "/" + fileDetailSaved);
            log.info("productDTO={} ", productDTO);

            int saveResult = productService.productSave(productDTO);
            log.info("saveResult={} ",saveResult);
            if (saveResult > 0) {
                return "redirect:/seller/myProduct?sellerIdx="+productDTO.getSellerIdx(); // 저장 성공 시 상품 목록 페이지로 redirect
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
    public String findByProductIdx(@RequestParam("sellerIdx") Integer sellerIdx, @RequestParam("productIdx") Integer productIdx, Model model) {
        ProductDTO productDTO= productService.findByProductIdx(sellerIdx, productIdx);
        model.addAttribute("sellerIdx", sellerIdx);
        model.addAttribute("product", productDTO);
        return "/product/productDetail";
    }

    @GetMapping("/productInfoUpdate")
    public String productUpdateForm(@RequestParam("sellerIdx") Integer sellerIdx, @RequestParam("productIdx") Integer
            productIdx, Model model) {

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
    public String productImgUpdateForm(@RequestParam("sellerIdx") Integer sellerIdx,
                                       @RequestParam("productIdx") Integer productIdx, Model model) {

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
            productMainImgUpdateDTO.setProductThumbSaved(thumbnailFilename);
            productMainImgUpdateDTO.setProductImgOriginal(fileImgOriginal);
            productMainImgUpdateDTO.setProductImgSaved(uploadFolderPath + "/" + fileImgSaved);

            boolean result = productService.productImgUpdate(productMainImgUpdateDTO);
            if (result) {
                return "redirect:/product/productDetail?sellerIdx=" + productMainImgUpdateDTO.getSellerIdx() + "&productIdx=" + productMainImgUpdateDTO.getProductIdx();
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
    public String productDetailImgUpdateForm(@RequestParam("sellerIdx") Integer sellerIdx,
                                             @RequestParam("productIdx") Integer productIdx, Model model) {

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
        if (productDetailImgUpdateDTO.getDetailImg() == null || productDetailImgUpdateDTO.getDetailImg().isEmpty()) {
            bindingResult.addError(new FieldError
                    ("productDetailImgUpdateDTO", "detailImg", "상품 설명 이미지는 필수 선택입니다"));
        }
        if (bindingResult.hasErrors()) {
            log.error("productDetailImgUpdateDTO has error");
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


    // 이무현


    // 유지호


    // 변재혁
    private final ProductService pService;

    @GetMapping(value = "/detail")
    public String productDetailForm(@RequestParam(required = false) Integer productIdx,
                                    Model model) {
        log.info("productIdx = {}", productIdx);
        if (productIdx == null) {
            return "cart/productMainCart";
        }
        ProductDTO productDTO = pService.getProductInformationByProductIdx(productIdx);
        log.info("productDTO = {}", productDTO);
        model.addAttribute("productDTO", productDTO);
        model.addAttribute("productIdx", productIdx);
        return "cart/productMainCart";
    }
}


