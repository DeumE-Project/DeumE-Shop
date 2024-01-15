package kr.co.chunjaeshop.product.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class ProductDetailImgUpdateDTO {
    private Integer sellerIdx;
    private Integer productIdx;

    private String productDetailOriginal; // 상품 상세 설명 이미지 판매자가 올린 원래 파일명
    private String productDetailSaved; // 상품 상세 설명 이미지 서버에 저장된 파일명

    private MultipartFile detailImg;
}
