package kr.co.chunjaeshop.seller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private String sellerTaxId;

    public String getSellerJoinedDateStr() {
        if (this.sellerJoined != null) {
            return this.sellerJoined.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            return "no date available";
        }
    }
    // 유지호


    // 변재혁

}
