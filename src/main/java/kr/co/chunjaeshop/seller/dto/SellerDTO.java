package kr.co.chunjaeshop.seller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SellerDTO {
    private Integer sellerIdx;
    private String sellerId;
    private String sellerName;
    private String sellerEmail;
    private String sellerPhone;
    private String sellerPassword;
    private LocalDateTime sellerJoined;
    private String sellerZipcode;
    private String sellerAddress1;
    private String sellerAddress2;
    private Integer sellerIncome;
    private String sellerAuthority;

    // 남원우


    // 최경락


    // 이무현
    private Integer sellerRecognize;
    private String sellerRejectReason;

    // 유지호


    // 변재혁

}
