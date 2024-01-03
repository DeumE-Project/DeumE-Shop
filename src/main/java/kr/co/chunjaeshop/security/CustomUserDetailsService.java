package kr.co.chunjaeshop.security;

import kr.co.chunjaeshop.customer.dto.CustomerDTO;
import kr.co.mapper_interface.customer.CustomerMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Log4j2
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.warn("load user by username = {}", username);

        // username means id
        CustomerDTO customerDTO = customerMapper.getCustomerByUsernameForLogin(username);
        log.info("customerDTO = {}", customerDTO);

        if (customerDTO != null) {
            LoginUserDTO.UserVO userVO = LoginUserDTO.UserVO.builder()
                    .idx(customerDTO.getCustomerIdx())
                    .id(customerDTO.getCustomerId())
                    .name(customerDTO.getCustomerName())
                    .email(customerDTO.getCustomerEmail())
                    .password(customerDTO.getCustomerPassword())
                    .joined(customerDTO.getCustomerJoined())
                    .zipcode(customerDTO.getCustomerZipcode())
                    .address1(customerDTO.getCustomerAddress1())
                    .address2(customerDTO.getCustomerAddress2())
                    .authority(customerDTO.getCustomerAuthority())
                    .build();
            return new LoginUserDTO(userVO, userVO.getAuthorityList());
        } else {
            return null;
        }
    }
}
