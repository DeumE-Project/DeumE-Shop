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
        String replaced = str.replace("-", File.separator);
        log.info("replaces = {}", replaced);
        return replaced;
    }

    private boolean checkImageType(File file) {
        try {
            String contentType = Files.probeContentType(file.toPath());
            log.info("contentType = {}", contentType);

            // MIME 유형이 jpe, png, jpeg 중 하나인지 확인
            return contentType != null && (contentType.equals("image/jpeg") || contentType.equals("image/png"));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    @GetMapping("/save")
    public String saveForm(@ModelAttribute ProductSaveDTO productSaveDTO) {
        return "save";
    }

    @PostMapping("/save")
    public String save(@Validated @ModelAttribute ProductSaveDTO productSaveDTO,
                       HttpServletRequest httpServletRequest,
                       BindingResult bindingResult) {
        String uploadFolder = httpServletRequest.getServletContext().getRealPath("/product");

        String uploadFolderPath = getFolder();
        File uploadPath = new File(uploadFolder, uploadFolderPath);

        if (uploadPath.exists() == false) {
            uploadPath.mkdir();
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
        String fileImgOriginal
                = uuid.toString() + "_" +
                productSaveDTO.getProductImg().getOriginalFilename();
        String fileDetailOriginal
                = uuid.toString() + "_" +
                productSaveDTO.getProductDetailImg().getOriginalFilename();

        String fileImgSaved = uploadPath + File.separator +
                fileImgOriginal + "_saved";

        String fileDetailSaved = uploadPath + File.separator +
                fileDetailOriginal + "_saved";
        try {
            //Original은 원본 이름을 저장하는데 사용.
            productSaveDTO.getProductImg().transferTo(new File(fileImgSaved));
            productSaveDTO.getProductDetailImg().transferTo(new File(fileDetailSaved));

            // 썸네일 저장 및 생성
            String thumbnailFilename = "thumb_" + uuid.toString() +
                    productSaveDTO.getProductImg().getOriginalFilename();
            String thumbnailFilePath = uploadPath + File.separator + thumbnailFilename;

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
                    //sellerIdx를 Integer로 변활할 수 없는 경우 처리할 내용
                    e.printStackTrace();
                }
            }

            //ProductMapper 대응하는 ProductDTO를 생성해서 DB에 저장
            ProductDTO productDTO = new ProductDTO();

            productDTO.setSellerIdx(sellerIdx);
            productDTO.setProductName(productSaveDTO.getProductName());
            productDTO.setCategoryIdx(productSaveDTO.getCategoryIdx());
            productDTO.setProductExplain(productSaveDTO.getProductExplain());
            productDTO.setProductPrice(productSaveDTO.getProductPrice());
            productDTO.setProductStock(productSaveDTO.getProductStock());
            productDTO.setProductThumbSaved(thumbnailFilePath);
            productDTO.setProductImgOriginal(fileImgOriginal);
            productDTO.setProductImgSaved(fileImgSaved);
            productDTO.setProductDetailOriginal(fileDetailSaved);
            productDTO.setProductDetailSaved(fileDetailOriginal);

            int saveResult = productService.productSave(productDTO);
            if (saveResult > 0) {
                return "redirect:/product/productDetail"; // 저장 성공 시 detail 페이지로 리디렉션
            } else {
                log.error("상품 등록에 실패했습니다."); // 상품 등록 실패 메시지 로깅

                bindingResult.addError(new FieldError("productSaveDTO", "",
                        "상품 등록에 실패했습니다. 다시 시도해주세요.")); // 실패 메시지 바인딩

                return "productSave"; // 저장 실패 시 save 페이지로 리디렉션
            }
        } catch (IOException e) {
            log.error("파일 업로드에 실패했습니다.", e); // 파일 업로드 실패 메시지 로깅
            bindingResult.addError(new FieldError("productSaveDTO", "",
                    "파일 업로드에 실패했습니다. 다시 시도해주세요.")); // 파일 업로드 실패 메시지 바인딩

            return "productSave"; // 저장 실패 시 save 페이지로 리디렉션
        }
    }
}




    // 이무현


    // 유지호


    // 변재혁


