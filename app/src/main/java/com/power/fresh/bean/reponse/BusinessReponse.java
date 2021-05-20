package com.power.fresh.bean.reponse;

/**
 * @author AlienChao
 * @date 2020/05/24 21:28
 */
public class BusinessReponse {

    /**
     * businessLicense : https://app.nbningjiang.com/ningjiangshengxian/upload/D2A88E30FB40F997F6E5713730BF5899.jpg
     * siteHeaderUri : https://app.nbningjiang.com/ningjiangshengxian/upload/06C902D848BD94CBF27AA19A62F16009.jpg
     * translate : https://app.nbningjiang.com/ningjiangshengxian/upload/1CE9EC3430A368FC97CD6A0C3F42E924.jpg
     * mobile : eee
     * infoSubmitType : 1
     * simpleInfo : Aaaa
     * type : 1
     * lat : 30.504343
     * address : 湖北省武汉市洪山区关山二路靠近光谷8号
     * regionId : 420111
     * leaderName : www
     * siteName : qqq
     * lng : 114.422335
     */

    private Integer id;
    private String businessLicense;
    private String siteHeaderUri;
    private String translate;
    private String mobile;
    private String infoSubmitType;
    private String simpleInfo;
    private String type;
    private String lat;
    private String address;
    private String regionId;
    private String leaderName;
    private String siteName;
    private String lng;


    /**商家/供应商认证 加了三个字段  */
    private String cardUri;
    private String cardBankUri;
    private String license;

    public String getCardUri() {
        return cardUri == null ? "" : cardUri;
    }

    public void setCardUri(String cardUri) {
        this.cardUri = cardUri;
    }

    public String getCardBankUri() {
        return cardBankUri == null ? "" : cardBankUri;
    }

    public void setCardBankUri(String cardBankUri) {
        this.cardBankUri = cardBankUri;
    }

    public String getLicense() {
        return license == null ? "" : license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getSiteHeaderUri() {
        return siteHeaderUri;
    }

    public void setSiteHeaderUri(String siteHeaderUri) {
        this.siteHeaderUri = siteHeaderUri;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getInfoSubmitType() {
        return infoSubmitType;
    }

    public void setInfoSubmitType(String infoSubmitType) {
        this.infoSubmitType = infoSubmitType;
    }

    public String getSimpleInfo() {
        return simpleInfo;
    }

    public void setSimpleInfo(String simpleInfo) {
        this.simpleInfo = simpleInfo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
