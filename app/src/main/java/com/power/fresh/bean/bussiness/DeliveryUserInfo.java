package com.power.fresh.bean.bussiness;

/**
 * @author AlienChao
 * @date 2020/05/31 14:57
 */
public class DeliveryUserInfo {



    private int id;
    private int adminId;

    private String name;
    private String mobile;
    private String cardUrl;
    private String cardUrlBack;
    private int status;

    private String createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile == null ? "" : mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCardUrl() {
        return cardUrl == null ? "" : cardUrl;
    }

    public void setCardUrl(String cardUrl) {
        this.cardUrl = cardUrl;
    }

    public String getCardUrlBack() {
        return cardUrlBack == null ? "" : cardUrlBack;
    }

    public void setCardUrlBack(String cardUrlBack) {
        this.cardUrlBack = cardUrlBack;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime == null ? "" : createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
