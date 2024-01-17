package kr.co.chunjaeshop.product.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
public class ProductDTO {
    private Integer productIdx; // primary key, auto increment
    private Integer sellerIdx; // 외래키
    private Integer categoryIdx; // 외래키
    private String productName; // 상품명

    @NotBlank(message = "상품 간단 설명은 필수 입력값입니다")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣\\s!@#$%^&*()_+\\-=,.<>/?\\[\\]{}'`~\\\\|]{1,50}$", message = "상품명은 특수문자와 띄어쓰기를 포함한 한국어, 영어, 숫자로 이루어진 50글자 이하의 문자열이어야 합니다")
    private String productExplain;

    @NotNull(message = "상품 가격은 필수 입력값입니다")
    @PositiveOrZero(message = "상품 가격은 0원 이상만 입력할 수 있습니다")
    @Max(value = 100000, message = "상품 가격은 10만원 이하만 입력할 수 있습니다")
    private Integer productPrice;

    @NotNull(message = "상품 재고량은 필수 입력값입니다")
    @PositiveOrZero(message = "상품 재고량은 0개 이상만 입력할 수 있습니다")
    @Max(value = 1000, message = "상품 재고량은 1000개 이하만 입력할 수 있습니다")
    private Integer productStock;

    private String productThumbSaved; // 상품 리스트 페이지에서 보여질 썸네일 파일명
    private String productImgOriginal; // 상품 메인 이미지 판매자가 올린 원래 파일명
    private String productImgSaved; // 상품 메인이미지 서버에 저장된 파일명
    private String productDetailOriginal; // 상품 상세 설명 이미지 판매자가 올린 원래 파일명
    private String productDetailSaved; // 상품 상세 설명 이미지 서버에 저장된 파일명
    private Integer productStatus; // 1번: 판매가능 / 0번: 품절(판매중지) [기본 값은 1번]
    private Integer productSales; // 판매된 개수
    private LocalDateTime productRegDate; // 상품 등록 날짜

    // 남원우


    // 최경락


    // 이무현


    // 유지호
    private String categoryName;

    // 변재혁
    private String sellerName;
}
