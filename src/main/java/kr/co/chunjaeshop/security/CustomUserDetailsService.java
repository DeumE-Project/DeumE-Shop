package kr.co.chunjaeshop.security;

import kr.co.mapper_interface.customer.CustomerMapper;
import kr.co.mapper_interface.seller.SellerMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

@Log4j2
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private SellerMapper sellerMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.warn("load user by username = {}", username);

        if (!StringUtils.hasText(username)) {
            throw new UsernameNotFoundException("Username is empty");
        }

        String type = username.substring(username.lastIndexOf("=") + 1, username.length());
        String realUsername = username.substring(0, username.lastIndexOf("="));
        log.info("type = {}", type);
        log.info("realUsername = {}", realUsername);

        LoginUserDTO.UserVO result = null;

        if (type.equals("customer")) {
            result = customerMapper.loginCustomer(realUsername);
        } else if (type.equals("seller")) {
            result = sellerMapper.loginSeller(realUsername);
        } else {
            throw new UsernameNotFoundException("Invalid member type");
        }

        if (result != null) {
            LoginUserDTO.UserVO userVO = LoginUserDTO.UserVO.builder()
                    .idx(result.getIdx())
                    .id(result.getId())
                    .name(result.getName())
                    .email(result.getEmail())
                    .password(result.getPassword())
                    .joined(result.getJoined())
                    .zipcode(result.getZipcode())
                    .address1(result.getAddress1())
                    .address2(result.getAddress2())
                    .authority(result.getAuthority())
                    .type(type)
                    .sellerRecognize(result.getSellerRecognize())
                    .sellerRejectReason(result.getSellerRejectReason())
                    .build();
            return new LoginUserDTO(userVO, userVO.getAuthorityList());
        } else {
            throw new UsernameNotFoundException("User not exist with username: " + username);
        }
    }
}
