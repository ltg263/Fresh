package com.power.fresh.bean.shoppingcar;

import com.power.fresh.utils.Constant;

import java.util.List;

/**
 * @author AlienChao
 * @date 2020/05/18 14:28
 */
public class ShoppingCarBean {

    /**
     * list : [{"baseBusiness":{"businessAvatar":"http://img.hao661.com/www.hao661.com/uploads/allimg/c140830/14093YO213410-2T3P.jpg","businessId":2,"businessLicense":"http://img.hao661.com/www.hao661.com/uploads/allimg/c140830/14093YO213410-2T3P.jpg","businessName":"我的商店","businessType":"经销商","lat":16.407,"leader":"铁憨憨","lng":39.90469,"mobile":"18458794212","score":0,"type":1},"userCartDTOS":[{"activityStatus":0,"baseActivity":{"typeName":"无活动"},"baseInfo":{"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":15,"commodityName":"彩果","originalPrice":199,"saleNum":2,"simpleInfo":"简单的介绍商品,拖鞋"},"baseNorms":{"normsId":118,"normsName":"橙","salePrice":99.99,"stock":1000,"subAmount":0,"taskAmount":0},"cartId":167,"normsExpireStatus":1,"num":0},{"activityStatus":0,"baseActivity":{"typeName":"无活动"},"baseInfo":{"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":15,"commodityName":"彩果","originalPrice":199,"saleNum":2,"simpleInfo":"简单的介绍商品,拖鞋"},"baseNorms":{"normsId":117,"normsName":"红","salePrice":99.99,"stock":1003,"subAmount":0,"taskAmount":0},"cartId":158,"normsExpireStatus":1,"num":4},{"activityStatus":1,"baseActivity":{"activityId":10,"activityName":"15:00开始抢购","endTime":"2020-06-01 13:59:59","image":"http://img.aiimg.com/uploads/allimg/181025/263915-1Q0251033590-L.jpg","type":1,"typeName":"秒杀"},"baseInfo":{"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":17,"commodityName":"苹果","originalPrice":40.11,"saleNum":1,"simpleInfo":"简单的介绍商品"},"baseNorms":{"normsId":130,"normsName":"500g","salePrice":30.99,"stock":1000,"subAmount":0,"taskAmount":0},"cartId":159,"normsExpireStatus":1,"num":1},{"activityStatus":0,"baseActivity":{"typeName":"无活动"},"baseInfo":{"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":18,"commodityName":"香蕉","originalPrice":40.11,"saleNum":1,"simpleInfo":"简单的介绍商品"},"baseNorms":{"normsId":131,"normsName":"500g","salePrice":40.99,"stock":1000,"subAmount":0,"taskAmount":0},"cartId":195,"normsExpireStatus":1,"num":1},{"activityStatus":1,"baseActivity":{"activityId":7,"activityName":"12:00开始抢购","endTime":"2020-05-13 13:59:59","image":"http://img.aiimg.com/uploads/allimg/181025/263915-1Q0251033590-L.jpg","type":2,"typeName":"限时"},"baseInfo":{"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":23,"commodityName":"哈密华","originalPrice":40.11,"saleNum":1,"simpleInfo":"简单的介绍商品"},"baseNorms":{"normsId":136,"normsName":"500g","salePrice":30.99,"stock":1001,"subAmount":0,"taskAmount":0},"cartId":193,"normsExpireStatus":1,"num":0},{"activityStatus":1,"baseActivity":{"activityId":7,"activityName":"12:00开始抢购","endTime":"2020-05-13 13:59:59","image":"http://img.aiimg.com/uploads/allimg/181025/263915-1Q0251033590-L.jpg","type":2,"typeName":"限时"},"baseInfo":{"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":23,"commodityName":"哈密华","originalPrice":40.11,"saleNum":1,"simpleInfo":"简单的介绍商品"},"baseNorms":{"normsId":136,"normsName":"500g","salePrice":30.99,"stock":1001,"subAmount":0,"taskAmount":0},"cartId":194,"normsExpireStatus":1,"num":2}]},{"baseBusiness":{"businessAvatar":"https://app.nbningjiang.com/ningjiangshengxian/upload/D688670AC0B5C350DD1525DA4274F3E0.jpg","businessId":6,"businessLicense":"https://app.nbningjiang.com/ningjiangshengxian/upload/E4024304C18F55E738F163AF7F848362.jpg","businessName":"新鲜花店","businessType":"经销商","lat":30.167223,"leader":"大动干戈","lng":118.379518,"mobile":"17633819345","score":0,"type":1},"userCartDTOS":[{"activityStatus":0,"baseActivity":{"typeName":"无活动"},"baseInfo":{"commodityHeaderUri":"https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2308693949,3552313049&fm=26&gp=0.jpg","commodityId":31,"commodityName":"西红柿","originalPrice":8,"saleNum":0,"simpleInfo":"<h2>西红柿<h2><br><p>简单的介绍<\/p>"},"baseNorms":{"normsId":148,"normsName":"250g","salePrice":3,"stock":1000,"subAmount":0,"taskAmount":0},"cartId":160,"normsExpireStatus":1,"num":1}]}]
     * listCount : 2
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
         * baseBusiness : {"businessAvatar":"http://img.hao661.com/www.hao661.com/uploads/allimg/c140830/14093YO213410-2T3P.jpg","businessId":2,"businessLicense":"http://img.hao661.com/www.hao661.com/uploads/allimg/c140830/14093YO213410-2T3P.jpg","businessName":"我的商店","businessType":"经销商","lat":16.407,"leader":"铁憨憨","lng":39.90469,"mobile":"18458794212","score":0,"type":1}
         * userCartDTOS : [{"activityStatus":0,"baseActivity":{"typeName":"无活动"},"baseInfo":{"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":15,"commodityName":"彩果","originalPrice":199,"saleNum":2,"simpleInfo":"简单的介绍商品,拖鞋"},"baseNorms":{"normsId":118,"normsName":"橙","salePrice":99.99,"stock":1000,"subAmount":0,"taskAmount":0},"cartId":167,"normsExpireStatus":1,"num":0},{"activityStatus":0,"baseActivity":{"typeName":"无活动"},"baseInfo":{"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":15,"commodityName":"彩果","originalPrice":199,"saleNum":2,"simpleInfo":"简单的介绍商品,拖鞋"},"baseNorms":{"normsId":117,"normsName":"红","salePrice":99.99,"stock":1003,"subAmount":0,"taskAmount":0},"cartId":158,"normsExpireStatus":1,"num":4},{"activityStatus":1,"baseActivity":{"activityId":10,"activityName":"15:00开始抢购","endTime":"2020-06-01 13:59:59","image":"http://img.aiimg.com/uploads/allimg/181025/263915-1Q0251033590-L.jpg","type":1,"typeName":"秒杀"},"baseInfo":{"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":17,"commodityName":"苹果","originalPrice":40.11,"saleNum":1,"simpleInfo":"简单的介绍商品"},"baseNorms":{"normsId":130,"normsName":"500g","salePrice":30.99,"stock":1000,"subAmount":0,"taskAmount":0},"cartId":159,"normsExpireStatus":1,"num":1},{"activityStatus":0,"baseActivity":{"typeName":"无活动"},"baseInfo":{"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":18,"commodityName":"香蕉","originalPrice":40.11,"saleNum":1,"simpleInfo":"简单的介绍商品"},"baseNorms":{"normsId":131,"normsName":"500g","salePrice":40.99,"stock":1000,"subAmount":0,"taskAmount":0},"cartId":195,"normsExpireStatus":1,"num":1},{"activityStatus":1,"baseActivity":{"activityId":7,"activityName":"12:00开始抢购","endTime":"2020-05-13 13:59:59","image":"http://img.aiimg.com/uploads/allimg/181025/263915-1Q0251033590-L.jpg","type":2,"typeName":"限时"},"baseInfo":{"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":23,"commodityName":"哈密华","originalPrice":40.11,"saleNum":1,"simpleInfo":"简单的介绍商品"},"baseNorms":{"normsId":136,"normsName":"500g","salePrice":30.99,"stock":1001,"subAmount":0,"taskAmount":0},"cartId":193,"normsExpireStatus":1,"num":0},{"activityStatus":1,"baseActivity":{"activityId":7,"activityName":"12:00开始抢购","endTime":"2020-05-13 13:59:59","image":"http://img.aiimg.com/uploads/allimg/181025/263915-1Q0251033590-L.jpg","type":2,"typeName":"限时"},"baseInfo":{"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":23,"commodityName":"哈密华","originalPrice":40.11,"saleNum":1,"simpleInfo":"简单的介绍商品"},"baseNorms":{"normsId":136,"normsName":"500g","salePrice":30.99,"stock":1001,"subAmount":0,"taskAmount":0},"cartId":194,"normsExpireStatus":1,"num":2}]
         */

