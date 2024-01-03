package kr.co.chunjaeshop.security;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class RegisterFormDTO {
    private Integer idx;

//    @NotBlank(message = "아이디는 필수 입력값입니다")
    @Pattern(regexp = "^[A-za-z0-9]{5,15}$",
             message = "아이디는 영문 소문자 및 숫자를 사용해서 최소 5자 최대 15자까지 가능합니다")
    private String id;

    @NotBlank(message = "이름은 필수 입력값입니다")
    private String name;

    @NotBlank(message = "이메일은 필수 입력값입니다")
    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;

//    @NotBlank(message = "비밀번호는 필수 입력값입니다")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$])(?=.*[0-9])[A-Za-z\\d!@#$]{5,16}$",
             message = "비밀번호는 5자리 이상 16자리 이하, 영어 대소문자 각각 한 개 이상, 숫자 한 개 이상, " +
                     "특수기호 !, @, #, $ 중 한 개 이상을 포함해야 합니다")
    private String password1;

//    @NotBlank(message = "비밀번호 확인은 필수 입력값입니다")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$])(?=.*[0-9])[A-Za-z\\d!@#$]{5,16}$",
             message = "비밀번호는 5자리 이상 16자리 이하, 영어 대소문자 각각 한 개 이상, 숫자 한 개 이상, " +
                     "특수기호 !, @, #, $ 중 한 개 이상을 포함해야 합니다")
    private String password2;

//    @NotBlank(message = "전화번호는 필수 입력값입니다")
    @Pattern(regexp = "^0([0-9]{2,3})([0-9]{3,4})([0-9]{4})$",
             message = "- 기호를 제외한 숫자만 입력해주세요")
    private String phone;

//    @NotBlank(message = "우편번호는 필수 입력값입니다")
    @Pattern(regexp = "^(\\d{3}-\\d{3}|\\d{5})$",
             message = "올바른 우편번호를 입력해주세요")
    private String zipcode;

    @NotBlank(message = "주소는 필수 입력값입니다")
    private String address1;

    @NotBlank(message = "상세 주소는 필수 입력값입니다")
    private String address2;

    @NotBlank(message = "회원구분은 필수 선택입니다")
    private String type;

    private String authority;

    private String globalError;
}
