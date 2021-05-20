package com.power.fresh.bean.order;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AlienChao
 * @date 2020/05/22 20:44
 */
public class OrderDetails {


    /**
     * avatar : http://static.kaixinnanchang.com/
     * businessAvatar : http://img.hao661.com/www.hao661.com/uploads/allimg/c140830/14093YO213410-2T3P.jpg
     * businessId : 2
     * businessName : 我的商店
     * nickName : 用户：8220
     * order : {"businessId":2,"createTime":"2020-05-22 10:30:02","currentTotalPrice":204.95,"id":127,"orderDetailsDTO":[{"activityName":"无活动","commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":23,"commodityName":"哈密华","id":178,"normsId":136,"normsName":"500g","num":5,"orderId":127,"salePrice":40.99,"subPrice":0,"surplusNum":5,"totalPrice":204.95}],"orderNo":"null200522103001001704","orderStatus":5,"payExpireTime":"2020-05-22 10:45:02","payFinishTime":"2020-05-22 10:50:01","payStatus":"CANCEL","payType":3,"totalPrice":204.95,"transportPrice":0,"userId":25}
     * orderAddress : {"address":"北京-北京市-东城区-北京北京市东城区北京-北京市-东城区","addressee":"超哥","id":96,"mobile":"15971498220","orderId":127}
     * userId : 25
     */

    private String avatar;
    private String businessAvatar;
    private int businessId;
    private String businessName;
    private String nickName;
    private OrderBean order;
    private OrderAddressBean orderAddress;
    private int userId;

    private String deliveryName;
    private String deliveryMobile;

    public String getDeliveryName() {
        return deliveryName == null ? "" : deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryMobile() {
        return deliveryMobile == null ? "" : deliveryMobile;
    }

    public void setDeliveryMobile(String deliveryMobile) {
        this.deliveryMobile = deliveryMobile;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public OrderBean getOrder() {
        if (order==null) {
            order=new OrderBean();
        }
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public OrderAddressBean getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(OrderAddressBean orderAddress) {
        this.orderAddress = orderAddress;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static class OrderBean {
        /**
         * businessId : 2
         * createTime : 2020-05-22 10:30:02
         * currentTotalPrice : 204.95
         * id : 127
         * orderDetailsDTO : [{"activityName":"无活动","commodityHeaderUri":"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg","commodityId":23,"commodityName":"哈密华","id":178,"normsId":136,"normsName":"500g","num":5,"orderId":127,"salePrice":40.99,"subPrice":0,"surplusNum":5,"totalPrice":204.95}]
         * orderNo : null200522103001001704
         * orderStatus : 5
         * payExpireTime : 2020-05-22 10:45:02
         * payFinishTime : 2020-05-22 10:50:01
         * payStatus : CANCEL
         * payType : 3
         * totalPrice : 204.95
         * transportPrice : 0
         * userId : 25
         */

        private int businessId;
        private String createTime;
        private double currentTotalPrice;
        private int id;
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
        private double subPriceAll;

        public double getSubPriceAll() {
            subPriceAll=0;
            for (int i = 0; i < getOrderDetailsDTO().size(); i++) {
                subPriceAll+= getOrderDetailsDTO().get(i).getSubPrice();
            }
            return subPriceAll;
        }

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

        public void setSubPriceAll(double subPriceAll) {
            this.subPriceAll = subPriceAll;
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

        public static class OrderDetailsDTOBean {
            /**
             * activityName : 无活动
             * commodityHeaderUri : https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2591035070,1115114969&fm=26&gp=0.jpg
             * commodityId : 23
             * commodityName : 哈密华
             * id : 178
             * normsId : 136
             * normsName : 500g
             * num : 5
             * orderId : 127
             * salePrice : 40.99
             * subPrice : 0
             * surplusNum : 5
             * totalPrice : 204.95
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

    public static class OrderAddressBean {
        /**
         * address : 北京-北京市-东城区-北京北京市东城区北京-北京市-东城区
         * addressee : 超哥
         * id : 96
         * mobile : 15971498220
         * orderId : 127
         */

        private String address;
        private String addressee;
        private int id;
        private String mobile;
        private int orderId;

        public String getAddress() {
            return address == null ? "" : address;
        }

        public String getAddressee() {
            return addressee == null ? "" : addressee;
        }

        public void setAddress(String address) {
            this.address = address;
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
}
