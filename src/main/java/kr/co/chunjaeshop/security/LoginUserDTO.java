package kr.co.chunjaeshop.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class LoginUserDTO extends User {
    private static final long serialVersionUID = 1L;

    private UserVO userVO;
    private Integer idx;
    private String id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private LocalDateTime joined;
    private String zipcode;
    private String address1;
    private String address2;
    private String authority;

    public LoginUserDTO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public LoginUserDTO(UserVO userVO, List<SimpleGrantedAuthority> authorityList) {
        super(userVO.getId(), userVO.getPassword(), authorityList);
        this.userVO = userVO;
        this.idx = userVO.getIdx();
        this.id = userVO.getId();
        this.name = userVO.getName();
        this.email = userVO.getEmail();
        this.phone = userVO.getPhone();
        this.password = userVO.getPassword();
        this.joined = userVO.getJoined();
        this.zipcode = userVO.getZipcode();
        this.address1 = userVO.getAddress1();
        this.address2 = userVO.getAddress2();
        this.authority = userVO.getAuthority();
    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class UserVO {
        private Integer idx;
        private String id;
        private String name;
        private String email;
        private String phone;
        private String password;
        private LocalDateTime joined;
        private String zipcode;
        private String address1;
        private String address2;
        private String authority;

        public List<SimpleGrantedAuthority> getAuthorityList() {
            List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
            authorityList.add(new SimpleGrantedAuthority(this.getAuthority()));
            return authorityList;
        }
    }
}
