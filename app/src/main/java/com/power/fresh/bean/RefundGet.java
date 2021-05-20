package com.power.fresh.bean;

import java.util.List;

/**
 * @author AlienChao
 * @date 2020/08/06 18:01
 */
public  class RefundGet {

    /**
     * acceptance :
     * businessAvatar :
     * businessId : 42
     * businessMobile : 18458794212
     * businessName : 小店家人
     * businessPhone : 18458794212
     * commodityId : 5769
     * commodityInfo : {"activityName":"无活动","commodityHeaderUri":"https://app.nbningjiang.com/ningjiangshengxian/upload/commodity/A08EBC7AA7874CE83A9B1AFA0B9651EA.jpg","commodityName":"醋","normsName":"50g","salePrice":0,"subPrice":0,"unit":""}
     * createTime : 2020-08-06 16:03:49  YYYY--06
     * deliveryId : 0
     * deliveryInfo :
     * deliveryMobile :
     * deliveryName :
     * id : 28
     * images : null
     * imgs :
     * jsonOrderCommodity : {"activityName":"无活动","commodityHeaderUri":"https://app.nbningjiang.com/ningjiangshengxian/upload/commodity/A08EBC7AA7874CE83A9B1AFA0B9651EA.jpg","commodityName":"醋","normsName":"50g","salePrice":0,"subPrice":0,"unit":""}
     * nickName : 用户：0406
     * normsId : 11294
     * num : 1
     * orderEndTime : null
     * orderId : 503
     * orderStatus : 1
     * reason :
     * refundChannel : 1
     * refundNo : 200806160348008875
     * refundPrice : 0.01
     * refundStatus : APPLYING
     * refundThirdNo :
     * refundTime : null
     * refundType : 0
     * remark :
     * simplePrice : 0.01
     * transactionInfo :
     * transportPrice : 0
     * userAvatar : https://app.nbningjiang.com/ningjiangshengxian/upload/88A9695681ECC7AEF6D590257359B3EC.jpg
     * userId : 217
     */

    private String acceptance;
    private String businessAvatar;
    private int businessId;
    private String businessMobile;
    private String businessName;
    private String businessPhone;
    private int commodityId;
    private String commodityInfo;
    private String createTime;
    private int deliveryId;
    private String deliveryInfo;
    private String deliveryMobile;
    private String deliveryName;
    private int id;
    private List<String> images;
    private String imgs;
    private JsonOrderCommodityBean jsonOrderCommodity;
    private String nickName;
    private int normsId;
    private int num;
    private Object orderEndTime;
    private int orderId;
    private int orderStatus;
    private String reason;
    private int refundChannel;
    private String refundNo;
    private double refundPrice;
    private String refundStatus;
    private String refundThirdNo;
    private Object refundTime;
    private int refundType;
    private String remark;
    private double simplePrice;
    private String transactionInfo;
    private int transportPrice;
    private String userAvatar;
    private int userId;

    public String getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(String acceptance) {
        this.acceptance = acceptance;
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

    public String getBusinessMobile() {
        return businessMobile;
    }

    public void setBusinessMobile(String businessMobile) {
        this.businessMobile = businessMobile;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
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

    public String getCreateTime() {
        return createTime;
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

    public String getDeliveryInfo() {
        return deliveryInfo;
    }

    public void setDeliveryInfo(String deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }

    public String getDeliveryMobile() {
        return deliveryMobile;
    }

    public void setDeliveryMobile(String deliveryMobile) {
        this.deliveryMobile = deliveryMobile;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  List<String> getImages() {
        return images;
    }

    public void setImages( List<String> images) {
        this.images = images;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public JsonOrderCommodityBean getJsonOrderCommodity() {
        return jsonOrderCommodity;
    }

    public void setJsonOrderCommodity(JsonOrderCommodityBean jsonOrderCommodity) {
        this.jsonOrderCommodity = jsonOrderCommodity;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public Object getOrderEndTime() {
        return orderEndTime;
    }

    public void setOrderEndTime(Object orderEndTime) {
        this.orderEndTime = orderEndTime;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getRefundChannel() {
        return refundChannel;
    }

    public void setRefundChannel(int refundChannel) {
        this.refundChannel = refundChannel;
    }

    public String getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo;
    }

    public double getRefundPrice() {
        return refundPrice;
    }

    public void setRefundPrice(double refundPrice) {
        this.refundPrice = refundPrice;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getRefundThirdNo() {
        return refundThirdNo;
    }

    public void setRefundThirdNo(String refundThirdNo) {
        this.refundThirdNo = refundThirdNo;
    }

    public Object getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Object refundTime) {
        this.refundTime = refundTime;
    }

    public int getRefundType() {
        return refundType;
    }

    public void setRefundType(int refundType) {
        this.refundType = refundType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public double getSimplePrice() {
        return simplePrice;
    }

    public void setSimplePrice(double simplePrice) {
        this.simplePrice = simplePrice;
    }

    public String getTransactionInfo() {
        return transactionInfo;
    }

    public void setTransactionInfo(String transactionInfo) {
        this.transactionInfo = transactionInfo;
    }

    public int getTransportPrice() {
        return transportPrice;
    }

    public void setTransportPrice(int transportPrice) {
        this.transportPrice = transportPrice;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static class JsonOrderCommodityBean {
        /**
         * activityName : 无活动
         * commodityHeaderUri : https://app.nbningjiang.com/ningjiangshengxian/upload/commodity/A08EBC7AA7874CE83A9B1AFA0B9651EA.jpg
         * commodityName : 醋
         * normsName : 50g
         * salePrice : 0
         * subPrice : 0
         * unit :
         */

        private String activityName;
        private String commodityHeaderUri;
        private String commodityName;
        private String normsName;
        private double salePrice;
        private double subPrice;
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

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
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

        public void setSalePrice(int salePrice) {
            this.salePrice = salePrice;
        }

        public double getSubPrice() {
            return subPrice;
        }

        public void setSubPrice(double subPrice) {
            this.subPrice = subPrice;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
}
