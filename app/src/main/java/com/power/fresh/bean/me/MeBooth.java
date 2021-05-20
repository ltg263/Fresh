package com.power.fresh.bean.me;

/**
 * 个人中心的展台
 * @author AlienChao
 * @date 2020/05/19 14:51
 */
public class MeBooth {

    private String title ;
    private int resourceId;


    public String getTitle() {
        return title == null ? "" : title;
    }

    public MeBooth setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getResourceId() {
        return resourceId;
    }

    public MeBooth setResourceId(int resourceId) {
        this.resourceId = resourceId;
        return this;
    }
}
