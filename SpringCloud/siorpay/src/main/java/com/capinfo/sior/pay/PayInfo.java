package com.capinfo.sior.pay;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PayInfo {

     private String dateTime;
        private String branchNo;
        private String merchantNo;
        private String date;
        private String orderNo;
        private String amount;
        private String expireTimeSpan;
        private String payNoticeUrl;
        private String payNoticePara;
        private String returnUrl;
        private String cardType;
        private String agrNo;
    // private String agrNo;
    // private String merchantSerialNo;
        private String userID;
        private String mobile;
        private String lon;
        private String lat;
        private String riskLevel;
        private String signNoticeUrl;
        private String signNoticePara;
        private String merchantCssUrl;
        private String merchantBridgeName;

    public String getDateTime() {
        return dateTime;
    }

    public String getBranchNo() {
        return branchNo;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public String getDate() {
        return date;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public String getAmount() {
        return amount;
    }

    public String getExpireTimeSpan() {
        return expireTimeSpan;
    }

    public String getPayNoticeUrl() {
        return payNoticeUrl;
    }

    public String getPayNoticePara() {
        return payNoticePara;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public String getCardType() {
        return cardType;
    }

    public String getAgrNo() {
        return agrNo;
    }

    public String getUserID() {
        return userID;
    }

    public String getMobile() {
        return mobile;
    }

    public String getLon() {
        return lon;
    }

    public String getLat() {
        return lat;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public String getSignNoticeUrl() {
        return signNoticeUrl;
    }

    public String getSignNoticePara() {
        return signNoticePara;
    }

    public String getMerchantCssUrl() {
        return merchantCssUrl;
    }

    public String getMerchantBridgeName() {
        return merchantBridgeName;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setExpireTimeSpan(String expireTimeSpan) {
        this.expireTimeSpan = expireTimeSpan;
    }

    public void setPayNoticeUrl(String payNoticeUrl) {
        this.payNoticeUrl = payNoticeUrl;
    }

    public void setPayNoticePara(String payNoticePara) {
        this.payNoticePara = payNoticePara;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setAgrNo(String agrNo) {
        this.agrNo = agrNo;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public void setSignNoticeUrl(String signNoticeUrl) {
        this.signNoticeUrl = signNoticeUrl;
    }

    public void setSignNoticePara(String signNoticePara) {
        this.signNoticePara = signNoticePara;
    }

    public void setMerchantCssUrl(String merchantCssUrl) {
        this.merchantCssUrl = merchantCssUrl;
    }

    public void setMerchantBridgeName(String merchantBridgeName) {
        this.merchantBridgeName = merchantBridgeName;
    }
}
