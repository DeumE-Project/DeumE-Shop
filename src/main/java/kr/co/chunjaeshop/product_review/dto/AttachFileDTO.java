package kr.co.chunjaeshop.product_review.dto;


import lombok.Data;

@Data
public class AttachFileDTO {

  private String fileName;
  private String uploadPath;
  private String uuid;
  private boolean image;
}
