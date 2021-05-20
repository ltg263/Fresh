package com.power.fresh.bean.order;

import java.util.List;

/**
 * @author AlienChao
 * @date 2020/05/20 17:21
 */
public class CreateOrderBean {

    /**
     * appCheckDetailsDTOList : [{"activityStatus":0,"baseActivity":{"typeName":"无活动"},"baseInfo":{"commodityHeaderUri":"https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2308693949,3552313049&fm=26&gp=0.jpg","commodityId":34,"commodityName":"红辣椒","originalPrice":7,"saleNum":2,"simpleInfo":"<h2>红辣椒<h2><p>简单的介绍<\/p>"},"cartId":207,"commodityNorms":{"commodityId":34,"currentTotalPrice":3,"norms":"250g","normsId":154,"num":1,"price":3,"stock":998,"subTotalPrice":0,"totalPrice":3}}]
     * baseBusiness : {"businessAvatar":"https://app.nbningjiang.com/ningjiangshengxian/upload/D688670AC0B5C350DD1525DA4274F3E0.jpg","businessId":6,"businessLicense":"https://app.nbningjiang.com/ningjiangshengxian/upload/E4024304C18F55E738F163AF7F848362.jpg","businessName":"新鲜花店","businessType":"经销商","lat":30.167223,"leader":"大动干戈","lng":118.379518,"mobile":"17633819345","score":0,"type":1}
     * couponAmounts : []
     * serviceAmount : 0
     * totalPrice : 3
     * userAddressDTO : {"acceptName":"qqq","city":"武汉市","cityId":420100,"createTime":"2020-05-20 14:51:50","districtId":420115,"hasDel":0,"id":13,"isDefault":1,"lat":30.457428,"lng":114.39867,"location":"湖北省武汉市江夏区华师园北路靠近光谷科技港","mobile":"15971498220","province":"湖北省","provinceId":420000,"region":"江夏区","userId":46}
     * userId : 46
     */

    private BaseBusinessBean baseBusiness;
    private int serviceAmount;
    private double totalPrice;
    private UserAddressDTOBean userAddressDTO;
    private int userId;
    private List<AppCheckDetailsDTOListBean> appCheckDetailsDTOList;
    private List<CouponAmountsBean> couponAmounts;

    public BaseBusinessBean getBaseBusiness() {
        return baseBusiness;
    }

    public void setBaseBusiness(BaseBusinessBean baseBusiness) {
        this.baseBusiness = baseBusiness;
    }

