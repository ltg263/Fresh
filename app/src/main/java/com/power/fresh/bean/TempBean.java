package com.power.fresh.bean;

import androidx.databinding.ObservableField;

/**
 * @author AlienChao
 * @date 2020/04/29 14:38
 */
public class TempBean {

    private ObservableField<String> name;


    private ObservableField<Integer> pwd;


    public TempBean(String name, int pwd) {
        this.name = new ObservableField<>(name);
        this.pwd = new ObservableField<>(pwd);
    }


    public ObservableField<String> getName() {
        return name;
    }

    public void setName(ObservableField<String> name) {
        this.name = name;
    }

    public ObservableField<Integer> getPwd() {
        return pwd;
    }

    public void setPwd(ObservableField<Integer> pwd) {
        this.pwd = pwd;
    }
}
