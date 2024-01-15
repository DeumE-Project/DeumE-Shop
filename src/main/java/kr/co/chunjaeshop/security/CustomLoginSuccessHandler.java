package kr.co.chunjaeshop.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication auth) throws IOException, ServletException {
        log.warn("login success!");
        log.info("session id = {}", request.getSession().getId());

        List<String> roleNames = new ArrayList<>();
        auth.getAuthorities().forEach(authority -> {
            roleNames.add(authority.getAuthority());
        });
        log.warn("roleNames = {}", roleNames);

        LoginUserDTO loginUserDTO = (LoginUserDTO) auth.getPrincipal();
        log.info("auth = {}", loginUserDTO);

        String redirectURL = "/";

        // 만약 seller 일 경우, sellerRecognize 상태 확인
        if (loginUserDTO.getType().equals("seller")) {
            if (loginUserDTO.getSellerRecognize().equals(0)) {
                log.error("아직 관리자 승인 안됨");
                setSellerLoginFailedMessageIntoSession(request, "아직 관리자가 승인하지 않아 로그인할 수 없습니다");
                redirectURL = "/login";
            } else if (loginUserDTO.getSellerRecognize().equals(1)) {
                log.info("판매자 로그인 가능");
            } else if (loginUserDTO.getSellerRecognize().equals(2)) {
                log.error("관리자가 승인 거부");
                setSellerLoginFailedMessageIntoSession(request, "판매자 로그인 거절: "
                        + loginUserDTO.getSellerRejectReason());
                redirectURL = "/login";
            }
        }
        response.sendRedirect(redirectURL);
    }

    private static void setSellerLoginFailedMessageIntoSession(HttpServletRequest request, String loginFailedMessage) {
        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute("sellerLoginFailMsg",  loginFailedMessage);
    }
}
