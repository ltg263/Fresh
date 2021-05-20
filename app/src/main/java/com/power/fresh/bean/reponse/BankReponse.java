package com.power.fresh.bean.reponse;

/**
 * @author AlienChao
 * @date 2020/05/26 22:52
 */
public class BankReponse {

    /**
     * hasDefault : 1
     * mobile : ttttt
     * bankId : 76
     * name : qqq
     * bankNo : www
     * bankBranch : eeee
     */

    private String hasDefault;
    private String mobile;
    private String bankId;
    private String name;
    private String bankNo;
    private String bankBranch;

    public String getHasDefault() {
        return hasDefault;
    }

    public void setHasDefault(String hasDefault) {
        this.hasDefault = hasDefault;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }
}
