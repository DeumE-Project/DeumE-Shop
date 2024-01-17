package kr.co.chunjaeshop.product.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

@Data
public class ProductSaveDTO {
    @NotNull(message = "상품 카테고리는 필수 선택사항입니다")
    private Integer categoryIdx;

    @NotBlank(message = "상품명은 필수 입력값입니다")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣]{1,20}$", message = "상품명은 한국어, 영어, 숫자로 이루어진 20글자 이하의 문자열이어야 합니다")
    private String productName;


    @NotBlank(message = "상품 간단 설명은 필수 입력값입니다")
    @Size(max = 50, message = "상품 간단 설명은 50글자 이하로 입력해주세요")
    private String productExplain;

    @NotNull(message = "상품 가격은 필수 입력값입니다")
    @PositiveOrZero(message = "상품 가격은 0원 이상만 입력할 수 있습니다")
    @Max(value = 100000, message = "상품 가격은 10만원 이하만 입력할 수 있습니다")
    private Integer productPrice;

    @NotNull(message = "상품 재고량은 필수 입력값입니다")
    @PositiveOrZero(message = "상품 재고량은 0개 이상만 입력할 수 있습니다")
    @Max(value = 1000, message = "상품 재고량은 1000개 이하만 입력할 수 있습니다")
    private Integer productStock;
    private MultipartFile productImg;
    private MultipartFile productDetailImg;



    
}
