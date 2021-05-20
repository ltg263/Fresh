package com.power.common_opensurce;

/**
 * @author AlienChao
 * @date 2020/05/24 14:29
 */
public class UserInfoCc {

    /**
     * authStatus : 1
     * createTime : 2020-04-24 16:12:18
     * gender : 1
     * id : 25
     * logPort : 1
     * nickname : 用户：8220
     * portraitUri : http://static.kaixinnanchang.com/
     * userNo : 15971498220
     * userType : 0
     */


    private int authStatus;
    private String createTime;
    private int gender;
    private int id;
    /** 用户登陆：1， 经销商登陆：2，供应商登陆：3，配送员登陆：4 */
    private int logPort;
    private String nickname;
    private String portraitUri;
    private String userNo;
    private int userType;
    /**
     * aliUnionId :
     * bannedEndTime : null
     * bannedReason :
     * bannedStartTime : null
     * birth : 北京-北京市-东城区
     * cityId : 110100
     * cityStr : 北京市
     * deleteTime : null
     * expireTime : 2020-12-05 00:00:00
     * hasDelete : 0
     * password :
     * provinceId : 110000
     * provinceStr : 北京
     * qrLine :
     * refererId : 0
     * saltValue :
     * status : 1
     * updateTime : 2020-05-25 16:15:41
     * wxUnionId :
     */

    private String aliUnionId;
    private Object bannedEndTime;
    private String bannedReason;
    private Object bannedStartTime;
    private String birth;
    private int cityId;
    private String cityStr;
    private Object deleteTime;
    private String expireTime;
    private int hasDelete;
    private String password;
    private int provinceId;
    private int regionId;
    private String regionStr;
    private String provinceStr;
    private String qrLine;
    private int refererId;
    private String saltValue;
    private int status;
    private String updateTime;
    private String wxUnionId;


    public String getRegionStr() {
        return regionStr == null ? "" : regionStr;
    }

    public void setRegionStr(String regionStr) {
        this.regionStr = regionStr;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public int getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(int authStatus) {
        this.authStatus = authStatus;
    }

    public String getCreateTime() {
        return createTime == null ? "" : createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLogPort() {
        return logPort;
    }

    public void setLogPort(int logPort) {
        this.logPort = logPort;
    }

    public String getNickname() {
        return nickname == null ? "" : nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPortraitUri() {
        return portraitUri == null ? "" : portraitUri;
    }

    public void setPortraitUri(String portraitUri) {
        this.portraitUri = portraitUri;
    }

    public String getUserNo() {
        return userNo == null ? "" : userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getAliUnionId() {
        return aliUnionId;
    }

    public void setAliUnionId(String aliUnionId) {
        this.aliUnionId = aliUnionId;
    }

    public Object getBannedEndTime() {
        return bannedEndTime;
    }

    public void setBannedEndTime(Object bannedEndTime) {
        this.bannedEndTime = bannedEndTime;
    }

    public String getBannedReason() {
        return bannedReason;
    }

    public void setBannedReason(String bannedReason) {
        this.bannedReason = bannedReason;
    }

    public Object getBannedStartTime() {
        return bannedStartTime;
    }

    public void setBannedStartTime(Object bannedStartTime) {
        this.bannedStartTime = bannedStartTime;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityStr() {
        return cityStr;
    }

    public void setCityStr(String cityStr) {
        this.cityStr = cityStr;
    }

    public Object getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Object deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public int getHasDelete() {
        return hasDelete;
    }

    public void setHasDelete(int hasDelete) {
        this.hasDelete = hasDelete;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceStr() {
        return provinceStr;
    }

    public void setProvinceStr(String provinceStr) {
        this.provinceStr = provinceStr;
    }

    public String getQrLine() {
        return qrLine;
    }

    public void setQrLine(String qrLine) {
        this.qrLine = qrLine;
    }

    public int getRefererId() {
        return refererId;
    }

    public void setRefererId(int refererId) {
        this.refererId = refererId;
    }

    public String getSaltValue() {
        return saltValue;
    }

    public void setSaltValue(String saltValue) {
        this.saltValue = saltValue;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getWxUnionId() {
        return wxUnionId;
    }

    public void setWxUnionId(String wxUnionId) {
        this.wxUnionId = wxUnionId;
    }
}
