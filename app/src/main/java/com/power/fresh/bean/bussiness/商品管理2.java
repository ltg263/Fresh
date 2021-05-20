package com.power.fresh.bean.bussiness;

import java.util.List;

/**
 * @author AlienChao
 * @date 2020/05/25 17:20
 */
public class 商品管理2 {

    /**
     * list : [{"arrivalTime":null,"businessId":11,"couponId":0,"couponPrice":0,"createTime":"2020-05-25 16:10:36","currentTotalPrice":150,"deliveryTime":null,"estimatedArrivalTime":null,"id":131,"orderAddress":{"address":"湖北省-武汉市-江夏区-湖北省武汉市江夏区华师园北路靠近光谷科技港","addressee":"qq","deliverInfo":"","id":100,"lat":30.457441,"lng":114.398695,"mobile":"15971498228","orderId":131},"orderDetailsDTO":[{"activityName":"","commodityHeaderUri":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=107487147,1705962821&fm=15&gp=0.jpg","commodityId":46,"commodityInfo":"","commodityName":"黄金圣女果","id":183,"normsId":182,"normsName":"20斤","num":1,"orderId":131,"salePrice":150,"subPrice":0,"surplusNum":1,"totalPrice":150,"unit":""}],"orderNo":"wxpay200525161035001011","orderNoThird":"1-APP200525161036002400","orderStatus":2,"payExpireTime":"2020-05-25 16:25:36","payFinishTime":"2020-05-25 16:10:59","payStatus":"SUCCESS","payType":1,"remake":"","totalPrice":150,"transportPrice":0,"userId":50}]
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
         * arrivalTime : null
         * businessId : 11
         * couponId : 0
         * couponPrice : 0
         * createTime : 2020-05-25 16:10:36
         * currentTotalPrice : 150
         * deliveryTime : null
         * estimatedArrivalTime : null
         * id : 131
         * orderAddress : {"address":"湖北省-武汉市-江夏区-湖北省武汉市江夏区华师园北路靠近光谷科技港","addressee":"qq","deliverInfo":"","id":100,"lat":30.457441,"lng":114.398695,"mobile":"15971498228","orderId":131}
         * orderDetailsDTO : [{"activityName":"","commodityHeaderUri":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=107487147,1705962821&fm=15&gp=0.jpg","commodityId":46,"commodityInfo":"","commodityName":"黄金圣女果","id":183,"normsId":182,"normsName":"20斤","num":1,"orderId":131,"salePrice":150,"subPrice":0,"surplusNum":1,"totalPrice":150,"unit":""}]
         * orderNo : wxpay200525161035001011
         * orderNoThird : 1-APP200525161036002400
         * orderStatus : 2
         * payExpireTime : 2020-05-25 16:25:36
         * payFinishTime : 2020-05-25 16:10:59
         * payStatus : SUCCESS
         * payType : 1
         * remake :
         * totalPrice : 150
         * transportPrice : 0
         * userId : 50
         */

        private Object arrivalTime;
        private int businessId;
        private int couponId;
        private int couponPrice;
        private String createTime;
        private int currentTotalPrice;
        private Object deliveryTime;
        private Object estimatedArrivalTime;
        private int id;
        private OrderAddressBean orderAddress;
        private String orderNo;
        private String orderNoThird;
        private int orderStatus;
        private String payExpireTime;
        private String payFinishTime;
        private String payStatus;
        private int payType;
        private String remake;
        private int totalPrice;
        private int transportPrice;
        private int userId;
        private List<OrderDetailsDTOBean> orderDetailsDTO;

        public Object getArrivalTime() {
            return arrivalTime;
        }

        public void setArrivalTime(Object arrivalTime) {
            this.arrivalTime = arrivalTime;
        }

        public int getBusinessId() {
            return businessId;
        }

        public void setBusinessId(int businessId) {
            this.businessId = businessId;
        }

        public int getCouponId() {
            return couponId;
        }

        public void setCouponId(int couponId) {
            this.couponId = couponId;
        }

        public int getCouponPrice() {
            return couponPrice;
        }

