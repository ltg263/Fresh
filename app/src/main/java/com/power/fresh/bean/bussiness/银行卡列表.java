package com.power.fresh.bean.bussiness;

import java.util.List;

/**
 * @author AlienChao
 * @date 2020/05/26 09:59
 */
public class 银行卡列表 {


    /**
     * list : [{"bankBranch":"Fuji","bankCode":"FJHXBC","bankLogo":"https://apimg.alipay.com/combo.png?d=cashier&t=FJHXBC","bankName":"福建海峡银行","bankNo":"gjjj","createTime":"2020-05-25 17:32:30","hasDefault":1,"id":11,"mobile":"ghjjj","name":"fhjjj","siteId":22,"status":1}]
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
         * bankBranch : Fuji
         * bankCode : FJHXBC
         * bankLogo : https://apimg.alipay.com/combo.png?d=cashier&t=FJHXBC
         * bankName : 福建海峡银行
         * bankNo : gjjj
         * createTime : 2020-05-25 17:32:30
         * hasDefault : 1
         * id : 11
         * mobile : ghjjj
         * name : fhjjj
         * siteId : 22
         * status : 1
         */

        private String bankBranch;
        private String bankCode;
        private String bankLogo;
        private String bankName;
        private String bankNo;
        private String createTime;
        private int hasDefault;
        private int id;
        private String mobile;
        private String name;
        private int siteId;
        private int status;

        public String getBankBranch() {
            return bankBranch;
        }

        public void setBankBranch(String bankBranch) {
            this.bankBranch = bankBranch;
        }

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public String getBankLogo() {
            return bankLogo;
        }

        public void setBankLogo(String bankLogo) {
            this.bankLogo = bankLogo;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBankNo() {
            return bankNo;
        }

        public void setBankNo(String bankNo) {
            this.bankNo = bankNo;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getHasDefault() {
            return hasDefault;
        }

        public void setHasDefault(int hasDefault) {
            this.hasDefault = hasDefault;
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

        public int getSiteId() {
            return siteId;
        }

        public void setSiteId(int siteId) {
            this.siteId = siteId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
