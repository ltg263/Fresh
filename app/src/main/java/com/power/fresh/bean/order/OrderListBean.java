package com.power.fresh.bean.order;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AlienChao
 * @date 2020/05/21 21:02
 */
public class OrderListBean {

    /**
     * list : [{"businessId":6,"createTime":"2020-05-20 17:19:59","currentTotalPrice":3,"id":119,"orderAddress":{"address":"湖北省-武汉市-江夏区-湖北省武汉市江夏区华师园北路靠近光谷科技港","addressee":"qqq","id":88,"lat":30.457428,"lng":114.39867,"mobile":"15971498220","orderId":119},"orderDetailsDTO":[{"activityName":"无活动","commodityHeaderUri":"https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2308693949,3552313049&fm=26&gp=0.jpg","commodityId":34,"commodityName":"红辣椒","id":166,"normsId":154,"normsName":"250g","num":1,"orderId":119,"salePrice":3,"subPrice":0,"surplusNum":1,"totalPrice":3}],"orderNo":"wxpay200520171958001534","orderStatus":5,"payExpireTime":"2020-05-20 17:34:59","payFinishTime":"2020-05-20 17:35:00","payStatus":"CANCEL","payType":1,"totalPrice":3,"transportPrice":0,"userId":46},{"businessId":2,"createTime":"2020-05-19 14:33:35","currentTotalPrice":99.99,"id":99,"orderAddress":{"address":"湖北省-武汉市-江夏区-湖北省武汉市江夏区华师园北路靠近光谷科技港","addressee":"www","id":68,"lat":30.457432,"lng":114.398691,"mobile":"15971498220","orderId":99},"orderDetailsDTO":[{"activityName":"无活动","commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":15,"commodityName":"彩果","id":144,"normsId":117,"normsName":"红","num":1,"orderId":99,"salePrice":99.99,"subPrice":0,"surplusNum":1,"totalPrice":99.99}],"orderNo":"wxpay200519143335002660","orderStatus":5,"payExpireTime":"2020-05-19 14:48:35","payFinishTime":"2020-05-19 14:50:00","payStatus":"CANCEL","payType":1,"totalPrice":99.99,"transportPrice":0,"userId":46}]
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
         * businessId : 6
         * createTime : 2020-05-20 17:19:59
         * currentTotalPrice : 3
         * id : 119
         * orderAddress : {"address":"湖北省-武汉市-江夏区-湖北省武汉市江夏区华师园北路靠近光谷科技港","addressee":"qqq","id":88,"lat":30.457428,"lng":114.39867,"mobile":"15971498220","orderId":119}
         * orderDetailsDTO : [{"activityName":"无活动","commodityHeaderUri":"https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2308693949,3552313049&fm=26&gp=0.jpg","commodityId":34,"commodityName":"红辣椒","id":166,"normsId":154,"normsName":"250g","num":1,"orderId":119,"salePrice":3,"subPrice":0,"surplusNum":1,"totalPrice":3}]
         * orderNo : wxpay200520171958001534
         * orderStatus : 5
         * payExpireTime : 2020-05-20 17:34:59
         * payFinishTime : 2020-05-20 17:35:00
         * payStatus : CANCEL
         * payType : 1
         * totalPrice : 3
         * transportPrice : 0
         * userId : 46
         */

        private int businessId;
        private String createTime;
        private double currentTotalPrice;
        private int id;
        private OrderAddressBean orderAddress;
        private String orderNo;
        private int orderStatus;
        private String payExpireTime;
        private String payFinishTime;
        private String payStatus;
        private int payType;
        private double totalPrice;
        private double transportPrice;
        private int userId;
        private List<OrderDetailsDTOBean> orderDetailsDTO;

        public int getBusinessId() {
            return businessId;
        }

        public void setBusinessId(int businessId) {
            this.businessId = businessId;
        }

        public String getCreateTime() {
            return createTime == null ? "" : createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public double getCurrentTotalPrice() {
            return currentTotalPrice;
        }

        public void setCurrentTotalPrice(double currentTotalPrice) {
            this.currentTotalPrice = currentTotalPrice;
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
            return orderNo == null ? "" : orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getPayExpireTime() {
            return payExpireTime == null ? "" : payExpireTime;
        }

        public void setPayExpireTime(String payExpireTime) {
            this.payExpireTime = payExpireTime;
        }

        public String getPayFinishTime() {
            return payFinishTime == null ? "" : payFinishTime;
        }

        public void setPayFinishTime(String payFinishTime) {
            this.payFinishTime = payFinishTime;
        }

        public String getPayStatus() {
            return payStatus == null ? "" : payStatus;
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

        public double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
        }

        public double getTransportPrice() {
            return transportPrice;
        }

        public void setTransportPrice(double transportPrice) {
            this.transportPrice = transportPrice;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public List<OrderDetailsDTOBean> getOrderDetailsDTO() {
            if (orderDetailsDTO == null) {
                orderDetailsDTO= new ArrayList<>();
                orderDetailsDTO.add(new OrderDetailsDTOBean());
            }
            return orderDetailsDTO;
        }

        public void setOrderDetailsDTO(List<OrderDetailsDTOBean> orderDetailsDTO) {
            this.orderDetailsDTO = orderDetailsDTO;
        }

        public static class OrderAddressBean {
            /**
             * address : 湖北省-武汉市-江夏区-湖北省武汉市江夏区华师园北路靠近光谷科技港
             * addressee : qqq
             * id : 88
             * lat : 30.457428
             * lng : 114.39867
             * mobile : 15971498220
             * orderId : 119
             */

            private String address;
            private String addressee;
            private int id;
            private double lat;
            private double lng;
            private String mobile;
            private int orderId;

            public String getAddress() {
                return address == null ? "" : address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getAddressee() {
                return addressee == null ? "" : addressee;
            }

            public void setAddressee(String addressee) {
                this.addressee = addressee;
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
        }

        public static class OrderDetailsDTOBean {
            /**
             * activityName : 无活动
             * commodityHeaderUri : https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2308693949,3552313049&fm=26&gp=0.jpg
             * commodityId : 34
             * commodityName : 红辣椒
             * id : 166
             * normsId : 154
             * normsName : 250g
             * num : 1
             * orderId : 119
             * salePrice : 3
             * subPrice : 0
             * surplusNum : 1
             * totalPrice : 3
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
                return activityName == null ? "" : activityName;
            }

            public void setActivityName(String activityName) {
                this.activityName = activityName;
            }

            public String getCommodityHeaderUri() {
                return commodityHeaderUri == null ? "" : commodityHeaderUri;
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
                return commodityName == null ? "" : commodityName;
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
                return normsName == null ? "" : normsName;
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
