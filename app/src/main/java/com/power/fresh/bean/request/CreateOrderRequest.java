package com.power.fresh.bean.request;

import androidx.annotation.NonNull;

import com.power.common_opensurce.App;

import java.util.List;

/**
 * @author AlienChao
 * @date 2020/05/20 17:11
 */
public class CreateOrderRequest {

    /**
     * checkCartDTOS : [{"num":1,"commodityId":34,"cartId":207,"normsId":154}]
     * businessId : 6
     */

    private int businessId;
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

    public static class CheckCartDTOSBean {

        @NonNull
        @Override
        public String toString() {
            return App.getmGson().toJson(this);
        }

        /**
         * num : 1
         * commodityId : 34
         * cartId : 207
         * normsId : 154
         */

        private int num;
        private int commodityId;
        private Integer cartId;
        private int normsId;

//        private String name;
//        private String simple;
//        private String imgUrl;
//        private String specification;
//        private String price;


        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public Integer getCartId() {
            return cartId;
        }

        public void setCartId(Integer cartId) {
            this.cartId = cartId;
        }

        public int getNormsId() {
            return normsId;
        }

        public void setNormsId(int normsId) {
            this.normsId = normsId;
        }
    }
}
