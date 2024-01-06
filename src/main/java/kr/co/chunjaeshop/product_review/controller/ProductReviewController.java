package kr.co.chunjaeshop.product_review.controller;

import kr.co.chunjaeshop.product_review.dto.AttachFileDTO;
import kr.co.chunjaeshop.product_review.dto.ProductReviewDTO;
import kr.co.chunjaeshop.product_review.service.ProductReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    return str.replace("_",File.separator);
  }

  private boolean checkImageType(File file){
    try {
      String contentType = Files.probeContentType(file.toPath());

      return  contentType.startsWith("image");
    }catch (IOException e){
      e.printStackTrace();
    }
    return false;
  }
  @GetMapping("/uploadAjax")
  public void uploadAjax(){
    log.info("upload ajax");
  }

  @PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile, HttpServletRequest httpServletRequest) {

    log.info("update ajax post.........");

    List<AttachFileDTO> list = new ArrayList<>();
    //String uploadFolder = "E:\\upload";
    String uploadFolder = httpServletRequest.getServletContext().getRealPath("/review");

    // make folder -------
    //File uploadPath = new File(uploadFolder, getFolder());
    String uploadFolderPath = getFolder();
    File uploadPath = new File(uploadFolder, uploadFolderPath);

    //log.info("upload path: " + uploadPath);

    if (uploadPath.exists() == false) {
      uploadPath.mkdir();
    }
    // make yyyy-MM-dd folder


    for (MultipartFile multipartFile : uploadFile) {
      log.info("-------------------------------------");
      log.info("Upload File Name: " + multipartFile.getOriginalFilename());
      log.info("Upload File Size: " + multipartFile.getSize());

      AttachFileDTO attachDTO = new AttachFileDTO();

      String uploadFileName = multipartFile.getOriginalFilename();

      // IE has file path
      uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);

      log.info("only file name: " + uploadFileName);
      attachDTO.setFileName(uploadFileName);

      UUID uuid = UUID.randomUUID();

      uploadFileName = uuid.toString() + "_" + uploadFileName;


      try {
        File saveFile = new File(uploadPath, uploadFileName);
        multipartFile.transferTo(saveFile);

        attachDTO.setUuid(uuid.toString());
        attachDTO.setUploadPath(uploadFolderPath);

        // check image type file
        if (checkImageType(saveFile)) {

          attachDTO.setImage(true);

          FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
          Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
          thumbnail.close();
        }

        // add to List
        list.add(attachDTO);

      } catch (Exception e) {
        //log.error(e.getMessage());
        e.printStackTrace();
      } // end catch

    } // end for
    return new ResponseEntity<>(list, HttpStatus.OK);
  }
  @GetMapping("/display")
  @ResponseBody
  public ResponseEntity<byte[]> getFile(@RequestParam("fileName") String fileName, HttpServletRequest httpServletRequest) {

    log.info("fileName: " + fileName);
    String uploadFolder = httpServletRequest.getServletContext().getRealPath("/review");

    try {
      fileName = URLDecoder.decode(fileName, "UTF-8");
      File file = new File(uploadFolder, fileName);

      if (file.exists()) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", Files.probeContentType(file.toPath()));
        return new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (IOException e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  @PostMapping("/deleteFile")
  @ResponseBody
  public ResponseEntity<String> deleteFile(String fileName, String type, HttpServletRequest httpServletRequest) {

    log.info("deleteFile: " + fileName);

    String uploadFolder = httpServletRequest.getServletContext().getRealPath("/review");

    try {
      fileName = URLDecoder.decode(fileName, "UTF-8");
      File file = new File(uploadFolder, fileName);

      if (file.exists()) {
        file.delete();

        if (type.equals("image")) {
          String largeFileName = file.getAbsolutePath().replace("s_", "");
          log.info("largeFileName: " + largeFileName);

          File largeFile = new File(largeFileName);
          if (largeFile.exists()) {
            largeFile.delete();
          }
        }
        return new ResponseEntity<>("deleted", HttpStatus.OK);
      } else {
        return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
      }
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      return new ResponseEntity<>("Error decoding file name", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


    @GetMapping("/save")
    public String saveForm(){
      return "review/reviewSave";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute @Valid ProductReviewDTO productReviewDTO, BindingResult bindingResult) {

      if (bindingResult.hasErrors()) {
      log.info("test0");
        return "review/reviewSave";
      }
      log.info("test");
      int saveResult = productReviewService.reviewSave(productReviewDTO);
      if (saveResult > 0) {

        return "redirect:/product/paging";
      } else {
        log.info("test1");
        return "review/reviewSave";
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
