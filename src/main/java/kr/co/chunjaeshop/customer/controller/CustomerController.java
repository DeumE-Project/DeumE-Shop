package kr.co.chunjaeshop.customer.controller;

import kr.co.chunjaeshop.customer.dto.AddToCartForm;
import kr.co.chunjaeshop.customer.dto.CartResult;
import kr.co.chunjaeshop.customer.dto.CustomerDTO;
import kr.co.chunjaeshop.customer.service.CustomerService;
import kr.co.chunjaeshop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
@Log4j2
public class CustomerController {
    private final CustomerService customerService;

    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁
    private final CustomerService cService;

    @GetMapping(value = "/test")
    public void test(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        log.info("/customer/test");
        String realPath = httpServletRequest.getServletContext().getRealPath("/upload");
        log.info("realPath = {}", realPath);
    }

    @GetMapping(value = "/all-customer-list")
    public void getAllCustomerList(HttpServletResponse httpServletResponse) {
        List<CustomerDTO> allCustomerList = customerService.getAllCustomerList();
        log.info(allCustomerList);
    }

    @GetMapping(value = "/uploadAjaxAction")
    public String uploadAjaxActionForm() {
        return "customer/customerTest";
    }

    @PostMapping(value = "/uploadAjaxAction")
    public void uploadAjaxPost(MultipartFile[] uploadFile, HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) {
        String uploadFolder = httpServletRequest.getServletContext().getRealPath("/upload");

        File uploadPath = new File(uploadFolder, getFolder());
        log.info("upload path = {}", uploadPath);

        if (uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }

        for (MultipartFile multipartFile : uploadFile) {
            log.info("-----start-----");
            log.info("upload file name = {}", multipartFile.getOriginalFilename());
            log.info("upload file size = {}", multipartFile.getSize());
            String uploadFileName = multipartFile.getOriginalFilename();

            // IE has file path
            uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
            log.info("only file name = {}", uploadFileName);

            UUID uuid = UUID.randomUUID();
            uploadFileName = uuid.toString() + "_" + uploadFileName;

            File saveFile = new File(uploadPath, uploadFileName);
            try {
                multipartFile.transferTo(saveFile);
                // check file type
                if (checkImageType(saveFile)) {
                    FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "thumb_" + uploadFileName));
                    Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 200, 300);
                    thumbnail.close();
                }
            } catch (Exception e) {
                log.error("file upload error!");
                e.printStackTrace();
                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            }
            log.info("-----end-----");
        } // end for
        httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);
    } // end entire method

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

    private String getFolder() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = sdf.format(date);
        String replaced = str.replace("-", File.separator);
        log.info("replaces = {}", replaced);
        return replaced;
    }

    @PostMapping(value = "/add-cart")
    public String addToCart(@ModelAttribute AddToCartForm addToCartForm) {
        log.info("addToCartForm = {}", addToCartForm);
        addToCartForm.setCustomerIdx(8);
        CartResult result = cService.addToCart(addToCartForm);
        return null;
    }
}