        private BaseBusinessBean baseBusiness;
        private List<UserCartDTOSBean> userCartDTOS;
        private boolean isCheck;
        private boolean isEdit;

        public boolean isEdit() {
            return isEdit;
        }

        public void setEdit(boolean edit) {
            isEdit = edit;
        }

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public BaseBusinessBean getBaseBusiness() {
            return baseBusiness;
        }

        public void setBaseBusiness(BaseBusinessBean baseBusiness) {
            this.baseBusiness = baseBusiness;
        }

        public List<UserCartDTOSBean> getUserCartDTOS() {
            return userCartDTOS;
        }

        public void setUserCartDTOS(List<UserCartDTOSBean> userCartDTOS) {
            this.userCartDTOS = userCartDTOS;
        }

        public static class BaseBusinessBean {
            /**
             * businessAvatar : http://img.hao661.com/www.hao661.com/uploads/allimg/c140830/14093YO213410-2T3P.jpg
             * businessId : 2
             * businessLicense : http://img.hao661.com/www.hao661.com/uploads/allimg/c140830/14093YO213410-2T3P.jpg
             * businessName : 我的商店
             * businessType : 经销商
             * lat : 16.407
             * leader : 铁憨憨
             * lng : 39.90469
             * mobile : 18458794212
             * score : 0
             * type : 1
             */

