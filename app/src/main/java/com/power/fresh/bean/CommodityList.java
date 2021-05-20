package com.power.fresh.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AlienChao
 * @date 2020/05/13 14:16
 */
public class CommodityList {

    /**
     * list : [{"activityStatus":0,"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":15,"createTime":"2020-03-17 10:16:06","hasHot":1,"minPrice":199.99,"name":"彩果","rules":[{"activitySalePrice":199.99,"activityStock":50,"commodityId":15,"costPrice":40.99,"id":117,"normsRule":"红","refundNum":0,"saleNum":0,"salePrice":99.99,"stock":1000},{"activitySalePrice":199.99,"activityStock":50,"commodityId":15,"costPrice":40.99,"id":118,"normsRule":"橙","refundNum":0,"saleNum":0,"salePrice":99.99,"stock":1000},{"activitySalePrice":199.99,"activityStock":50,"commodityId":15,"costPrice":40.99,"id":119,"normsRule":"黄","refundNum":0,"saleNum":0,"salePrice":99.99,"stock":1000},{"activitySalePrice":199.99,"activityStock":50,"commodityId":15,"costPrice":40.99,"id":120,"normsRule":"绿","refundNum":0,"saleNum":0,"salePrice":99.99,"stock":1000}],"saleNum":2,"salePrice":199,"simpleInfo":"   水水拖鞋","tcStatus":2,"totalStock":4000},{"activityStatus":0,"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":18,"createTime":"2020-03-24 14:12:21","hasHot":0,"minPrice":30.99,"name":"香蕉","rules":[{"activitySalePrice":30.99,"activityStock":100,"commodityId":18,"costPrice":20.99,"id":131,"normsRule":"500g","refundNum":0,"saleNum":0,"salePrice":40.99,"stock":1000}],"saleNum":1,"salePrice":40.11,"simpleInfo":"好吃的百香果","tcStatus":2,"totalStock":2000},{"activityStatus":0,"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":23,"createTime":"2020-03-24 14:12:21","hasHot":0,"minPrice":30.99,"name":"哈密华","rules":[{"activitySalePrice":30.99,"activityStock":115,"commodityId":23,"costPrice":20.99,"id":136,"normsRule":"500g","refundNum":0,"saleNum":0,"salePrice":40.99,"stock":998}],"saleNum":1,"salePrice":40.11,"simpleInfo":"好吃的百香果","tcStatus":2,"totalStock":2000},{"activityStatus":0,"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":19,"createTime":"2020-03-24 14:12:21","hasHot":0,"minPrice":30.99,"name":"猕猴桃","rules":[{"activitySalePrice":30.99,"activityStock":100,"commodityId":19,"costPrice":20.99,"id":132,"normsRule":"500g","refundNum":0,"saleNum":0,"salePrice":40.99,"stock":1000}],"saleNum":1,"salePrice":40.11,"simpleInfo":"好吃的百香果","tcStatus":2,"totalStock":2000},{"activityStatus":0,"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":24,"createTime":"2020-03-24 14:12:21","hasHot":0,"minPrice":30.99,"name":"水蜜桃","rules":[{"activitySalePrice":30.99,"activityStock":100,"commodityId":24,"costPrice":20.99,"id":137,"normsRule":"500g","refundNum":0,"saleNum":0,"salePrice":40.99,"stock":1000}],"saleNum":1,"salePrice":40.11,"simpleInfo":"好吃的百香果","tcStatus":2,"totalStock":2000},{"activityStatus":0,"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":20,"createTime":"2020-03-24 14:12:21","hasHot":0,"minPrice":30.99,"name":"西瓜","rules":[{"activitySalePrice":30.99,"activityStock":100,"commodityId":20,"costPrice":20.99,"id":133,"normsRule":"500g","refundNum":0,"saleNum":0,"salePrice":40.99,"stock":1000}],"saleNum":1,"salePrice":40.11,"simpleInfo":"好吃的百香果","tcStatus":2,"totalStock":2000},{"activityStatus":0,"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":25,"createTime":"2020-03-24 14:12:21","hasHot":0,"minPrice":30.99,"name":"圣女果","rules":[{"activitySalePrice":30.99,"activityStock":100,"commodityId":25,"costPrice":20.99,"id":138,"normsRule":"500g","refundNum":0,"saleNum":0,"salePrice":40.99,"stock":1000}],"saleNum":1,"salePrice":40.11,"simpleInfo":"好吃的百香果","tcStatus":2,"totalStock":2000},{"activityStatus":0,"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":16,"createTime":"2020-03-24 14:12:21","hasHot":0,"minPrice":30.99,"name":"百香果","rules":[{"activitySalePrice":30.99,"activityStock":100,"commodityId":16,"costPrice":20.99,"id":127,"normsRule":"500g","refundNum":0,"saleNum":0,"salePrice":30.99,"stock":1000},{"activitySalePrice":59.99,"activityStock":105,"commodityId":16,"costPrice":44.99,"id":128,"normsRule":"1000g","refundNum":0,"saleNum":0,"salePrice":59.99,"stock":105},{"activitySalePrice":100.99,"activityStock":100,"commodityId":16,"costPrice":80.99,"id":129,"normsRule":"2000g","refundNum":0,"saleNum":0,"salePrice":100.99,"stock":100}],"saleNum":1,"salePrice":40.11,"simpleInfo":"好吃的百香果","tcStatus":2,"totalStock":2000},{"activityStatus":0,"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":21,"createTime":"2020-03-24 14:12:21","hasHot":0,"minPrice":30.99,"name":"香梨","rules":[{"activitySalePrice":30.99,"activityStock":101,"commodityId":21,"costPrice":20.99,"id":134,"normsRule":"500g","refundNum":0,"saleNum":0,"salePrice":40.99,"stock":1000}],"saleNum":1,"salePrice":40.11,"simpleInfo":"好吃的百香果","tcStatus":2,"totalStock":2000},{"activity":{"activityName":"15:00开始抢购","createTime":"2020-03-25 15:00:20","endTime":"2020-06-01 13:59:59","hasDelete":0,"id":10,"image":"http://img.aiimg.com/uploads/allimg/181025/263915-1Q0251033590-L.jpg","startTime":"2020-03-31 16:04:14","status":1,"type":1},"activityStatus":1,"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":17,"createTime":"2020-03-24 14:12:21","hasHot":0,"minPrice":30.99,"name":"苹果","rules":[{"activitySalePrice":30.99,"activityStock":123,"commodityId":17,"costPrice":20.99,"id":130,"normsRule":"500g","refundNum":0,"saleNum":0,"salePrice":40.99,"stock":1000}],"saleNum":1,"salePrice":40.11,"simpleInfo":"好吃的百香果","tcStatus":2,"totalStock":2000}]
     * count : 11
     */

