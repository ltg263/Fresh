package com.power.fresh.bean.marki;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AlienChao
 * @date 2020/06/12 16:05
 */
public class MarkiOrderBean {

    /**
     * count : 1
     * list : [{"addresses":"yw136","createTime":"2020-04-20 22:05:56","deliveryId":1,"id":1,"lat":29.806374,"lng":121.560795,"location":"浙江省-宁波","mobile":"17621682558","orderDetailsDTOS":[{"activityName":"无活动","commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":15,"commodityName":"彩果","id":17,"normsId":117,"normsName":"红","num":1,"orderId":22,"salePrice":99.99,"subPrice":0,"surplusNum":1,"totalPrice":99.99},{"activityName":"无活动","commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":15,"commodityName":"彩果","id":18,"normsId":118,"normsName":"橙","num":1,"orderId":22,"salePrice":99.99,"subPrice":0,"surplusNum":1,"totalPrice":99.99},{"activityName":"无活动","commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":19,"commodityName":"猕猴桃","id":19,"normsId":132,"normsName":"500g","num":1,"orderId":22,"salePrice":40.99,"subPrice":0,"surplusNum":1,"totalPrice":40.99},{"activityName":"无活动","commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":24,"commodityName":"水蜜桃","id":20,"normsId":137,"normsName":"500g","num":3,"orderId":22,"salePrice":40.99,"subPrice":0,"surplusNum":3,"totalPrice":122.97}],"orderId":22}]
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
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * addresses : yw136
         * createTime : 2020-04-20 22:05:56
         * deliveryId : 1
         * id : 1
         * lat : 29.806374
         * lng : 121.560795
         * location : 浙江省-宁波
         * mobile : 17621682558
         * orderDetailsDTOS : [{"activityName":"无活动","commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":15,"commodityName":"彩果","id":17,"normsId":117,"normsName":"红","num":1,"orderId":22,"salePrice":99.99,"subPrice":0,"surplusNum":1,"totalPrice":99.99},{"activityName":"无活动","commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":15,"commodityName":"彩果","id":18,"normsId":118,"normsName":"橙","num":1,"orderId":22,"salePrice":99.99,"subPrice":0,"surplusNum":1,"totalPrice":99.99},{"activityName":"无活动","commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":19,"commodityName":"猕猴桃","id":19,"normsId":132,"normsName":"500g","num":1,"orderId":22,"salePrice":40.99,"subPrice":0,"surplusNum":1,"totalPrice":40.99},{"activityName":"无活动","commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":24,"commodityName":"水蜜桃","id":20,"normsId":137,"normsName":"500g","num":3,"orderId":22,"salePrice":40.99,"subPrice":0,"surplusNum":3,"totalPrice":122.97}]
         * orderId : 22
         */

        private String addresses;
        private String createTime;
        private int deliveryId;
        private int id;
        private int orderStatus;
        private double lat;
        private double lng;
        private String location;
        private String mobile;
        private int orderId;
        private List<OrderDetailsDTOSBean> orderDetailsDTO;


        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public List<OrderDetailsDTOSBean> getOrderDetailsDTO() {
            if (orderDetailsDTO == null) {
                return new ArrayList<>();
            }
            return orderDetailsDTO;
        }

        public void setOrderDetailsDTO(List<OrderDetailsDTOSBean> orderDetailsDTO) {
            this.orderDetailsDTO = orderDetailsDTO;
        }

        public String getAddresses() {
            return addresses == null ? "" : addresses;
        }

        public void setAddresses(String addresses) {
            this.addresses = addresses;
        }

        public String getCreateTime() {
            return createTime == null ? "" : createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getDeliveryId() {
            return deliveryId;
        }

        public void setDeliveryId(int deliveryId) {
            this.deliveryId = deliveryId;
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

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public List<OrderDetailsDTOSBean> getOrderDetailsDTOS() {
            if (orderDetailsDTO == null) {
                return new ArrayList<>();
            }
            return orderDetailsDTO;
        }

        public void setOrderDetailsDTOS(List<OrderDetailsDTOSBean> orderDetailsDTO) {
            this.orderDetailsDTO = orderDetailsDTO;
        }

        public static class OrderDetailsDTOSBean {
            /**
             * activityName : 无活动
             * commodityHeaderUri : https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg
             * commodityId : 15
             * commodityName : 彩果
             * id : 17
             * normsId : 117
             * normsName : 红
             * num : 1
             * orderId : 22
             * salePrice : 99.99
             * subPrice : 0
             * surplusNum : 1
             * totalPrice : 99.99
             */

            private String activityName;
            private String commodityHeaderUri;
            private int commodityId;
            private String commodityName;
            private int id;
            private int normsId;
            private String normsName;
            private int num;
            private int orderId;
            private double salePrice;
            private double subPrice;
            private int surplusNum;
            private double totalPrice;

            public String getActivityName() {
                return activityName;
            }

            public void setActivityName(String activityName) {
                this.activityName = activityName;
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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

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

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public double getSalePrice() {
                return salePrice;
            }

            public void setSalePrice(double salePrice) {
                this.salePrice = salePrice;
            }

            public double getSubPrice() {
                return subPrice;
            }

            public void setSubPrice(double subPrice) {
                this.subPrice = subPrice;
            }

            public int getSurplusNum() {
                return surplusNum;
            }

            public void setSurplusNum(int surplusNum) {
                this.surplusNum = surplusNum;
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
