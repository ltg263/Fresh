package com.power.fresh.bean.home;

import androidx.annotation.NonNull;

import com.power.common_opensurce.App;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AlienChao
 * @date 2020/05/12 16:19
 */
public class HomeGouponBean {

    /**
     * list : [{"couponId":1,"couponName":"新人优惠券","expireTime":"2020-06-01 16:44:04","note":"11111","rate":0,"subAmount":10,"taskAmount":0,"type":1,"useType":1}]
     * listCount : 1
     */

    private int listCount;
    private List<ListBean> list;

    public int getListCount() {
        return listCount;
    }

    public void setListCount(int listCount) {
        this.listCount = listCount;
    }

    public List<ListBean> getList() {
        if (list == null) {
            list = new ArrayList<>();
            list.add(new ListBean());
        }
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        @NonNull
        @Override
        public String toString() {
            return App.getmGson().toJson(this);
        }

        /**
         * couponId : 1
         * couponName : 新人优惠券
         * expireTime : 2020-06-01 16:44:04
         * note : 11111
         * rate : 0
         * subAmount : 10
         * taskAmount : 0
         * type : 1
         * useType : 1
         */



        private int couponId;
        private String couponName;
        private String expireTime;
        private String note;
        private int rate;
        private double subAmount;
        private int taskAmount;
        private int type;
        private int useType;
        private int userCouponId;

        public int getUserCouponId() {
            return userCouponId;
        }

        public void setUserCouponId(int userCouponId) {
            this.userCouponId = userCouponId;
        }

        public int getCouponId() {
            return couponId;
        }

        public void setCouponId(int couponId) {
            this.couponId = couponId;
        }

        public String getCouponName() {
            return couponName;
        }

        public void setCouponName(String couponName) {
            this.couponName = couponName;
        }

        public String getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(String expireTime) {
            this.expireTime = expireTime;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public int getRate() {
            return rate;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }

        public double getSubAmount() {
            return subAmount;
        }

        public void setSubAmount(double subAmount) {
            this.subAmount = subAmount;
        }

        public int getTaskAmount() {
            return taskAmount;
        }

        public void setTaskAmount(int taskAmount) {
            this.taskAmount = taskAmount;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUseType() {
            return useType;
        }

        public void setUseType(int useType) {
            this.useType = useType;
        }
    }
}
