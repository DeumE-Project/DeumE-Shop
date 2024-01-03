package kr.co.chunjaeshop.security;

import kr.co.mapper_interface.customer.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@Log4j2
public class SecurityController {

    private final PasswordEncoder passwordEncoder;
    private final CustomerMapper customerMapper;

    @GetMapping(value = "/accessError")
    public void accessDenied(HttpServletResponse httpServletResponse, Authentication auth, Model model) {
        log.info("auth = {}", auth);
    }

    @GetMapping(value = "/customLogin")
    public String loginInput(Model model) {
        return "common/customLogin";
    }

    @GetMapping(value = "/customRegister")
    public String registerForm(@ModelAttribute("registerFormDTO") RegisterFormDTO registerFormDTO, Model model) {
        log.info("registerFormDTO = {}", registerFormDTO);
        return "common/customRegister";
    }

    @PostMapping(value = "/customRegister")
    public String registerPost(@Validated @ModelAttribute("registerFormDTO") RegisterFormDTO registerFormDTO,
                               BindingResult bindingResult, Model model) {
        log.info("registerFormDTO = {}", registerFormDTO);
        if (bindingResult.hasErrors()) {
            log.info("registerFormDTO has errors!");
            bindingResult.addError(new FieldError("registerFormDTO", "globalError", "회원가입 양식에 맞게 모든 값을 입력해주세요"));
            return "common/customRegister";
        }
        if (registerFormDTO.getType().equals("customer")) {

        } else if (registerFormDTO.getType().equals("seller")) {

        } else {
            log.info("register type error");
        }
        registerFormDTO.setPassword1(passwordEncoder.encode(registerFormDTO.getPassword1()));
        customerMapper.customerRegister(registerFormDTO);
        return "redirect:/customLogin";
    }
}
