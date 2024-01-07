package kr.co.chunjaeshop.product.controller;

import kr.co.chunjaeshop.product.dto.ProductDTO;
import kr.co.chunjaeshop.product.dto.ProductSaveDTO;
import kr.co.chunjaeshop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
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
            log.info("file.toPath() = {}", file.toPath());
            String contentType = Files.probeContentType(file.toPath());
            log.info("contentType = {}", contentType);
            return contentType.startsWith("image");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }
    @GetMapping("save")
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
        //String sellerIdx=(String)httpServletRequest.getSession().getAttribute("sellerIdx");
        String fileThumbSaved ="경로/파일이름";
        UUID uuid = UUID.randomUUID();
        String fileImgOriginal
                = uuid.toString() + "_" + productSaveDTO.getProductImg().getOriginalFilename();
        String fileImgSaved = fileImgOriginal + "saved";
        /*try {
            fileImgSaved.toString(new File(uploadPath + fileImgSaved));
        } catch(Exception e){
            e.printStackTrace();
        }*/
        String fileDetailOriginal
                = uuid.toString() + "_" + productSaveDTO.getProductDetailImg().getOriginalFilename();
        String fileDetailSaved = fileDetailOriginal + "saved";


        //ProductMapper 대응하는 ProductDTO를 생성해서 DB에 저장
        ProductDTO productDTO = new ProductDTO();

        //productDTO.setSellerIdx(sellerIdx);
        productDTO.setProductName(productSaveDTO.getProductName());
        productDTO.setCategoryIdx(productSaveDTO.getCategoryIdx());
        productDTO.setProductExplain(productSaveDTO.getProductExplain());
        productDTO.setProductPrice(productSaveDTO.getProductPrice());
        productDTO.setProductStock(productSaveDTO.getProductStock());
        productDTO.setProductThumbSaved(fileThumbSaved);
        productDTO.setProductImgOriginal(fileImgOriginal);
        productDTO.setProductImgSaved(fileImgSaved);
        productDTO.setProductDetailOriginal(fileDetailSaved);
        productDTO.setProductDetailSaved(fileDetailOriginal);

       /* int saveResult = productService.productSave(productDTO);
        if(saveResult >0 ) {
            return "redirect:/detail"
        } else{
        return "save"; */


    }



    // 이무현


    // 유지호


    // 변재혁

}
