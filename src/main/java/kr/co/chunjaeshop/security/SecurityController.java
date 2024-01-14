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
import javax.servlet.http.HttpSession;
import java.security.Principal;

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

    @GetMapping(value = "/sample/customer")
    public void sampleCustomer(Principal principal, HttpServletResponse httpServletResponse) {
        log.info("principal = {}", principal);
    }

    @GetMapping(value = "/sample/seller")
    public void sampleSeller(Principal principal, HttpServletResponse httpServletResponse) {
        log.info("principal = {}", principal);
    }

    @GetMapping(value = "/sample/admin")
    public void sampleAdmin(Principal principal, HttpServletResponse httpServletResponse) {
        log.info("principal = {}", principal);
    }

    @GetMapping(value = "/session-test")
    public void sessionTest(HttpSession httpSession) {
        log.info("httpSession id = {}", httpSession.getId());
        httpSession.invalidate();
    }

    @GetMapping(value = "/seller-login-fail")
    public String sellerLoginFail(Principal principal,
                                HttpServletResponse httpServletResponse,
                                HttpSession httpSession) {
        log.info("principal = {}", principal);
        log.info("session id = {}", httpSession.getId());
        String sellerLoginFailMsg = (String) httpSession.getAttribute("sellerLoginFailMsg");
        log.info("sellerLoginFailMsg = {}", sellerLoginFailMsg);
        httpSession.invalidate();
        log.info("after invalidate session id = {}", httpSession.getId());
        return "common/customLogin";
    }
}
