package kr.co.chunjaeshop.order_product.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderProductDTO {
    private Integer orderIdx;
    private Integer customerIdx;
    private LocalDateTime orderDate;
    private Integer orderTotalPrice;
    /*private Integer orderStatus;*/
    private String orderZipcode;
    private String orderAddress1;
    private String orderAddress2;
    private Integer orderPayment;
    private String orderRequest;
    private String orderName;
    private String orderPhone;

    // 남원우


    // 최경락


    // 이무현


    // 유지호
    private String productName;
    private String customerName;
    private Integer productPrice;
    private Integer productCount;
    private String orderAddress;
    private String orderStatus;
    private Integer productIdx;


    // 변재혁

}
