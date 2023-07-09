package com.qw.pojo.bo;

/**
 * user add or modify address
 */
public class AddressBO {
    private String addressId;

    private String userId;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    private String receiver;
    private String mobile;
    private String province;
    private String city;
    private String district;
    private String detail;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "AddressBO{" +
                "userId='" + userId + '\'' +
                ", receiver='" + receiver + '\'' +
                ", mobile='" + mobile + '\'' +
                ", prov='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
