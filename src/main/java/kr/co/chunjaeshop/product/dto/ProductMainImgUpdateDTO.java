package kr.co.chunjaeshop.product.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class ProductMainImgUpdateDTO {
    private Integer sellerIdx;
    private Integer productIdx;
    private String productThumbSaved; // 상품 리스트 페이지에서 보여질 썸네일 파일명
    private String productImgOriginal; // 상품 메인 이미지 판매자가 올린 원래 파일명
    private String productImgSaved;

    private MultipartFile mainImg;

}

