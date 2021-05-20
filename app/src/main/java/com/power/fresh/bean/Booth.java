package com.power.fresh.bean;

/**
 * 工作台-快捷办公
 * @author AlienChao
 * @date 2019/08/12 15:38
 */
public class Booth {

    private String name;
    private int resourcesId;


    public String getName() {
        return name == null ? "" : name;
    }

    public int getResourcesId() {
        return resourcesId;
    }

    public Booth setName(String name) {
        this.name = name;
        return this;
    }

    public Booth setResourcesId(int resourcesId) {
        this.resourcesId = resourcesId;
        return this;
    }
}
