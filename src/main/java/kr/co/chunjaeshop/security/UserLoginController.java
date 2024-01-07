package kr.co.chunjaeshop.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Log4j2
public class UserLoginController {
    @GetMapping(value = "/login")
    public String loginForm(Principal principal, HttpServletRequest httpServletRequest, Model model) {
        log.info("principal = {}", principal);
        /*if (principal != null) {
            return "redirect:/";
        }*/
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("loginFailMsg") != null) {
            model.addAttribute("loginFailMsg", session.getAttribute("loginFailMsg"));
            session.removeAttribute("loginFailMsg");
        }
        return "common/customLogin";
    }
}
