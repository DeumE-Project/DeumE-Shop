package kr.co.chunjaeshop.security;

import kr.co.chunjaeshop.customer.service.CustomerService;
import kr.co.chunjaeshop.seller.service.SellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
@Log4j2
public class UserRegisterController {
    private final CustomerService customerService;
    private final SellerService sellerService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String registerForm(@ModelAttribute RegisterFormDTO registerFormDTO) {
        log.info("registerFormDTO = {}", registerFormDTO);
        return "common/customRegister";
    }

    @PostMapping
    public String registerSubmit(@Validated @ModelAttribute RegisterFormDTO registerFormDTO,
                                 BindingResult bindingResult, Model model) {
        log.info("registerFormDTO = {}", registerFormDTO);

        if (StringUtils.hasText(registerFormDTO.getPassword1()) && StringUtils.hasText((registerFormDTO.getPassword2()))) {
            if (!registerFormDTO.getPassword1().equals(registerFormDTO.getPassword2())) {
                bindingResult.addError(new FieldError("registerFormDTO", "passwordErrorMsg",
                        "비밀번호와 비밀번호 확인이 일치하지 않습니다"));
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("registerFormDTO has errors!");
            bindingResult.addError(new FieldError("registerFormDTO", "globalError",
                    "회원가입 양식에 맞게 모든 값을 입력해주세요"));
            return "common/customRegister";
        }
        // 비밀번호 암호화
        registerFormDTO.setPassword1(passwordEncoder.encode(registerFormDTO.getPassword1()));
        boolean registerResult = false;
        if (registerFormDTO.getType().equals("customer")) {
            log.info("고객 회원가입");
            registerResult = customerService.customerRegister(registerFormDTO);
        } else if (registerFormDTO.getType().equals("seller")) {
            log.info("고객 회원가입");
            registerResult = sellerService.sellerRegister(registerFormDTO);
        } else {
            log.info("register type error");
        }
        if (registerResult) {
            log.info("회원가입 성공");
            return "redirect:/login";
        } else {
            return "common/customRegister";
        }
    }

    @PostMapping(value = "/id-duplicate-check", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> idDuplicationCheck(@RequestParam String id) {
        log.info("id = {}", id);
        // 고객이 이미 사용 중인지 확인
        boolean customerAlreadyUsed = customerService.idDuplicationCheck(id);
        if (customerAlreadyUsed) {
            return ResponseEntity.badRequest().body("cannotuse");
        }
        boolean sellerAlreadyUsed = sellerService.idDuplicationCheck(id);
        if (sellerAlreadyUsed) {
            return ResponseEntity.badRequest().body("cannotuse");
        }
        return ResponseEntity.ok("canuse");
    }
}
