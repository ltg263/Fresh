package com.power.fresh.bean.event;

/**
 * 首页点击进入 排序
 * @author AlienChao
 * @date 2020/09/22 09:38
 */
public class EventSort {

    private String title;
    private int position;

    public EventSort(String title, int position) {
        this.title = title;
        this.position = position;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public int getPosition() {
        return position;
    }
}
