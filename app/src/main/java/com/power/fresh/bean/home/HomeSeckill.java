package com.power.fresh.bean.home;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动 （秒杀专区+ 限时热销）
 * @author AlienChao
 * @date 2020/04/27 11:49
 */
public class HomeSeckill {


    /**
     * list : [{"activityName":"12:00开始抢购","appActivityCommodityDTOS":[{"activityId":7,"businessId":2,"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":23,"createTime":"2020-03-31 16:33:11","id":5,"minPrice":30.99,"name":"哈密华","rules":[{"activitySalePrice":30.99,"activityStock":105,"commodityId":23,"id":136,"normsRule":"500g"}],"saleNum":0,"salePrice":40.11,"simpleInfo":"好吃实惠","stockPercent":100,"totalStock":200},{"activityId":7,"businessId":2,"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":22,"createTime":"2020-03-31 16:33:07","id":4,"minPrice":30.99,"name":"樱桃","rules":[{"activitySalePrice":30.99,"activityStock":100,"commodityId":22,"id":135,"normsRule":"500g"}],"saleNum":0,"salePrice":40.11,"simpleInfo":"好吃实惠","stockPercent":100,"totalStock":200},{"activityId":7,"businessId":2,"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":21,"createTime":"2020-03-31 16:33:04","id":3,"minPrice":30.99,"name":"香梨","rules":[{"activitySalePrice":30.99,"activityStock":100,"commodityId":21,"id":134,"normsRule":"500g"}],"saleNum":0,"salePrice":40.11,"simpleInfo":"好吃实惠","stockPercent":100,"totalStock":200}],"createTime":"2020-03-25 12:00:20","endTime":"2020-05-13 13:59:59","hasDelete":0,"id":7,"image":"http://img.aiimg.com/uploads/allimg/181025/263915-1Q0251033590-L.jpg","liveStatus":2,"startTime":"2020-03-25 12:00:00","status":1,"type":2}]
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
        if (list == null||list.size()==0) {
            list= new ArrayList<>();
            list.add(new ListBean());
        }
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * activityName : 12:00开始抢购
         * appActivityCommodityDTOS : [{"activityId":7,"businessId":2,"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":23,"createTime":"2020-03-31 16:33:11","id":5,"minPrice":30.99,"name":"哈密华","rules":[{"activitySalePrice":30.99,"activityStock":105,"commodityId":23,"id":136,"normsRule":"500g"}],"saleNum":0,"salePrice":40.11,"simpleInfo":"好吃实惠","stockPercent":100,"totalStock":200},{"activityId":7,"businessId":2,"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":22,"createTime":"2020-03-31 16:33:07","id":4,"minPrice":30.99,"name":"樱桃","rules":[{"activitySalePrice":30.99,"activityStock":100,"commodityId":22,"id":135,"normsRule":"500g"}],"saleNum":0,"salePrice":40.11,"simpleInfo":"好吃实惠","stockPercent":100,"totalStock":200},{"activityId":7,"businessId":2,"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":21,"createTime":"2020-03-31 16:33:04","id":3,"minPrice":30.99,"name":"香梨","rules":[{"activitySalePrice":30.99,"activityStock":100,"commodityId":21,"id":134,"normsRule":"500g"}],"saleNum":0,"salePrice":40.11,"simpleInfo":"好吃实惠","stockPercent":100,"totalStock":200}]
         * createTime : 2020-03-25 12:00:20
         * endTime : 2020-05-13 13:59:59
         * hasDelete : 0
         * id : 7
         * image : http://img.aiimg.com/uploads/allimg/181025/263915-1Q0251033590-L.jpg
         * liveStatus : 2
         * startTime : 2020-03-25 12:00:00
         * status : 1
         * type : 2
         */

        private String activityName;
        private String createTime;
        private String endTime;
        private int hasDelete;
        private int id;
        private String image;
        private int liveStatus;
        private String startTime;
        private int status;
        private int type;
        private List<HomeSeckill2.ListBean> appActivityCommodityDTOS;

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
            return image == null ? "" : image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getLiveStatus() {
            return liveStatus;
        }

        public void setLiveStatus(int liveStatus) {
            this.liveStatus = liveStatus;
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

        public List<HomeSeckill2.ListBean> getAppActivityCommodityDTOS() {
            if (appActivityCommodityDTOS == null) {
                appActivityCommodityDTOS= new ArrayList<>();
            }
            return appActivityCommodityDTOS;
        }

        public void setAppActivityCommodityDTOS(List<HomeSeckill2.ListBean> appActivityCommodityDTOS) {
            this.appActivityCommodityDTOS = appActivityCommodityDTOS;
        }

        public static class AppActivityCommodityDTOSBean {
            /**
             * activityId : 7
             * businessId : 2
             * commodityHeaderUri : https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg
             * commodityId : 23
             * createTime : 2020-03-31 16:33:11
             * id : 5
             * minPrice : 30.99
             * name : 哈密华
             * rules : [{"activitySalePrice":30.99,"activityStock":105,"commodityId":23,"id":136,"normsRule":"500g"}]
             * saleNum : 0
             * salePrice : 40.11
             * simpleInfo : 好吃实惠
             * stockPercent : 100
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

            public int getActivityId() {
                return activityId;
            }

            public void setActivityId(int activityId) {
                this.activityId = activityId;
            }

            public int getBusinessId() {
                return businessId;
            }

            public void setBusinessId(int businessId) {
                this.businessId = businessId;
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
                if (rules == null) {
                    rules= new ArrayList<>();
                    rules.add(new RulesBean());
                }
                return rules;
            }

            public void setRules(List<RulesBean> rules) {
                this.rules = rules;
            }

            public static class RulesBean {
                /**
                 * activitySalePrice : 30.99
                 * activityStock : 105
                 * commodityId : 23
                 * id : 136
                 * normsRule : 500g
                 */

                private double activitySalePrice;
                private double salePrice;
                private int activityStock;
                private int commodityId;
                private int id;
                private String normsRule;

                public double getSalePrice() {
                    return salePrice;
                }

                public void setSalePrice(double salePrice) {
                    this.salePrice = salePrice;
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
            }
        }
    }
}