            private String businessAvatar;
            private int businessId;
            private String businessLicense;
            private String businessName;
            private String businessType;
            private double lat;
            private String leader;
            private double lng;
            private String mobile;
            private double score;
            private int type;

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

            public String getBusinessLicense() {
                return businessLicense;
            }

            public void setBusinessLicense(String businessLicense) {
                this.businessLicense = businessLicense;
            }

            public String getBusinessName() {
                return businessName;
            }

            public void setBusinessName(String businessName) {
                this.businessName = businessName;
            }

            public String getBusinessType() {
                return businessType;
            }

            public void setBusinessType(String businessType) {
                this.businessType = businessType;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public String getLeader() {
                return leader;
            }

            public void setLeader(String leader) {
                this.leader = leader;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public double getScore() {
                return score;
            }

            public void setScore(double score) {
                this.score = score;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }

        public static class UserCartDTOSBean {
            /**
             * activityStatus : 0
             * baseActivity : {"typeName":"无活动"}
             * baseInfo : {"commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":15,"commodityName":"彩果","originalPrice":199,"saleNum":2,"simpleInfo":"简单的介绍商品,拖鞋"}
             * baseNorms : {"normsId":118,"normsName":"橙","salePrice":99.99,"stock":1000,"subAmount":0,"taskAmount":0}
             * cartId : 167
             * normsExpireStatus : 1
             * num : 0
             */

            private int activityStatus;
            private BaseActivityBean baseActivity;
            private BaseInfoBean baseInfo;
            private BaseNormsBean baseNorms;
            private int cartId;
            private int normsExpireStatus;
            private int num;
            private boolean isCheck;
            private  boolean isEdit;
            private String total ;

            public String getTotal() {
                return Constant.get().canvetDouble(baseNorms.getSalePrice() *num) ;
            }



            public boolean isEdit() {
                return isEdit;
            }

            public void setEdit(boolean edit) {
                isEdit = edit;
            }

            public boolean isCheck() {
                return isCheck;
            }

            public void setCheck(boolean check) {
                isCheck = check;
            }

            public int getActivityStatus() {
                return activityStatus;
            }

            public void setActivityStatus(int activityStatus) {
                this.activityStatus = activityStatus;
            }

            public BaseActivityBean getBaseActivity() {
                return baseActivity;
            }

            public void setBaseActivity(BaseActivityBean baseActivity) {
                this.baseActivity = baseActivity;
            }

            public BaseInfoBean getBaseInfo() {
                if (baseInfo==null) {
                    baseInfo=new BaseInfoBean();
                }
                return baseInfo;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public void setBaseInfo(BaseInfoBean baseInfo) {
                this.baseInfo = baseInfo;
            }

            public BaseNormsBean getBaseNorms() {
                if (baseNorms==null) {
                    baseNorms =new BaseNormsBean();
                }
                return baseNorms;
            }

            public void setBaseNorms(BaseNormsBean baseNorms) {
                this.baseNorms = baseNorms;
            }

            public int getCartId() {
                return cartId;
            }

            public void setCartId(int cartId) {
                this.cartId = cartId;
            }

            public int getNormsExpireStatus() {
                return normsExpireStatus;
            }

            public void setNormsExpireStatus(int normsExpireStatus) {
                this.normsExpireStatus = normsExpireStatus;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public static class BaseActivityBean {
                /**
                 * typeName : 无活动
                 */

                private String typeName;

                public String getTypeName() {
                    return typeName;
                }

                public void setTypeName(String typeName) {
                    this.typeName = typeName;
                }
            }

            public static class BaseInfoBean {
                /**
                 * commodityHeaderUri : https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg
                 * commodityId : 15
                 * commodityName : 彩果
                 * originalPrice : 199
                 * saleNum : 2
                 * simpleInfo : 简单的介绍商品,拖鞋
                 */

                private String commodityHeaderUri;
                private int commodityId;
                private String commodityName;
                private double originalPrice;
                private int saleNum;
                private String simpleInfo;

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

                public String getCommodityName() {
                    return commodityName;
                }

                public void setCommodityName(String commodityName) {
                    this.commodityName = commodityName;
                }

                public double getOriginalPrice() {
                    return originalPrice;
                }

                public void setOriginalPrice(double originalPrice) {
                    this.originalPrice = originalPrice;
                }

                public int getSaleNum() {
                    return saleNum;
                }

                public void setSaleNum(int saleNum) {
                    this.saleNum = saleNum;
                }

                public String getSimpleInfo() {
                    return simpleInfo;
                }

                public void setSimpleInfo(String simpleInfo) {
                    this.simpleInfo = simpleInfo;
                }
            }

            public static class BaseNormsBean {
                /**
                 * normsId : 118
                 * normsName : 橙
                 * salePrice : 99.99
                 * stock : 1000
                 * subAmount : 0
                 * taskAmount : 0
                 */

                private int normsId;
                private String normsName;
                private double salePrice;
                private int stock;
                private double subAmount;
                private int taskAmount;

                public int getNormsId() {
                    return normsId;
                }

                public void setNormsId(int normsId) {
                    this.normsId = normsId;
                }

                public String getNormsName() {
                    return normsName;
                }

                public void setNormsName(String normsName) {
                    this.normsName = normsName;
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
            }
        }
    }
}
