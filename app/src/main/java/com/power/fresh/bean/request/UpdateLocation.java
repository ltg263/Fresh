package com.power.fresh.bean.request;

/**
 * @author AlienChao
 * @date 2020/05/20 15:36
 */
public class UpdateLocation {

    /**
     * id : 1
     * lat : 30.3
     * lng : 121.4
     * districtId : 110101
     * location : 天河南
     * mobile : 18458796666
     * acceptName : 撒旦法
     */

    private int id;
    private int cityId;
    private String lat;
    private String lng;
    private int districtId;
    private String location;
    private String mobile;
    private String acceptName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAcceptName() {
        return acceptName;
    }

    public void setAcceptName(String acceptName) {
        this.acceptName = acceptName;
    }
}
