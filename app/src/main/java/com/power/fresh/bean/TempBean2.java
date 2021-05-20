package com.power.fresh.bean;

import androidx.databinding.ObservableField;

/**
 * @author AlienChao
 * @date 2020/04/29 14:38
 */
public class TempBean2 {

    private String name;


    private int pwd;

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPwd() {
        return pwd;
    }

    public void setPwd(int pwd) {
        this.pwd = pwd;
    }
}
