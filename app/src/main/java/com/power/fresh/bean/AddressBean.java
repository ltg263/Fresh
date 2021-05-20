package com.power.fresh.bean;

import androidx.annotation.NonNull;

import com.power.common_opensurce.App;

import java.util.List;

/**
 * @author AlienChao
 * @date 2020/05/20 09:32
 */
public class AddressBean {

    /**
     * list : [{"acceptName":"www","city":"武汉市","cityId":420100,"createTime":"2020-05-19 14:33:32","districtId":420115,"hasDel":0,"id":10,"isDefault":1,"location":"湖北省武汉市江夏区华师园北路靠近光谷科技港","mobile":"15971498220","province":"湖北省","provinceId":420000,"region":"江夏区","userId":46}]
     * listCount : 1
     */

    private int listCount;
    private List<ListBean> list;

    public int getListCount() {
        return listCount;
    }

    public void setListCount(int listCount) {
        this.listCount = listCount;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * acceptName : www
         * city : 武汉市
         * cityId : 420100
         * createTime : 2020-05-19 14:33:32
         * districtId : 420115
         * hasDel : 0
         * id : 10
         * isDefault : 1
         * location : 湖北省武汉市江夏区华师园北路靠近光谷科技港
         * mobile : 15971498220
         * province : 湖北省
         * provinceId : 420000
         * region : 江夏区
         * userId : 46
         */

        private String acceptName;
        private String city;
        private int cityId;
        private String createTime;
        private int districtId;
        private int hasDel;
        private int id;
        private int isDefault;
        private String location;
        private String mobile;
        private String province;
        private int provinceId;
        private String region;
        private int userId;


        @NonNull
        @Override
        public String toString() {
            return App.getmGson().toJson(this);
        }

        public String getAcceptName() {
            return acceptName;
        }

        public void setAcceptName(String acceptName) {
            this.acceptName = acceptName;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getDistrictId() {
            return districtId;
        }

        public void setDistrictId(int districtId) {
            this.districtId = districtId;
        }

        public int getHasDel() {
            return hasDel;
        }

        public void setHasDel(int hasDel) {
            this.hasDel = hasDel;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(int isDefault) {
            this.isDefault = isDefault;
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

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public int getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(int provinceId) {
            this.provinceId = provinceId;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