    public int getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(int serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public UserAddressDTOBean getUserAddressDTO() {
        if (userAddressDTO==null) {
            userAddressDTO =new UserAddressDTOBean();
        }
        return userAddressDTO;
    }

    public void setUserAddressDTO(UserAddressDTOBean userAddressDTO) {
        this.userAddressDTO = userAddressDTO;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<AppCheckDetailsDTOListBean> getAppCheckDetailsDTOList() {
        return appCheckDetailsDTOList;
    }

    public void setAppCheckDetailsDTOList(List<AppCheckDetailsDTOListBean> appCheckDetailsDTOList) {
        this.appCheckDetailsDTOList = appCheckDetailsDTOList;
    }

    public List<CouponAmountsBean> getCouponAmounts() {
        return couponAmounts;
    }

    public void setCouponAmounts(List<CouponAmountsBean> couponAmounts) {
        this.couponAmounts = couponAmounts;
    }

    public static class BaseBusinessBean {
        /**
         * businessAvatar : https://app.nbningjiang.com/ningjiangshengxian/upload/D688670AC0B5C350DD1525DA4274F3E0.jpg
         * businessId : 6
         * businessLicense : https://app.nbningjiang.com/ningjiangshengxian/upload/E4024304C18F55E738F163AF7F848362.jpg
         * businessName : 新鲜花店
         * businessType : 经销商
         * lat : 30.167223
         * leader : 大动干戈
         * lng : 118.379518
         * mobile : 17633819345
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
        private int unlinePayStatus;

        public int getUnlinePayStatus() {
            return unlinePayStatus;
        }

        public void setUnlinePayStatus(int unlinePayStatus) {
            this.unlinePayStatus = unlinePayStatus;
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

    public static class UserAddressDTOBean {
        /**
         * acceptName : qqq
         * city : 武汉市
         * cityId : 420100
         * createTime : 2020-05-20 14:51:50
         * districtId : 420115
         * hasDel : 0
         * id : 13
         * isDefault : 1
         * lat : 30.457428
         * lng : 114.39867
         * location : 湖北省武汉市江夏区华师园北路靠近光谷科技港
         * mobile : 15971498220
         * province : 湖北省
         * provinceId : 420000
         * region : 江夏区
         * userId : 46
         */

        private String acceptName;
        private String city;
        private int cityId;
        private String createTime;
        private int districtId;
        private int hasDel;
        private int id;
        private int isDefault;
        private double lat;
        private double lng;
        private String location;
        private String mobile;
        private String province;
        private int provinceId;
        private String region;
        private int userId;

        public String getAcceptName() {
            return acceptName == null ? "" : acceptName;
        }

        public void setAcceptName(String acceptName) {
            this.acceptName = acceptName;
        }

        public String getCity() {
            return city == null ? "" : city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public String getCreateTime() {
            return createTime == null ? "" : createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getDistrictId() {
            return districtId;
        }

        public void setDistrictId(int districtId) {
            this.districtId = districtId;
        }

        public int getHasDel() {
            return hasDel;
        }

        public void setHasDel(int hasDel) {
            this.hasDel = hasDel;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(int isDefault) {
            this.isDefault = isDefault;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public String getLocation() {
            return location == null ? "" : location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getMobile() {
            return mobile == null ? "" : mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getProvince() {
            return province == null ? "" : province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public int getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(int provinceId) {
            this.provinceId = provinceId;
        }

        public String getRegion() {
            return region == null ? "" : region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
    public static class CouponAmountsBean {

        /**
         * couponId : 1
         * name : 券
         * num : 1
         * subAmount : 10
         * type : 0
         */

        private int couponId;
        private String name;
        private int num;
        private double subAmount;
        private int type;

        public int getCouponId() {
            return couponId;
        }

        public void setCouponId(int couponId) {
            this.couponId = couponId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public double getSubAmount() {
            return subAmount;
        }

        public void setSubAmount(double subAmount) {
            this.subAmount = subAmount;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class AppCheckDetailsDTOListBean {
        /**
         * activityStatus : 0
         * baseActivity : {"typeName":"无活动"}
         * baseInfo : {"commodityHeaderUri":"https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2308693949,3552313049&fm=26&gp=0.jpg","commodityId":34,"commodityName":"红辣椒","originalPrice":7,"saleNum":2,"simpleInfo":"<h2>红辣椒<h2><p>简单的介绍<\/p>"}
         * cartId : 207
         * commodityNorms : {"commodityId":34,"currentTotalPrice":3,"norms":"250g","normsId":154,"num":1,"price":3,"stock":998,"subTotalPrice":0,"totalPrice":3}
         */

        private int activityStatus;
        private BaseActivityBean baseActivity;
        private BaseInfoBean baseInfo;
        private int cartId;
        private CommodityNormsBean commodityNorms;

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
            return baseInfo;
        }

        public void setBaseInfo(BaseInfoBean baseInfo) {
            this.baseInfo = baseInfo;
        }

        public int getCartId() {
            return cartId;
        }

        public void setCartId(int cartId) {
            this.cartId = cartId;
        }

        public CommodityNormsBean getCommodityNorms() {
            return commodityNorms;
        }

        public void setCommodityNorms(CommodityNormsBean commodityNorms) {
            this.commodityNorms = commodityNorms;
        }

        public static class BaseActivityBean {
            /**
             * typeName : 无活动
             */

            private String typeName;
            private int unlinePayStatus;

            public int getUnlinePayStatus() {
                return unlinePayStatus;
            }

            public void setUnlinePayStatus(int unlinePayStatus) {
                this.unlinePayStatus = unlinePayStatus;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }
        }

        public static class BaseInfoBean {
            /**
             * commodityHeaderUri : https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2308693949,3552313049&fm=26&gp=0.jpg
             * commodityId : 34
             * commodityName : 红辣椒
             * originalPrice : 7
             * saleNum : 2
             * simpleInfo : <h2>红辣椒<h2><p>简单的介绍</p>
             */

            private String commodityHeaderUri;
            private int commodityId;
            private String commodityName;
            private double originalPrice;
            private int saleNum;
            private String simpleInfo;
            private String simple;

            public String getSimple() {
                return simple == null ? "" : simple;
            }

            public void setSimple(String simple) {
                this.simple = simple;
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

        public static class CommodityNormsBean {
            /**
             * commodityId : 34
             * currentTotalPrice : 3
             * norms : 250g
             * normsId : 154
             * num : 1
             * price : 3
             * stock : 998
             * subTotalPrice : 0
             * totalPrice : 3
             */

            private int commodityId;
            private double currentTotalPrice;
            private String norms;
            private int normsId;
            private int num;
            private double price;
            private int stock;
            private double subTotalPrice;
            private double totalPrice;

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public double getCurrentTotalPrice() {
                return currentTotalPrice;
            }

            public void setCurrentTotalPrice(double currentTotalPrice) {
                this.currentTotalPrice = currentTotalPrice;
            }

            public String getNorms() {
                return norms;
            }

            public void setNorms(String norms) {
                this.norms = norms;
            }

            public int getNormsId() {
                return normsId;
            }

            public void setNormsId(int normsId) {
                this.normsId = normsId;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public double getSubTotalPrice() {
                return subTotalPrice;
            }

            public void setSubTotalPrice(double subTotalPrice) {
                this.subTotalPrice = subTotalPrice;
            }

            public double getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(double totalPrice) {
                this.totalPrice = totalPrice;
            }
        }
    }
}
