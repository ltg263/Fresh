package com.power.fresh.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品详情
 * @author AlienChao
 * @date 2020/05/13 11:33
 */
public class CommodityDetails {


    /**
     * activityStatus : 1
     * business : {"address":"*****38号","id":2,"lat":16.407,"lng":39.90469,"mobile":"18458794212","simpleInfo":"营业时间：周一至周六 9.30-18.30","siteHeaderUri":"http://img.hao661.com/www.hao661.com/uploads/allimg/c140830/14093YO213410-2T3P.jpg","siteName":"我的商店"}
     * category : {"categoryName":"水果"}
     * categoryId : 2
     * channel : 1
     * commodityHeaderUri : https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg
     * commodityNormsRuleDTOS : [{"activitySalePrice":30.99,"activityStock":123,"commodityId":17,"costPrice":20.99,"id":130,"normsRule":"500g","refundNum":0,"saleNum":0,"salePrice":40.99,"stock":1000}]
     * hasHot : 0
     * information : 简单的介绍商品
     * name : 苹果
     * salePrice : 40.11
     * simple : 好吃的百香果
     * siteId : 2
     * status : 2
     * tActivity : {"activityName":"15:00开始抢购","endTime":"2020-06-01 13:59:59","id":10,"startTime":"2020-03-31 16:04:14","status":1}
     * tCommodityImgs : [{"commodityId":17,"id":85,"img":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg"}]
     * totalBrowse : 0
     * totalComment : 0
     * totalRefund : 0
     * totalSaleNum : 1
     * totalStock : 2000
     */

    private int activityStatus;
    private BusinessBean business;
    private CategoryBean category;
    private int categoryId;
    private int channel;
    private String commodityHeaderUri;
    private int hasHot;
    private String information;
    private String name;
    private double salePrice;
    private String simple;
    private int siteId;
    private int status;
    private TActivityBean tActivity;
    private int totalBrowse;
    private int totalComment;
    private int totalRefund;
    private int totalSaleNum;
    private int totalStock;
    private List<CommodityNormsRuleDTOSBean> commodityNormsRuleDTOS;
    private List<TCommodityImgsBean> tCommodityImgs;

    public int getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(int activityStatus) {
        this.activityStatus = activityStatus;
    }

    public BusinessBean getBusiness() {
        return business;
    }

    public void setBusiness(BusinessBean business) {
        this.business = business;
    }

    public CategoryBean getCategory() {
        return category;
    }

    public void setCategory(CategoryBean category) {
        this.category = category;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public String getCommodityHeaderUri() {
        return commodityHeaderUri == null ? "" : commodityHeaderUri;
    }

    public void setCommodityHeaderUri(String commodityHeaderUri) {
        this.commodityHeaderUri = commodityHeaderUri;
    }

    public int getHasHot() {
        return hasHot;
    }

    public void setHasHot(int hasHot) {
        this.hasHot = hasHot;
    }

    public String getInformation() {
        return information == null ? "" : information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public String getSimple() {
        return simple == null ? "" : simple;
    }

    public void setSimple(String simple) {
        this.simple = simple;
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

    public TActivityBean gettActivity() {
        return tActivity;
    }

    public void settActivity(TActivityBean tActivity) {
        this.tActivity = tActivity;
    }

    public int getTotalBrowse() {
        return totalBrowse;
    }

    public void setTotalBrowse(int totalBrowse) {
        this.totalBrowse = totalBrowse;
    }

    public int getTotalComment() {
        return totalComment;
    }

    public void setTotalComment(int totalComment) {
        this.totalComment = totalComment;
    }

    public int getTotalRefund() {
        return totalRefund;
    }

    public void setTotalRefund(int totalRefund) {
        this.totalRefund = totalRefund;
    }

    public int getTotalSaleNum() {
        return totalSaleNum;
    }

    public void setTotalSaleNum(int totalSaleNum) {
        this.totalSaleNum = totalSaleNum;
    }

    public int getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(int totalStock) {
        this.totalStock = totalStock;
    }

    public List<CommodityNormsRuleDTOSBean> getCommodityNormsRuleDTOS() {
        if (commodityNormsRuleDTOS == null) {
            commodityNormsRuleDTOS = new ArrayList<>();
            commodityNormsRuleDTOS.add(new CommodityNormsRuleDTOSBean());
        }
        return commodityNormsRuleDTOS;
    }

    public void setCommodityNormsRuleDTOS(List<CommodityNormsRuleDTOSBean> commodityNormsRuleDTOS) {
        this.commodityNormsRuleDTOS = commodityNormsRuleDTOS;
    }

    public List<TCommodityImgsBean> gettCommodityImgs() {
        if (tCommodityImgs == null) {
            return new ArrayList<>();
        }
        return tCommodityImgs;
    }

    public void settCommodityImgs(List<TCommodityImgsBean> tCommodityImgs) {
        this.tCommodityImgs = tCommodityImgs;
    }

    public static class BusinessBean {
        /**
         * address : *****38号
         * id : 2
         * lat : 16.407
         * lng : 39.90469
         * mobile : 18458794212
         * simpleInfo : 营业时间：周一至周六 9.30-18.30
         * siteHeaderUri : http://img.hao661.com/www.hao661.com/uploads/allimg/c140830/14093YO213410-2T3P.jpg
         * siteName : 我的商店
         */

        private String address;
        private int id;
        private double lat;
        private double lng;
        private String mobile;
        private String simpleInfo;
        private String siteHeaderUri;
        private String siteName;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getSimpleInfo() {
            return simpleInfo;
        }

        public void setSimpleInfo(String simpleInfo) {
            this.simpleInfo = simpleInfo;
        }

        public String getSiteHeaderUri() {
            return siteHeaderUri;
        }

        public void setSiteHeaderUri(String siteHeaderUri) {
            this.siteHeaderUri = siteHeaderUri;
        }

        public String getSiteName() {
            return siteName;
        }

        public void setSiteName(String siteName) {
            this.siteName = siteName;
        }
    }

    public static class CategoryBean {
        /**
         * categoryName : 水果
         */

        private String categoryName;

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }
    }

    public static class TActivityBean {
        /**
         * activityName : 15:00开始抢购
         * endTime : 2020-06-01 13:59:59
         * id : 10
         * startTime : 2020-03-31 16:04:14
         * status : 1
         */

        private String activityName;
        private String endTime;
        private int id;
        private String startTime;
        private int status;

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
    }

    public static class CommodityNormsRuleDTOSBean {
        /**
         * activitySalePrice : 30.99
         * activityStock : 123
         * commodityId : 17
         * costPrice : 20.99
         * id : 130
         * normsRule : 500g
         * refundNum : 0
         * saleNum : 0
         * salePrice : 40.99
         * stock : 1000
         */

        private double activitySalePrice;
        private int activityStock;
        private int commodityId;
        private double costPrice;
        private int id;
        private String normsRule;
        private int refundNum;
        private int saleNum;
        private double salePrice;
        private int stock;

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

    public static class TCommodityImgsBean {
        /**
         * commodityId : 17
         * id : 85
         * img : https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg
         */

        private int commodityId;
        private int id;
        private String img;

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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
