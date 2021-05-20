package com.power.fresh.bean.request;


import com.power.fresh.bean.request.CreateOrderRequest.CheckCartDTOSBean;

import java.util.List;


/**
 * @author AlienChao
 * @date 2020/05/20 17:11
 */
public class SaveOrderRequest {

    /**
     * checkCartDTOS : [{"num":1,"commodityId":34,"cartId":207,"normsId":154}]
     * businessId : 6
     */

    private int businessId;
    private Integer userCouponId;
    private int payType;
    private int checkAddressId;
    private String remark;

    public String getRemark() {
        return remark == null ? "" : remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }



    public Integer getUserCouponId() {
        return userCouponId;
    }

    public void setUserCouponId(Integer userCouponId) {
        this.userCouponId = userCouponId;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public int getCheckAddressId() {
        return checkAddressId;
    }

    public void setCheckAddressId(int checkAddressId) {
        this.checkAddressId = checkAddressId;
    }

    private List<CheckCartDTOSBean> checkCartDTOS;

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public List<CheckCartDTOSBean> getCheckCartDTOS() {
        return checkCartDTOS;
    }

    public void setCheckCartDTOS(List<CheckCartDTOSBean> checkCartDTOS) {
        this.checkCartDTOS = checkCartDTOS;
    }


}
