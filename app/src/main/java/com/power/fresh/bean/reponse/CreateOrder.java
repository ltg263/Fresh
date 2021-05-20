package com.power.fresh.bean.reponse;

/**
 * @author AlienChao
 * @date 2020/05/21 14:30
 */
public class CreateOrder {

    /**
     * expireTime : 2020-05-21 14:44:04
     * orderId : 120
     * orderNo : wxpay200521142904001919
     * payType : 1
     * price : 15
     */

    private String expireTime;
    private int orderId;
    private String orderNo;
    private int payType;
    private double price;

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
