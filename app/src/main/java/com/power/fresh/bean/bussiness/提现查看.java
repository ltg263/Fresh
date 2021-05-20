package com.power.fresh.bean.bussiness;

import java.util.List;

/**
 * @author AlienChao
 * @date 2020/05/27 11:50
 */
public class 提现查看 {

    /**
     * list : [{"amount":100,"businessAvatar":"","businessId":22,"businessName":"eee","createTime":"2020-05-27 11:32:07","handlerTime":null,"id":9,"mobile":"","orderNo":"","parameter":"","parameterJSON":{"bankAccount":"qqq","bankNo":"www","bankName":"安徽省农村信用社"},"reason":"","status":1,"type":1},{"amount":1,"businessAvatar":"","businessId":22,"businessName":"eee","createTime":"2020-05-27 11:03:03","handlerTime":null,"id":8,"mobile":"","orderNo":"","parameter":"","parameterJSON":{"bankAccount":"，，","bankNo":"4444","bankName":"安阳银行"},"reason":"","status":4,"type":1},{"amount":8,"businessAvatar":"","businessId":22,"businessName":"eee","createTime":"2020-05-27 10:32:24","handlerTime":null,"id":7,"mobile":"","orderNo":"","parameter":"","parameterJSON":{"bankAccount":"qqq","bankNo":"www","bankName":"安徽省农村信用社"},"reason":"","status":1,"type":1},{"amount":5,"businessAvatar":"","businessId":22,"businessName":"eee","createTime":"2020-05-27 10:31:43","handlerTime":null,"id":6,"mobile":"","orderNo":"","parameter":"","parameterJSON":{"bankAccount":"fhjjj","bankNo":"gjjj","bankName":"福建海峡银行"},"reason":"","status":1,"type":1},{"amount":1,"businessAvatar":"","businessId":22,"businessName":"eee","createTime":"2020-05-26 15:01:05","handlerTime":null,"id":5,"mobile":"","orderNo":"","parameter":"","parameterJSON":{"bankAccount":"fhjjj","bankNo":"gjjj","bankName":"福建海峡银行"},"reason":"","status":1,"type":1}]
     * listCount : 5
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
         * amount : 100
         * businessAvatar :
         * businessId : 22
         * businessName : eee
         * createTime : 2020-05-27 11:32:07
         * handlerTime : null
         * id : 9
         * mobile :
         * orderNo :
         * parameter :
         * parameterJSON : {"bankAccount":"qqq","bankNo":"www","bankName":"安徽省农村信用社"}
         * reason :
         * status : 1
         * type : 1
         */

        private double amount;
        private String businessAvatar;
        private int businessId;
        private String businessName;
        private String createTime;
        private Object handlerTime;
        private int id;
        private String mobile;
        private String orderNo;
        private String parameter;
        private ParameterJSONBean parameterJSON;
        private String reason;
        private int status;
        private int type;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getBusinessAvatar() {
            return businessAvatar;
        }

        public void setBusinessAvatar(String businessAvatar) {
            this.businessAvatar = businessAvatar;
        }

        public int getBusinessId() {
            return businessId;
        }

        public void setBusinessId(int businessId) {
            this.businessId = businessId;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getHandlerTime() {
            return handlerTime;
        }

        public void setHandlerTime(Object handlerTime) {
            this.handlerTime = handlerTime;
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

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getParameter() {
            return parameter;
        }

        public void setParameter(String parameter) {
            this.parameter = parameter;
        }

        public ParameterJSONBean getParameterJSON() {
            return parameterJSON;
        }

        public void setParameterJSON(ParameterJSONBean parameterJSON) {
            this.parameterJSON = parameterJSON;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public static class ParameterJSONBean {
            /**
             * bankAccount : qqq
             * bankNo : www
             * bankName : 安徽省农村信用社
             */

            private String bankAccount;
            private String bankNo;
            private String bankName;

            public String getBankAccount() {
                return bankAccount;
            }

            public void setBankAccount(String bankAccount) {
                this.bankAccount = bankAccount;
            }

            public String getBankNo() {
                return bankNo;
            }

            public void setBankNo(String bankNo) {
                this.bankNo = bankNo;
            }

            public String getBankName() {
                return bankName;
            }

            public void setBankName(String bankName) {
                this.bankName = bankName;
            }
        }
    }
}
