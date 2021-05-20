package com.power.fresh.bean.home;

import java.util.List;

/**
 * 活动 （秒杀专区+ 限时热销）
 * @author AlienChao
 * @date 2020/04/27 11:49
 */
public class HomeSeckill2 {


    /**
     * list : [{"activityId":9,"commodityHeaderUri":"http://tupan.cn.shoe.jpg","commodityId":16,"createTime":"2020-03-25 15:01:39","id":2,"minPrice":30.99,"name":"百香果","rules":[{"activitySalePrice":30.99,"activityStock":100,"commodityId":16,"id":127,"normsRule":"500g"},{"activitySalePrice":59.99,"activityStock":100,"commodityId":16,"id":128,"normsRule":"1000g"},{"activitySalePrice":100.99,"activityStock":100,"commodityId":16,"id":129,"normsRule":"2000g"}],"saleNum":0,"salePrice":40.11,"simpleInfo":"好吃,实惠","stockPercent":0,"totalStock":200}]
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
         * activityId : 9
         * commodityHeaderUri : http://tupan.cn.shoe.jpg
         * commodityId : 16
         * createTime : 2020-03-25 15:01:39
         * id : 2
         * minPrice : 30.99
         * name : 百香果
         * rules : [{"activitySalePrice":30.99,"activityStock":100,"commodityId":16,"id":127,"normsRule":"500g"},{"activitySalePrice":59.99,"activityStock":100,"commodityId":16,"id":128,"normsRule":"1000g"},{"activitySalePrice":100.99,"activityStock":100,"commodityId":16,"id":129,"normsRule":"2000g"}]
         * saleNum : 0
         * salePrice : 40.11
         * simpleInfo : 好吃,实惠
         * stockPercent : 0
         * totalStock : 200
         */

        private int activityId;
        private int businessId;
        private String commodityHeaderUri;
        private int commodityId;
        private String createTime;
        private int id;
        private double minPrice;
        private String name;
        private int saleNum;
        private double salePrice;
        private String simpleInfo;
        private double stockPercent;
        private double totalStock;
        private List<RulesBean> rules;

        public int getBusinessId() {
            return businessId;
        }

        public void setBusinessId(int businessId) {
            this.businessId = businessId;
        }

        public int getActivityId() {
            return activityId;
        }

        public void setActivityId(int activityId) {
            this.activityId = activityId;
        }

        public String getCommodityHeaderUri() {
            return commodityHeaderUri;
        }

        public void setCommodityHeaderUri(String commodityHeaderUri) {
            this.commodityHeaderUri = commodityHeaderUri;
        }

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
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

        public double getMinPrice() {
            return minPrice;
        }

        public void setMinPrice(double minPrice) {
            this.minPrice = minPrice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }

        public double getSalePrice() {
            return salePrice;
        }

        public void setSalePrice(double salePrice) {
            this.salePrice = salePrice;
        }

        public String getSimpleInfo() {
            return simpleInfo;
        }

        public void setSimpleInfo(String simpleInfo) {
            this.simpleInfo = simpleInfo;
        }

        public double getStockPercent() {
            return stockPercent;
        }

        public void setStockPercent(double stockPercent) {
            this.stockPercent = stockPercent;
        }

        public double getTotalStock() {
            return totalStock;
        }

        public void setTotalStock(double totalStock) {
            this.totalStock = totalStock;
        }

        public List<RulesBean> getRules() {
            return rules;
        }

        public void setRules(List<RulesBean> rules) {
            this.rules = rules;
        }

        public static class RulesBean {
            /**
             * activitySalePrice : 30.99
             * activityStock : 100
             * commodityId : 16
             * id : 127
             * normsRule : 500g
             */

            private double activitySalePrice;
            private int activityStock;
            private int commodityId;
            private int id;
            private String normsRule;

            public double getActivitySalePrice() {
                return activitySalePrice;
            }

            public void setActivitySalePrice(double activitySalePrice) {
                this.activitySalePrice = activitySalePrice;
            }

            public int getActivityStock() {
                return activityStock;
            }

            public void setActivityStock(int activityStock) {
                this.activityStock = activityStock;
            }

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getNormsRule() {
                return normsRule;
            }

            public void setNormsRule(String normsRule) {
                this.normsRule = normsRule;
            }
        }
    }
}
