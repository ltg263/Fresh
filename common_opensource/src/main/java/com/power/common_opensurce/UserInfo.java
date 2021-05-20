package com.power.common_opensurce;

/**
 * @author AlienChao
 * @date 2020/04/24 16:47
 */
public class UserInfo {

    /**
     * tokenId : 15e38a69-0fed-4c6d-8f77-c5c96f603761_1
     * userBase : {"adminId":13,"clientType":3,"createTime":"2020-04-24 16:12:18","gender":1,"id":25,"logPort":1,"nickname":"用户：8220","portraitUri":"http://static.kaixinnanchang.com/","userNo":"15971498220","userType":0}
     */

    private String tokenId;
    private UserBaseBean userBase;

    public String getTokenId() {
        return tokenId == null ? "" : tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public UserBaseBean getUserBase() {
        if (userBase==null) {
            userBase=new UserBaseBean();
        }
        return userBase;
    }

    public void setUserBase(UserBaseBean userBase) {
        this.userBase = userBase;
    }

    public static class UserBaseBean {
        /**
         * adminId : 13
         * clientType : 3
         * createTime : 2020-04-24 16:12:18
         * gender : 1
         * id : 25
         * logPort : 1
         * nickname : 用户：8220
         * portraitUri : http://static.kaixinnanchang.com/
         * userNo : 15971498220
         * userType : 0
         */

        private int adminId;
        private int clientType;
        private String createTime;
        private int gender;
        private int id;
        private int logPort;
        private String nickname;
        private String portraitUri;
        private String userNo;
        private int userType;

        public int getAdminId() {
            return adminId;
        }

        public void setAdminId(int adminId) {
            this.adminId = adminId;
        }

        public int getClientType() {
            return clientType;
        }

        public void setClientType(int clientType) {
            this.clientType = clientType;
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
    }
}
