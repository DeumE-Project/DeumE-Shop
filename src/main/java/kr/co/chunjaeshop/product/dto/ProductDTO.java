package kr.co.chunjaeshop.product.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Integer productIdx; // primary key, auto increment
    private Integer sellerIdx; // 외래키
    private Integer categoryIdx; // 외래키
    private String productName; // 상품명
    private String productExplain; // 상품 간단 설명
    private Integer productPrice; // 한 개당 가격
    private Integer productStock; // 상품 재고
    private String productThumbSaved; // 상품 리스트 페이지에서 보여질 썸네일 파일명
    private String productImgOriginal; // 상품 메인 이미지 판매자가 올린 원래 파일명
    private String productImgSaved; // 상품 메인이미지 서버에 저장된 파일명
    private String productDetailOriginal; // 상품 상세 설명 이미지 판매자가 올린 원래 파일명
    private String productDetailSaved; // 상품 상세 설명 이미지 서버에 저장된 파일명
    private Integer productStatus; // 1번: 판매가능 / 0번: 품절(판매중지) [기본 값은 1번]
    private Integer productSales; // 판매된 개수

    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁
    private String sellerName;
}
