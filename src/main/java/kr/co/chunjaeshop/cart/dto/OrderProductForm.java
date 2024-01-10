package kr.co.chunjaeshop.cart.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class OrderProductForm {
    private Integer orderIdx; // db에서 받아오는 값
    private Integer customerIdx; // 시큐리티에서 받는 값
    private Integer totalPrice; // 카트 조회해서 계산필요

    @NotBlank(message = "우편번호는 필수 입력값입니다")
    @Pattern(regexp = "^(\\d{3}-\\d{3}|\\d{5})$",
            message = "올바른 우편번호를 입력해주세요")
    private String zipcode; // 사용자에게

    @NotBlank(message = "주소는 필수 입력값입니다")
    private String orderAddress1; // 사용자에게

    @NotBlank(message = "상세주소는 필수 입력값입니다")
    private String orderAddress2; // 사용자에게

    private String orderRequest; // 사용자에게

    @NotBlank(message = "수취인은 필수 입력값입니다")
    private String orderName; // 사용자에게

    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "이메일 형식으로 입력해주세요")
    private String orderEmail;

    @Pattern(regexp = "^0([0-9]{2,3})([0-9]{3,4})([0-9]{4})$",
            message = "- 기호를 제외한 숫자만 입력해주세요")
    private String orderPhone; // 사용자에게

    private Integer cartIdx; // input hidden 으로...
}
