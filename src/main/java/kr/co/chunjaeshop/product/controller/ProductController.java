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
                       BindingResult bindingResult, HttpServletRequest request) {
        // 파일 저장 경로
        String uploadFolder = request.getServletContext().getRealPath("/product");

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

        String fileImgOriginal
                = productSaveDTO.getProductImg().getOriginalFilename();
        log.info("fileImgOriginal={}", fileImgOriginal);

        String fileDetailOriginal
                = productSaveDTO.getProductDetailImg().getOriginalFilename();
        log.info("fileDetailOriginal = {} " + fileDetailOriginal);

        String fileImgSaved = uuid.toString()+ "_"+  fileImgOriginal;
        log.info("fileImgSaved = {} " + fileImgSaved);

        String fileImgSavedUploadPath = uploadPath + File.separator + fileImgSaved;
        log.info("fileImgSavedUploadPath = {} " + fileImgSavedUploadPath);

        String fileDetailSaved = uuid.toString()+ "_"+ fileDetailOriginal;
        log.info("fileDetailSaved = {} " + fileDetailSaved);

        String fileDetailSavedUploadPath = uploadPath + File.separator + fileDetailSaved;
        log.info("fileDetailSavedUploadPath = {} " + fileDetailSavedUploadPath);
        try {
            //Original은 원본 이름을 저장하는데 사용.
            productSaveDTO.getProductImg().transferTo(new File(fileImgSavedUploadPath));
            productSaveDTO.getProductDetailImg().transferTo(new File(fileDetailSavedUploadPath));

            // 썸네일 생성 및 저장
            String thumbnailFilename = "thumb_" + uuid.toString() + productSaveDTO.getProductImg().getOriginalFilename();
            log.info("thumbnailFilename = {} " + thumbnailFilename);
            String thumbnailFilePath = uploadPath + File.separator + thumbnailFilename;
            log.info("thumbnailFilePath = {} " + thumbnailFilePath);

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
            log.info("productDTO = {} " + productDTO);

            int saveResult = productService.productSave(productDTO);
            log.info("saveResult = {} " + saveResult);
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

    //HttpServletRequest request)
    @GetMapping("/productInfoUpdate")
    public String productUpdateForm(@RequestParam("sellerIdx") Integer sellerIdx, @RequestParam("productIdx") Integer
            productIdx, Model model) {

        ProductDTO productDTO = productService.findByProductIdx2(sellerIdx, productIdx);
        log.info("sellerIdx",sellerIdx );
        log.info("productIdx", productIdx);
        model.addAttribute("sellerIdx", sellerIdx);
        model.addAttribute("productIdx", productIdx);
        model.addAttribute("product", productDTO);
        // 현재 제품 정보를 세션에 추가
       /* HttpSession session = request.getSession();
        session.setAttribute("currentProduct", productDTO);*/

        return "/product/productInfoUpdate";
    }

    @PostMapping("/productInfoUpdate")
    public String productInfoUpdate(@ModelAttribute ProductDTO productDTO) {

        boolean result = productService.productInfoUpdate(productDTO);
        if (result) {
            return "redirect:/product/productDetail?sellerIdx="+productDTO.getSellerIdx()+"&"+"productIdx="+productDTO.getProductIdx();
        } else {
        }
        return "redirect:/seller/mySellerPage?sellerIdx="+productDTO.getSellerIdx();
    }
    @GetMapping("/productImgUpdate")
    public String productImgUpdateForm(@RequestParam("sellerIdx") Integer sellerIdx, @RequestParam("productIdx") Integer
            productIdx, Model model, HttpServletRequest request) {

        ProductDTO productDTO = productService.findMainImg(sellerIdx, productIdx);
        log.info("sellerIdx",sellerIdx );
        log.info("productIdx", productIdx);
        model.addAttribute("sellerIdx", sellerIdx);
        model.addAttribute("productIdx", productIdx);
        model.addAttribute("product", productDTO);
        // 현재 제품 정보를 세션에 추가
       /* HttpSession session = request.getSession();
        session.setAttribute("currentProduct", productDTO);*/

        return "/product/productImgUpdateForm";
    }

    @PostMapping("/productImgUpdate")
    public String productImgUpdate(@Validated @ModelAttribute ProductMainImgUpdateDTO productMainImgUpdateDTO,
                                   BindingResult bindingResult, HttpServletRequest request) {
        log.info("mainImg = {}", productMainImgUpdateDTO);

        String uploadFolder = request.getServletContext().getRealPath("/product");

        String uploadFolderPath = getFolder(); // yyyy-MM-dd
        File uploadPath = new File(uploadFolder, uploadFolderPath);// /product/yyyy-MM-dd
        log.info("upload path = {}", uploadPath);
        // 저장경로가 없으면 생성
        if (uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }

        // 파일 업로드하는 로직
        UUID uuid = UUID.randomUUID();
        log.info("uuid{}" + uuid);

        String fileImgOriginal
                = productMainImgUpdateDTO.getMainImg().getOriginalFilename();
        log.info("fileImgOriginal={}"+  fileImgOriginal);

        String fileImgSaved = uuid.toString() + "_" + fileImgOriginal;
        log.info("fileImgSaved = {} " + fileImgSaved);

        String fileImgSavedUploadPath = uploadPath + File.separator + fileImgSaved;
        log.info("fileImgSavedUploadPath = {} " + fileImgSavedUploadPath);
        try {
            //Original은 원본 이름을 저장하는데 사용.
            productMainImgUpdateDTO.getMainImg().transferTo(new File(fileImgSavedUploadPath));

            String thumbnailFilename = "thumb_" + uuid.toString() + productMainImgUpdateDTO.getMainImg().getOriginalFilename();
            log.info("thumbnailFilename = {} " + thumbnailFilename);
            String thumbnailFilePath = uploadPath + File.separator + thumbnailFilename;
            log.info("thumbnailFilePath = {} " + thumbnailFilePath);

            Thumbnails.of(productMainImgUpdateDTO.getMainImg().getInputStream())
                    .size(100, 100)
                    .toFile(new File(thumbnailFilePath));

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
                bindingResult.addError(new FieldError("productSaveDTO", "",
                        "사진 등록에 실패했습니다. 다시 시도해주세요."));
                // 에러 처리 로직 추가
                log.error("productDTO, sellerIdx, 또는 productIdx가 null입니다.");
                // 예를 들어 다른 페이지로 리다이렉트하거나 에러 메시지를 표시할 수 있습니다.
                return "redirect:/errorPage";
            }
        } catch (IOException e) {
            log.error("파일 업로드에 실패했습니다.", e); // 파일 업로드 실패 메시지 로깅
            bindingResult.addError(new FieldError("productSaveDTO", "",
                    "파일 업로드에 실패했습니다. 다시 시도해주세요."));
            // 에러 처리 로직 추가
            log.error("파일 업로드에 실패했습니다.", e);
            // 예를 들어 다른 페이지로 리다이렉트하거나 에러 메시지를 표시할 수 있습니다.
            return "redirect:/errorPage";
        }
    }
    @GetMapping("/productDetailImgUpdate")
    public String productDetailImgUpdateForm(@RequestParam("sellerIdx") Integer sellerIdx, @RequestParam("productIdx") Integer
            productIdx, Model model, HttpServletRequest request) {

        ProductDTO productDTO = productService.findDetailImg(sellerIdx, productIdx);
        log.info("sellerIdx",sellerIdx );
        log.info("productIdx", productIdx);
        model.addAttribute("sellerIdx", sellerIdx);
        model.addAttribute("productIdx", productIdx);
        model.addAttribute("product", productDTO);
        // 현재 제품 정보를 세션에 추가
       /* HttpSession session = request.getSession();
        session.setAttribute("currentProduct", productDTO);*/

        return "/product/productDetail_ImgUpdateForm";
    }

    @PostMapping("/productDetailImgUpdate")
    public String productDetailImgUpdate(@Validated @ModelAttribute ProductDetailImgUpdateDTO productDetailImgUpdateDTO,
                                   BindingResult bindingResult, HttpServletRequest request) {
        log.info("detailImg = {}", productDetailImgUpdateDTO);

        String uploadFolder = request.getServletContext().getRealPath("/product");

        String uploadFolderPath = getFolder(); // yyyy-MM-dd
        File uploadPath = new File(uploadFolder, uploadFolderPath);// /product/yyyy-MM-dd
        log.info("upload path = {}", uploadPath);
        // 저장경로가 없으면 생성
        if (uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }

        // 파일 업로드하는 로직
        UUID uuid = UUID.randomUUID();
        log.info("uuid{}" + uuid);

        String fileDetailOriginal
                = productDetailImgUpdateDTO.getDetailImg().getOriginalFilename();
        log.info("fileDetailOriginal={}"+  fileDetailOriginal);

        String fileDetailSaved = uuid.toString() + "_" + fileDetailOriginal;
        log.info("fileDetailSaved = {} " + fileDetailSaved);

        String fileDetailSavedUploadPath = uploadPath + File.separator + fileDetailSaved;
        log.info("fileImgSavedUploadPath = {} " + fileDetailSavedUploadPath);
        try {
            //Original은 원본 이름을 저장하는데 사용.
            productDetailImgUpdateDTO.getDetailImg().transferTo(new File(fileDetailSavedUploadPath));



            productDetailImgUpdateDTO.setProductIdx(productDetailImgUpdateDTO.getProductIdx());
            productDetailImgUpdateDTO.setSellerIdx(productDetailImgUpdateDTO.getSellerIdx());
            productDetailImgUpdateDTO.setProductDetailOriginal(fileDetailOriginal);
            productDetailImgUpdateDTO.setProductDetailSaved(uploadFolderPath + "/" + fileDetailSaved);

            boolean result = productService.productDetailImgUpdate(productDetailImgUpdateDTO);
            if (result) {
                return "redirect:/product/productDetail?sellerIdx=" + productDetailImgUpdateDTO.getSellerIdx() + "&productIdx=" + productDetailImgUpdateDTO.getProductIdx();
            } else {
                log.error("사진 등록에 실패했습니다.");
                bindingResult.addError(new FieldError("productSaveDTO", "",
                        "사진 등록에 실패했습니다. 다시 시도해주세요."));
                // 에러 처리 로직 추가
                log.error("productDTO, sellerIdx, 또는 productIdx가 null입니다.");
                // 예를 들어 다른 페이지로 리다이렉트하거나 에러 메시지를 표시할 수 있습니다.
                return "redirect:/errorPage";
            }
        } catch (IOException e) {
            log.error("파일 업로드에 실패했습니다.", e); // 파일 업로드 실패 메시지 로깅
            bindingResult.addError(new FieldError("productDetailImgUpdateDTO", "",
                    "파일 업로드에 실패했습니다. 다시 시도해주세요."));
            // 에러 처리 로직 추가
            log.error("파일 업로드에 실패했습니다.", e);
            // 예를 들어 다른 페이지로 리다이렉트하거나 에러 메시지를 표시할 수 있습니다.
            return "redirect:/errorPage";
        }
    }






   /* @PostMapping("/productInfoUpdate")
    @ResponseBody
    public ResponseEntity<String> productUpdate(@Validated @ModelAttribute ProductSaveDTO productSaveDTO,
                                               HttpServletRequest request, BindingResult bindingResult) {
        try {
            *//*if (bindingResult.hasErrors()) {
                String errorMessage =
            }
        }*//*
            String uploadFolder = request.getServletContext().getRealPath("/product");
            String uploadFolderPath = getFolder(); // yyyy-MM-dd
            File uploadPath = new File(uploadFolder, uploadFolderPath);// /product/yyyy-MM-dd
            log.info("upload path = {}", uploadPath);
            // 저장경로가 없으면 생성
            if (uploadPath.exists() == false) {
                uploadPath.mkdirs();
            }
            updateImages(productSaveDTO, uploadPath);
            updateProductInfo(productSaveDTO);

            //session에 저장된 현재 product의 정보를 제거
            HttpSession session = request.getSession();
            session.removeAttribute("currentProduct");

            return ResponseEntity.ok("상품 정보가 성공적으로 업데이트 되었습니다.");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("파일 업로드에 실패했습니다. 다시 시도해주세요");
        }
    }

    private void updateImages(ProductSaveDTO productSaveDTO, File uploadPath) throws IOException {
        String existingMainImage = productSaveDTO.getExistingProductImg();
        String existingDetailImage = productSaveDTO.getExistingDetailImg();

        MultipartFile mainImageFile = productSaveDTO.getProductImg();
        if(mainImageFile != null && !mainImageFile.isEmpty()){
            updateImage(mainImageFile, uploadPath, existingMainImage, "main");
            }
        MultipartFile detailImageFile = productSaveDTO.getProductDetailImg();
        if (detailImageFile != null && !mainImageFile.isEmpty()) {
            updateImage(detailImageFile, uploadPath, existingDetailImage, "detail");
        }
    }

    private void updateImage(MultipartFile imageFile, File uploadPath, String existingImage, String type) throws IOException {
        if (existingImage != null && !existingImage.isEmpty()) {
            File existingFile = new File(uploadPath, existingImage);
            existingFile.delete();
        }
        saveImage(imageFile, uploadPath, type);
    }

    private void saveImage(MultipartFile imageFile, File uploadPath, String type) throws IOException {
        UUID uuid = UUID.randomUUID();
        String originalFileName = imageFile.getOriginalFilename();
        String saveFileName = uuid.toString() + "_" + originalFileName;
        String saveFileUploadPath = uploadPath + File.separator + saveFileName;
        File newImageFile = new File(saveFileUploadPath);
        imageFile.transferTo(newImageFile);
        String thubmnailFileName = "thumb" + uuid.toString() + originalFileName;

    }

    private void updateProductInfo(ProductSaveDTO productSaveDTO) {
    }

    // 취소 버튼을 클릭할 경우 상세 페이지로 이동
    @GetMapping("/cancelUpdate")
    public String cancelUpdate(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("currentProduct"); // 세션에 저장된 현재 제품 정보를 제거
        return "redirect:/product/detail";
    }

    // 초기화 버튼을 클릭할 경우 세션에 저장된 현재 제품 정보를 다시 불러와서 모델에 추가
    @GetMapping("/resetUpdate")
    public String resetUpdate(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ProductDTO currentProduct = (ProductDTO) session.getAttribute("currentProduct");
        model.addAttribute("sellerIdx", currentProduct.getSellerIdx());
        model.addAttribute("product", currentProduct);
        return "/product/productUpdateForm";
    }*/


    // 취소 버튼을 클릭할 경우 세션에 저장된 현재 제품 정보를 불러와서 다시 모델에 추가
    /*@GetMapping("/cancelUpdate")
    public String cancelUpdate(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ProductDTO currentProduct = (ProductDTO) session.getAttribute("currentProduct");
        model.addAttribute("sellerIdx", currentProduct.getSellerIdx());
        model.addAttribute("productIdx", currentProduct.getProductIdx();
        model.addAttribute("product", currentProduct);
        return "/product/productUpdateForm";
    }
    // 초기화 버튼을 클릭할 경우
    @GetMapping("/resetUpdateForm")
    public String resetUpdateForm(Model model, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            ProductDTO currentProduct = (ProductDTO) session.getAttribute("currentProduct");

            if (currentProduct != null) {
                model.addAttribute("sellerIdx", currentProduct.getSellerIdx());
                model.addAttribute("productInx", currentProduct.getProductIdx());
                model.addAttribute("product", currentProduct);
            }

            // 초기화 버튼 클릭 시 세션에 저장된 현재 제품 정보를 유지하고 업데이트 폼으로 이동
            return "/product/productUpdateForm";
        } catch (Exception e) {
            // 오류 처리 로직 추가
            return "/product/productUpdateForm"; // 또는 오류 페이지로 리다이렉트 또는 포워딩
        }
    }*/
    /*@GetMapping("/cancelUpdate")
    public String cancelUpdate(HttpSession session) {
        // 취소 버튼 클릭 시 세션에 저장된 현재 제품 정보를 제거
        session.removeAttribute("currentProduct");

        // 상품 상세보기 페이지로 이동
        return "redirect:/product/productDetail";
    }*/




    /*private void updateMainImg(MultipartFile mainImgFile, File uploadPath, String existingMainImg) throws IOException{
        if (existingMainImg != null && !existingMainImg.isEmpty()) {
            File existingMainImgFile = new File(uploadPath, mainImgFile);
            existingMainImgFile.delete();
        }
        UUID uuid = UUID.randomUUID();
        String newMainImgFile =
    }
    private void updateDetailImg(MultipartFile detailImgFile, File uploadPath, String existingDetailImg) {
    }*/

    //, MultipartFile Img, MultipartFile Detail, HttpServletRequest req)








    // 이무현


    // 유지호


    // 변재혁

}


