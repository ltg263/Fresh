package com.power.fresh.bean.alipay;

/**
 * @author AlienChao
 * @date 2020/05/31 19:52
 */
public class PayQueryBean {

    /**
     * payType : 2
     * orderId : 164
     * updateTime : 2020-05-31 20:18:12
     * status : SUCCESS
     */

    private int payType;
    private int orderId;
    private String updateTime;
    private String status;

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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
