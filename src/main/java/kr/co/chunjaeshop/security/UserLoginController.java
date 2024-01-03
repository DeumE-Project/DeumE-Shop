package kr.co.chunjaeshop.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
public class UserLoginController {
    @GetMapping(value = "/login")
    public String loginForm(Model model) {
        return "common/customLogin";
    }
}
