package com.power.fresh.bean;

/**
 * @author AlienChao
 * @date 2020/06/12 14:24
 */
public class MessageDetails {

    /**
     * createTime : 2020-05-26 13:45:07
     * id : 25
     * msg :
     * paramId : 0
     * status : 1
     * title : 您好,您的用户信息认证状态变更
     * type : 10
     * userId : 54
     */

    private String createTime;
    private int id;
    private String msg;
    private int paramId;
    private int status;
    private String title;
    private int type;
    private int userId;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getParamId() {
        return paramId;
    }

    public void setParamId(int paramId) {
        this.paramId = paramId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
