package com.ssgsakk.ssgdotcom.shippingAddress.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddShippingAddressRequestVo {
    private String addressNickname;
    private String receiverName;
    private String reciverMobileNum;
    private String zipCode;
    private String roadAddress;
    private String jibunAddress;
    private String detailAddress;

    @Builder
    public AddShippingAddressRequestVo(String addressNickname, String receiverName, String reciverMobileNum, String zipCode, String roadAddress, String jibunAddress, String detailAddress) {
        this.addressNickname = addressNickname;
        this.receiverName = receiverName;
        this.reciverMobileNum = reciverMobileNum;
        this.zipCode = zipCode;
        this.roadAddress = roadAddress;
        this.jibunAddress = jibunAddress;
        this.detailAddress = detailAddress;
    }
}