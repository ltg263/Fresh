package com.power.fresh.bean.bussiness;

/**
 * @author AlienChao
 * @date 2020/05/26 22:19
 */
public class LiuShuiBean {


    /**
     * sumAmountSub : 1
     * sumAmountInsert : null
     */

    private double sumAmountSub;
    private Double sumAmountInsert;

    public double getSumAmountSub() {
        return sumAmountSub;
    }

    public void setSumAmountSub(double sumAmountSub) {
        this.sumAmountSub = sumAmountSub;
    }

    public Double getSumAmountInsert() {
        if (sumAmountInsert==null) {
            sumAmountInsert=0.0;
        }
        return sumAmountInsert;
    }

    public void setSumAmountInsert(Double sumAmountInsert) {
        this.sumAmountInsert = sumAmountInsert;
    }
}
