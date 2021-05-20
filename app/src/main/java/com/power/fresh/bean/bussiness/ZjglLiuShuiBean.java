package com.power.fresh.bean.bussiness;

import java.util.List;

/**
 * @author AlienChao
 * @date 2020/05/26 22:27
 */
public class ZjglLiuShuiBean {

    /**
     * list : [{"amount":1,"cashOutInfoDTO":null,"createTime":"2020-05-26 15:01:05","id":49,"info":"提现","siteId":22,"type":1}]
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
         * amount : 1
         * cashOutInfoDTO : null
         * createTime : 2020-05-26 15:01:05
         * id : 49
         * info : 提现
         * siteId : 22
         * type : 1
         */

        private double amount;
        private Object cashOutInfoDTO;
        private String createTime;
        private int id;
        private String info;
        private int siteId;
        private int type;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public Object getCashOutInfoDTO() {
            return cashOutInfoDTO;
        }

        public void setCashOutInfoDTO(Object cashOutInfoDTO) {
            this.cashOutInfoDTO = cashOutInfoDTO;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public int getSiteId() {
            return siteId;
        }

        public void setSiteId(int siteId) {
            this.siteId = siteId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
