package kr.co.chunjaeshop.security;

import kr.co.mapper_interface.customer.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