        public void setCouponPrice(int couponPrice) {
            this.couponPrice = couponPrice;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getCurrentTotalPrice() {
            return currentTotalPrice;
        }

        public void setCurrentTotalPrice(int currentTotalPrice) {
            this.currentTotalPrice = currentTotalPrice;
        }

        public Object getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(Object deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

        public Object getEstimatedArrivalTime() {
            return estimatedArrivalTime;
        }

        public void setEstimatedArrivalTime(Object estimatedArrivalTime) {
            this.estimatedArrivalTime = estimatedArrivalTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public OrderAddressBean getOrderAddress() {
            return orderAddress;
        }

        public void setOrderAddress(OrderAddressBean orderAddress) {
            this.orderAddress = orderAddress;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getOrderNoThird() {
            return orderNoThird;
        }

        public void setOrderNoThird(String orderNoThird) {
            this.orderNoThird = orderNoThird;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getPayExpireTime() {
            return payExpireTime;
        }

        public void setPayExpireTime(String payExpireTime) {
            this.payExpireTime = payExpireTime;
        }

        public String getPayFinishTime() {
            return payFinishTime;
        }

        public void setPayFinishTime(String payFinishTime) {
            this.payFinishTime = payFinishTime;
        }

        public String getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(String payStatus) {
            this.payStatus = payStatus;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public String getRemake() {
            return remake;
        }

        public void setRemake(String remake) {
            this.remake = remake;
        }

        public int getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
        }

        public int getTransportPrice() {
            return transportPrice;
        }

        public void setTransportPrice(int transportPrice) {
            this.transportPrice = transportPrice;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public List<OrderDetailsDTOBean> getOrderDetailsDTO() {
            return orderDetailsDTO;
        }

        public void setOrderDetailsDTO(List<OrderDetailsDTOBean> orderDetailsDTO) {
            this.orderDetailsDTO = orderDetailsDTO;
        }

        public static class OrderAddressBean {
            /**
             * address : 湖北省-武汉市-江夏区-湖北省武汉市江夏区华师园北路靠近光谷科技港
             * addressee : qq
             * deliverInfo :
             * id : 100
             * lat : 30.457441
             * lng : 114.398695
             * mobile : 15971498228
             * orderId : 131
             */

            private String address;
            private String addressee;
            private String deliverInfo;
            private int id;
            private double lat;
            private double lng;
            private String mobile;
            private int orderId;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getAddressee() {
                return addressee;
            }

            public void setAddressee(String addressee) {
                this.addressee = addressee;
            }

            public String getDeliverInfo() {
                return deliverInfo;
            }

            public void setDeliverInfo(String deliverInfo) {
                this.deliverInfo = deliverInfo;
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

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }
        }

        public static class OrderDetailsDTOBean {
            /**
             * activityName :
             * commodityHeaderUri : https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=107487147,1705962821&fm=15&gp=0.jpg
             * commodityId : 46
             * commodityInfo :
             * commodityName : 黄金圣女果
             * id : 183
             * normsId : 182
             * normsName : 20斤
             * num : 1
             * orderId : 131
             * salePrice : 150
             * subPrice : 0
             * surplusNum : 1
             * totalPrice : 150
             * unit :
             */

            private String activityName;
            private String commodityHeaderUri;
            private int commodityId;
            private String commodityInfo;
            private String commodityName;
            private int id;
            private int normsId;
            private String normsName;
            private int num;
            private int orderId;
            private int salePrice;
            private int subPrice;
            private int surplusNum;
            private int totalPrice;
            private String unit;

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

            public String getCommodityInfo() {
                return commodityInfo;
            }

            public void setCommodityInfo(String commodityInfo) {
                this.commodityInfo = commodityInfo;
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

            public int getSalePrice() {
                return salePrice;
            }

            public void setSalePrice(int salePrice) {
                this.salePrice = salePrice;
            }

            public int getSubPrice() {
                return subPrice;
            }

            public void setSubPrice(int subPrice) {
                this.subPrice = subPrice;
            }

            public int getSurplusNum() {
                return surplusNum;
            }

            public void setSurplusNum(int surplusNum) {
                this.surplusNum = surplusNum;
            }

            public int getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(int totalPrice) {
                this.totalPrice = totalPrice;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }
        }
    }
}
