package com.power.fresh.bean.bussiness;

/**
 * @author AlienChao
 * @date 2020/05/25 17:19
 */
public class 商品管理统计 {

    /**
     * selectOrderCount : 0件
     * boughtOrderCount : 1件
     * initStatusCount : 0件
     * failStatusCount : 0件
     * totalCount : 0件
     * finishOrderCount : 0件
     */

    private String selectOrderCount;
    private String boughtOrderCount;
    private String initStatusCount;
    private String failStatusCount;
    private String totalCount;
    private String finishOrderCount;

    public String getSelectOrderCount() {
        return selectOrderCount;
    }

    public void setSelectOrderCount(String selectOrderCount) {
        this.selectOrderCount = selectOrderCount;
    }

    public String getBoughtOrderCount() {
        return boughtOrderCount;
    }

    public void setBoughtOrderCount(String boughtOrderCount) {
        this.boughtOrderCount = boughtOrderCount;
    }

    public String getInitStatusCount() {
        return initStatusCount;
    }

    public void setInitStatusCount(String initStatusCount) {
        this.initStatusCount = initStatusCount;
    }

    public String getFailStatusCount() {
        return failStatusCount;
    }

    public void setFailStatusCount(String failStatusCount) {
        this.failStatusCount = failStatusCount;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getFinishOrderCount() {
        return finishOrderCount;
    }

    public void setFinishOrderCount(String finishOrderCount) {
        this.finishOrderCount = finishOrderCount;
    }
}
