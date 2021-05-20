package com.power.common_opensurce.utils;

/**
 * @author AlienChao
 * @date 2020/05/20 11:56
 */
public class LocationDataBean {
    private double longitude;
    private double latitude;
    private String province;
    private String city;
    private String cityCode;
    private String adCode;
    private String district;
    private String road;
    private String poiname;
    private String street;
    private String streetNum;
    private String aoiname;




    public String getAdCode() {
        return adCode == null ? "" : adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getProvince() {
        return province == null ? "" : province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStreetNum() {
        return streetNum == null ? "" : streetNum;
    }

    public void setStreetNum(String streetNum) {
        this.streetNum = streetNum;
    }

    public String getCityCode() {
        return cityCode == null ? "" : cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getProvider() {
        return province == null ? "" : province;
    }

    public void setProvider(String provider) {
        this.province = provider;
    }

    public String getCity() {
        return city == null ? "" : city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district == null ? "" : district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getRoad() {
        return road == null ? "" : road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getPoiname() {
        return poiname == null ? "" : poiname;
    }

    public void setPoiname(String poiname) {
        this.poiname = poiname;
    }

    public String getStreet() {
        return street == null ? "" : street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAoiname() {
        return aoiname == null ? "" : aoiname;
    }

    public void setAoiname(String aoiname) {
        this.aoiname = aoiname;
    }
}
