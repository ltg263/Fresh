package com.power.fresh.bean.bussiness;

/**
 * @author AlienChao
 * @date 2020/05/25 17:34
 */
public class 订单管理 {

    /**
     * refunApplyingdCount : 0条
     * arriveCount : 0条
     * sendingCount : 0条
     * unHarvestCount : 0条
     */

    private String refunApplyingdCount;
    private String arriveCount;
    private String sendingCount;
    private String unHarvestCount;

    public String getRefunApplyingdCount() {
        return refunApplyingdCount;
    }

    public void setRefunApplyingdCount(String refunApplyingdCount) {
        this.refunApplyingdCount = refunApplyingdCount;
    }

    public String getArriveCount() {
        return arriveCount;
    }

    public void setArriveCount(String arriveCount) {
        this.arriveCount = arriveCount;
    }

    public String getSendingCount() {
        return sendingCount;
    }

    public void setSendingCount(String sendingCount) {
        this.sendingCount = sendingCount;
    }

    public String getUnHarvestCount() {
        return unHarvestCount;
    }

    public void setUnHarvestCount(String unHarvestCount) {
        this.unHarvestCount = unHarvestCount;
    }
}