    private int count;
    private List<ListBean> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ListBean> getList() {
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * activityStatus : 0
         * commodityHeaderUri : https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg
         * commodityId : 15
         * createTime : 2020-03-17 10:16:06
         * hasHot : 1
         * minPrice : 199.99
         * name : 彩果
         * rules : [{"activitySalePrice":199.99,"activityStock":50,"commodityId":15,"costPrice":40.99,"id":117,"normsRule":"红","refundNum":0,"saleNum":0,"salePrice":99.99,"stock":1000},{"activitySalePrice":199.99,"activityStock":50,"commodityId":15,"costPrice":40.99,"id":118,"normsRule":"橙","refundNum":0,"saleNum":0,"salePrice":99.99,"stock":1000},{"activitySalePrice":199.99,"activityStock":50,"commodityId":15,"costPrice":40.99,"id":119,"normsRule":"黄","refundNum":0,"saleNum":0,"salePrice":99.99,"stock":1000},{"activitySalePrice":199.99,"activityStock":50,"commodityId":15,"costPrice":40.99,"id":120,"normsRule":"绿","refundNum":0,"saleNum":0,"salePrice":99.99,"stock":1000}]
         * saleNum : 2
         * salePrice : 199
         * simpleInfo :    水水拖鞋
         * tcStatus : 2
         * totalStock : 4000
         * activity : {"activityName":"15:00开始抢购","createTime":"2020-03-25 15:00:20","endTime":"2020-06-01 13:59:59","hasDelete":0,"id":10,"image":"http://img.aiimg.com/uploads/allimg/181025/263915-1Q0251033590-L.jpg","startTime":"2020-03-31 16:04:14","status":1,"type":1}
         */

        private int activityStatus;
        private String commodityHeaderUri;
        private int commodityId;
        private int categoryId;
        private String createTime;
        private int hasHot;
        private double minPrice;
        private String name;
        private int saleNum;

        private double salePrice;
        private String simpleInfo;
        private int tcStatus;
        private int totalCartNum;
        private int totalStock;
        private ActivityBean activity;
        private List<RulesBean> rules;
        private  int cardId;
        private int showCcNum=0;
        private double shoCcPrice ;

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public int getTotalCartNum() {
            return totalCartNum;
        }

        public void setTotalCartNum(int totalCartNum) {
            this.totalCartNum = totalCartNum;
        }

        public double getShoCcPrice() {
            return shoCcPrice;
        }

        public void setShoCcPrice(double shoCcPrice) {
            this.shoCcPrice = shoCcPrice;
        }

        public int getCardId() {
            return cardId;
        }

        public void setCardId(int cardId) {
            this.cardId = cardId;
        }

        public int getShowCcNum() {
            return showCcNum;
        }

        public void setShowCcNum(int showCcNum) {
            this.showCcNum = showCcNum;
        }

        public int getActivityStatus() {
            return activityStatus;
        }

        public void setActivityStatus(int activityStatus) {
            this.activityStatus = activityStatus;
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

        public int getHasHot() {
            return hasHot;
        }

        public void setHasHot(int hasHot) {
            this.hasHot = hasHot;
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

        /**
         * 商家查看自身商品传值:-1,审核中;1,下架(审核通过);2,上架(审核通过);3,审核失败;4,未入库;5,已入库
         * @return
         */
        public int getTcStatus() {
            return tcStatus;
        }

        public void setTcStatus(int tcStatus) {
            this.tcStatus = tcStatus;
        }

        public int getTotalStock() {
            return totalStock;
        }

        public void setTotalStock(int totalStock) {
            this.totalStock = totalStock;
        }

        public ActivityBean getActivity() {
            return activity;
        }

        public void setActivity(ActivityBean activity) {
            this.activity = activity;
        }

        public List<RulesBean> getRules() {
            if (rules == null) {
                rules= new ArrayList<>();
                rules.add(new RulesBean());
            }
            return rules;
        }

        public void setRules(List<RulesBean> rules) {
            this.rules = rules;
        }

        public static class ActivityBean {
            /**
             * activityName : 15:00开始抢购
             * createTime : 2020-03-25 15:00:20
             * endTime : 2020-06-01 13:59:59
             * hasDelete : 0
             * id : 10
             * image : http://img.aiimg.com/uploads/allimg/181025/263915-1Q0251033590-L.jpg
             * startTime : 2020-03-31 16:04:14
             * status : 1
             * type : 1
             */

            private String activityName;
            private String createTime;
            private String endTime;
            private int hasDelete;
            private int id;
            private String image;
            private String startTime;
            private int status;
            private int type;

            public String getActivityName() {
                return activityName;
            }

            public void setActivityName(String activityName) {
                this.activityName = activityName;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
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

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
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
        }

        public static class RulesBean {
            /**
             * activitySalePrice : 199.99
             * activityStock : 50
             * commodityId : 15
             * costPrice : 40.99
             * id : 117
             * normsRule : 红
             * refundNum : 0
             * saleNum : 0
             * salePrice : 99.99
             * stock : 1000
             */

            private double activitySalePrice;
            private int activityStock;
            private int commodityId;
            private double costPrice;
            private int id;
            private int cartId;
            private String normsRule;
            private int refundNum;
            private int cartNum;
            private int saleNum;
            private double salePrice;
            private double quotaNum;
            private int stock;

            private int selectNum;
            private int numShowCc;

            public int getCartNum() {
                return cartNum;
            }

            public void setCartNum(int cartNum) {
                this.cartNum = cartNum;
            }

            public void setSelectNum(int selectNum) {
                this.selectNum = selectNum;
            }

            public int getSelectNum() {
                return selectNum;
            }

            public double getQuotaNum() {
                return quotaNum;
            }

            public void setQuotaNum(double quotaNum) {
                this.quotaNum = quotaNum;
            }

            public int getCartId() {
                return cartId;
            }

            public void setCartId(int cartId) {
                this.cartId = cartId;
            }

            public int getNumShowCc() {
                return numShowCc;
            }

            public void setNumShowCc(int numShowCc) {
                this.numShowCc = numShowCc;
            }

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

            public double getCostPrice() {
                return costPrice;
            }

            public void setCostPrice(double costPrice) {
                this.costPrice = costPrice;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getNormsRule() {
                return normsRule == null ? "" : normsRule;
            }

            public void setNormsRule(String normsRule) {
                this.normsRule = normsRule;
            }

            public int getRefundNum() {
                return refundNum;
            }

            public void setRefundNum(int refundNum) {
                this.refundNum = refundNum;
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

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }
        }
    }
}
