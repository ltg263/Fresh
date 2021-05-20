package com.power.fresh.bean.reponse;

import com.google.gson.annotations.SerializedName;

/**
 * @author AlienChao
 * @date 2020/05/22 10:46
 */
public class PayOrderReponse {

    /**
     * package : Sign=WXPay
     * appid : wx7ac935e2df71c059
     * sign : BDED61CE29650221B7FD8EF306CE9999
     * partnerid : 1592850681
     * prepayid : wx161528254874087eb2be9d291986889200
     * noncestr : ac070ea17ca6409eb3723a563cfe1f9c
     * timestamp : 1589614119
     */

    @SerializedName("package")
    private String packageX;
    private String appid;
    private String sign;
    private String partnerid;
    private String prepayid;
    private String noncestr;
    private String timestamp;
    private String orderInfo;

    private int payType;
    private int orderId;
    private String status;

    public String getPackageX() {
        return packageX == null ? "" : packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getAppid() {
        return appid == null ? "" : appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSign() {
        return sign == null ? "" : sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPartnerid() {
        return partnerid == null ? "" : partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid == null ? "" : prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr == null ? "" : noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp == null ? "" : timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getOrderInfo() {
        return orderInfo == null ? "" : orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status == null ? "" : status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
