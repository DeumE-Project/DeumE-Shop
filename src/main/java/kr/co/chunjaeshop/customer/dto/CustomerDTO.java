package kr.co.chunjaeshop.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {
    private Integer customerIdx;
    private String customerId;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String customerPassword;
    private LocalDateTime customerJoined;
    private String customerZipcode;
    private String customerAddress1;
    private String customerAddress2;
    private Integer customerMoney;
    private String customerAuthority;

    // 남원우


    // 최경락


    // 이무현


    // 유지호


    // 변재혁

}
