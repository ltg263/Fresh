package com.power.fresh.bean.event;

/**
 * @author AlienChao
 * @date 2020/06/08 11:45
 */
public class EventTimeOut {

    private String e;

    public EventTimeOut(String e) {
        this.e = e;
    }

    public String getE() {
        return e == null ? "" : e;
    }

    public void setE(String e) {
        this.e = e;
    }
}
