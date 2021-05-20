package com.power.fresh.bean.bussiness;

import java.util.List;

/**
 * @author AlienChao
 * @date 2020/05/25 17:16
 */
public class 配送管理 {


    /**
     * list : [{"adminId":29,"balance":0,"cardUrl":"https://app.nbningjiang.com/ningjiangshengxian/upload/081DD8718FA441F3F3ACC502132E9C1D.jpg","cardUrlBack":"https://app.nbningjiang.com/ningjiangshengxian/upload/504295E2936D15FEE31C2CD9E6928BD6.jpg","copyBody":"","createTime":"2020-05-25 15:22:05","hasDelete":0,"id":4,"mobile":"15971498225","name":"配送员","status":31}]
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
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * adminId : 29
         * balance : 0
         * cardUrl : https://app.nbningjiang.com/ningjiangshengxian/upload/081DD8718FA441F3F3ACC502132E9C1D.jpg
         * cardUrlBack : https://app.nbningjiang.com/ningjiangshengxian/upload/504295E2936D15FEE31C2CD9E6928BD6.jpg
         * copyBody :
         * createTime : 2020-05-25 15:22:05
         * hasDelete : 0
         * id : 4
         * mobile : 15971498225
         * name : 配送员
         * status : 31
         */

        private int adminId;
        private int deliveryId;
        private int balance;
        private String cardUrl;
        private String cardUrlBack;
        private String copyBody;
        private String createTime;
        private int hasDelete;
        private int id;
        private String mobile;
        private String name;
        private int status;

        public int getDeliveryId() {
            return deliveryId;
        }

        public void setDeliveryId(int deliveryId) {
            this.deliveryId = deliveryId;
        }

        public int getAdminId() {
            return adminId;
        }

        public void setAdminId(int adminId) {
            this.adminId = adminId;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public String getCardUrl() {
            return cardUrl;
        }

        public void setCardUrl(String cardUrl) {
            this.cardUrl = cardUrl;
        }

        public String getCardUrlBack() {
            return cardUrlBack;
        }

        public void setCardUrlBack(String cardUrlBack) {
            this.cardUrlBack = cardUrlBack;
        }

        public String getCopyBody() {
            return copyBody;
        }

        public void setCopyBody(String copyBody) {
            this.copyBody = copyBody;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getHasDelete() {
            return hasDelete;
        }

        public void setHasDelete(int hasDelete) {
            this.hasDelete = hasDelete;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
