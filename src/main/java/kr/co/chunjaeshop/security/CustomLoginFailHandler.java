package kr.co.chunjaeshop.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
public class CustomLoginFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        log.error("login failed...");
        HttpSession session = request.getSession();
        session.setAttribute("loginFailMsg", "아이디 또는 비밀번호를 잘못 입력했습니다");
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
