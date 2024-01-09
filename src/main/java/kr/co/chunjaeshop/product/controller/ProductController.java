package kr.co.chunjaeshop.product.controller;

import kr.co.chunjaeshop.product.dto.ProductDTO;
import kr.co.chunjaeshop.product.dto.ProductSaveDTO;
import kr.co.chunjaeshop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = sdf.format(date);
//        String replaced = str.replace("-", File.separator);
//        log.info("replaces = {}", replaced);
//        return replaced;
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

    @GetMapping("/productSave")
    public String saveForm(@ModelAttribute ProductSaveDTO productSaveDTO) {
        return "/product/productSave";
    }

    @PostMapping("/productSave")
    public String save(@Validated @ModelAttribute ProductSaveDTO productSaveDTO,
                       HttpServletRequest httpServletRequest,
                       BindingResult bindingResult) {
        // 파일 저장 경로
        String uploadFolder = httpServletRequest.getServletContext().getRealPath("/product");

        String uploadFolderPath = getFolder(); // yyyy-MM-dd
        File uploadPath = new File(uploadFolder, uploadFolderPath);// /product/yyyy-MM-dd
        log.info("upload path = {}", uploadPath);
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
        log.info("productSaveDTO = {} " + productSaveDTO);


        // 파일 업로드하는 로직
        UUID uuid = UUID.randomUUID();
        //uuid_OriginalFileName
        /*String fileImgOriginal
                = uuid.toString() + "_" +
                productSaveDTO.getProductImg().getOriginalFilename();*/
        String fileImgOriginal
                = productSaveDTO.getProductImg().getOriginalFilename();
        log.info("fileImgOriginal={}", fileImgOriginal);
       /* String fileDetailOriginal
                = uuid.toString() + "_" +
                productSaveDTO.getProductDetailImg().getOriginalFilename();*/
        String fileDetailOriginal
                = productSaveDTO.getProductDetailImg().getOriginalFilename();
        log.info("fileDetailOriginal = {} " + fileDetailOriginal);
        // uuid_OriginalFileName_saved
        String fileImgSaved = uuid.toString()+ "_"+  fileImgOriginal;
        /*String fileImgSaved = "saved_" + fileImgOriginal;*/
        log.info("fileImgSaved = {} " + fileImgSaved);
        String fileImgSavedUploadPath = uploadPath + File.separator + fileImgSaved;
        log.info("fileImgSavedUploadPath = {} " + fileImgSavedUploadPath);


        String fileDetailSaved = uuid.toString()+ "_"+ fileDetailOriginal;
        /*String fileDetailSaved = "saved_" + fileDetailOriginal;*/
        log.info("fileDetailSaved = {} " + fileDetailSaved);
        String fileDetailSavedUploadPath = uploadPath + File.separator + fileDetailSaved;
        log.info("fileDetailSavedUploadPath = {} " + fileDetailSavedUploadPath);
        try {
            //Original은 원본 이름을 저장하는데 사용.
            productSaveDTO.getProductImg().transferTo(new File(fileImgSavedUploadPath));
            productSaveDTO.getProductDetailImg().transferTo(new File(fileDetailSavedUploadPath));

            // 썸네일 저장 및 생성
            /*String thumbnailFilename = "thumb_" + uuid.toString() +
                    productSaveDTO.getProductImg().getOriginalFilename();*/
            String thumbnailFilename = "thumb_" + uuid.toString() + productSaveDTO.getProductImg().getOriginalFilename();
            log.info("thumbnailFilename = {} " + thumbnailFilename);
            String thumbnailFilePath = uploadPath + File.separator + thumbnailFilename;
            log.info("thumbnailFilePath = {} " + thumbnailFilePath);

            Thumbnails.of(productSaveDTO.getProductImg().getInputStream())
                    .size(100, 100)
                    .toFile(new File(thumbnailFilePath));
            HttpSession session = httpServletRequest.getSession();
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
            log.info("productDTO = {} " + productDTO);

            int saveResult = productService.productSave(productDTO);
            log.info("saveResult = {} " + saveResult);
            if (saveResult > 0) {
                return "redirect:/product/productDetail"; // 저장 성공 시 detail 페이지로 redirect
            } else {
                log.error("/상품 등록에 실패했습니다."); // 상품 등록 실패 메시지 로깅

                bindingResult.addError(new FieldError("productSaveDTO", "",
                        "상품 등록에 실패했습니다. 다시 시도해주세요.")); // 실패 메시지 바인딩

                return "/product/productSave"; // 저장 실패 시 save 페이지로 리디렉션
            }
        } catch (IOException e) {
            log.error("파일 업로드에 실패했습니다.", e); // 파일 업로드 실패 메시지 로깅
            bindingResult.addError(new FieldError("productSaveDTO", "",
                    "파일 업로드에 실패했습니다. 다시 시도해주세요.")); // 파일 업로드 실패 메시지 바인딩

            return "/product/productSave"; // 저장 실패 시 save 페이지로 리디렉션
        }
    }
}




    // 이무현


    // 유지호


    // 변재혁


