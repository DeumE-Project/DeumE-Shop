package kr.co.chunjaeshop.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String loginForm(Principal principal,
                            HttpServletRequest httpServletRequest,
                            Model model,
                            HttpSession httpSession) {
        log.info("principal = {}", principal);
        log.info("session id = {}", httpSession.getId());
        /*if (principal != null) {
            return "redirect:/";
        }*/
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("loginFailMsg") != null) {
            log.info("loginFailMsg = {}", session.getAttribute("loginFailMsg"));
            model.addAttribute("loginFailMsg", session.getAttribute("loginFailMsg"));
            session.removeAttribute("loginFailMsg");
        }
        if (session.getAttribute("sellerLoginFailMsg") != null) {
            log.info("sellerLoginFailMsg = {}", session.getAttribute("sellerLoginFailMsg"));
            model.addAttribute("sellerLoginFailMsg", session.getAttribute("sellerLoginFailMsg"));
            session.removeAttribute("sellerLoginFailMsg");
        }
        SecurityContextHolder.clearContext();
        httpSession.invalidate();
        return "common/customLogin";
    }
}
