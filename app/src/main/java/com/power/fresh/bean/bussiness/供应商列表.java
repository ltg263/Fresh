package com.power.fresh.bean.bussiness;

import java.util.List;

/**
 * @author AlienChao
 * @date 2020/05/25 17:22
 */
public class 供应商列表 {

    /**
     * list : [{"appCommodityDTOS":[],"couponCount":0,"distance":0.004202323295596716,"fee":"","id":24,"lat":30.457456,"lng":114.398703,"saleNum":0,"scope":0,"scout":0,"serviceDistance":-1,"servicePrice":0,"serviceStatus":2,"siteHeaderUri":"https://app.nbningjiang.com/ningjiangshengxian/upload/32958C6B868C744AFC475ADC8749698E.jpg","siteName":"经销商","taskAmount":0,"type":2,"unitType":0},{"appCommodityDTOS":[{"activity":null,"activityStatus":0,"commodityHeaderUri":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=107487147,1705962821&fm=15&gp=0.jpg","commodityId":46,"createTime":"2020-05-11 14:46:18","hasHot":0,"minPrice":0,"name":"黄金圣女果","rules":[{"activitySalePrice":0,"activityStock":0,"commodityId":46,"costPrice":88,"id":182,"normsRule":"20斤","qualityPeriod":"30天","refundNum":0,"saleNum":0,"salePrice":150,"stock":4995,"unit":"箱"}],"saleNum":5,"salePrice":30,"simpleInfo":"香甜可口","tcStatus":2,"totalStock":5000},{"activity":null,"activityStatus":0,"commodityHeaderUri":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3352382498,1894897267&fm=26&gp=0.jpg","commodityId":45,"createTime":"2020-05-11 14:42:13","hasHot":0,"minPrice":0,"name":"红富士苹果","rules":[{"activitySalePrice":0,"activityStock":0,"commodityId":45,"costPrice":16,"id":181,"normsRule":"20斤","qualityPeriod":"180天","refundNum":0,"saleNum":0,"salePrice":28,"stock":4992,"unit":"箱"}],"saleNum":4,"salePrice":30,"simpleInfo":"香甜可口","tcStatus":2,"totalStock":5000},{"activity":null,"activityStatus":0,"commodityHeaderUri":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3008821419,1025571207&fm=26&gp=0.jpg","commodityId":44,"createTime":"2020-05-11 14:40:05","hasHot":0,"minPrice":0,"name":"阿克苏苹果","rules":[{"activitySalePrice":0,"activityStock":0,"commodityId":44,"costPrice":40,"id":180,"normsRule":"20斤","qualityPeriod":"180天","refundNum":0,"saleNum":0,"salePrice":55,"stock":4996,"unit":"箱"}],"saleNum":2,"salePrice":60,"simpleInfo":"香甜可口","tcStatus":2,"totalStock":5000},{"activity":null,"activityStatus":0,"commodityHeaderUri":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3502336091,4207013521&fm=26&gp=0.jpg","commodityId":47,"createTime":"2020-05-11 14:54:23","hasHot":0,"minPrice":0,"name":"蓝色妖姬","rules":[{"activitySalePrice":0,"activityStock":0,"commodityId":47,"costPrice":399,"id":183,"normsRule":"99支","qualityPeriod":"30天","refundNum":0,"saleNum":0,"salePrice":699,"stock":4998,"unit":"一束"},{"activitySalePrice":0,"activityStock":0,"commodityId":47,"costPrice":50,"id":184,"normsRule":"11支","qualityPeriod":"30天","refundNum":0,"saleNum":0,"salePrice":100,"stock":4998,"unit":"一束"}],"saleNum":0,"salePrice":799,"simpleInfo":"好看","tcStatus":2,"totalStock":5000}],"couponCount":0,"distance":693.2224901160472,"fee":"","id":11,"lat":29.806374,"lng":121.560795,"saleNum":6,"scope":0,"scout":0,"serviceDistance":-1,"servicePrice":0,"serviceStatus":2,"siteHeaderUri":"https://app.nbningjiang.com/ningjiangshengxian/upload/0C4E2433522C9DB6AA60ECB62CFB2C1B.jpg","siteName":"供应商2180","taskAmount":0,"type":2,"unitType":0}]
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
         * appCommodityDTOS : []
         * couponCount : 0
         * distance : 0.004202323295596716
         * fee :
         * id : 24
         * lat : 30.457456
         * lng : 114.398703
         * saleNum : 0
         * scope : 0
         * scout : 0
         * serviceDistance : -1
         * servicePrice : 0
         * serviceStatus : 2
         * siteHeaderUri : https://app.nbningjiang.com/ningjiangshengxian/upload/32958C6B868C744AFC475ADC8749698E.jpg
         * siteName : 经销商
         * taskAmount : 0
         * type : 2
         * unitType : 0
         */

        private int couponCount;
        private double distance;
        private String fee;
        private int id;
        private double lat;
        private double lng;
        private int saleNum;
        private int scope;
        private int scout;
        private int serviceDistance;
        private int servicePrice;
        private int serviceStatus;
        private String siteHeaderUri;
        private String siteName;
        private int taskAmount;
        private int type;
        private int unitType;
        private List<?> appCommodityDTOS;

        public int getCouponCount() {
            return couponCount;
        }

        public void setCouponCount(int couponCount) {
            this.couponCount = couponCount;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
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

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }

        public int getScope() {
            return scope;
        }

        public void setScope(int scope) {
            this.scope = scope;
        }

        public int getScout() {
            return scout;
        }

        public void setScout(int scout) {
            this.scout = scout;
        }

        public int getServiceDistance() {
            return serviceDistance;
        }

        public void setServiceDistance(int serviceDistance) {
            this.serviceDistance = serviceDistance;
        }

        public int getServicePrice() {
            return servicePrice;
        }

        public void setServicePrice(int servicePrice) {
            this.servicePrice = servicePrice;
        }

        public int getServiceStatus() {
            return serviceStatus;
        }

        public void setServiceStatus(int serviceStatus) {
            this.serviceStatus = serviceStatus;
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

        public int getTaskAmount() {
            return taskAmount;
        }

        public void setTaskAmount(int taskAmount) {
            this.taskAmount = taskAmount;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUnitType() {
            return unitType;
        }

        public void setUnitType(int unitType) {
            this.unitType = unitType;
        }

        public List<?> getAppCommodityDTOS() {
            return appCommodityDTOS;
        }

        public void setAppCommodityDTOS(List<?> appCommodityDTOS) {
            this.appCommodityDTOS = appCommodityDTOS;
        }
    }
}
