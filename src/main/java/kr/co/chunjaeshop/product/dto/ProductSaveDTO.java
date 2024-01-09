package kr.co.chunjaeshop.product.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class ProductSaveDTO {
    @NotNull(message = "상품 카테고리는 필수 선택사항입니다")
    private Integer categoryIdx;

    @NotBlank(message = "상품명은 필수 입력값입니다")
    private String productName;

    @NotBlank(message = "상품 간단 설명은 필수 입력값입니다")
    private String productExplain;

    @NotNull(message = "상품 가격은 필수 입력값입니다")
    @PositiveOrZero(message = "상품 가격은 0원 이상만 입력할 수 있습니다")
    private Integer productPrice;

    @NotNull(message = "상품 재고량은 필수 입력값입니다")
    @PositiveOrZero(message = "상품 재고량은 0개 이상만 입력할 수 있습니다")
    private Integer productStock;
    private MultipartFile productImg;
    private MultipartFile productDetailImg;
    
}
