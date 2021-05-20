package com.power.fresh.bean;

import java.util.List;

/**
 * @author AlienChao
 * @date 2020/08/06 16:18
 */
public class BusinessUser {

    /**
     * list : [{"businessId":42,"businessName":"","createTime":"2020-08-03 10:36:32","member":"","nickname":"用户：0406","portraitUri":"https://app.nbningjiang.com/ningjiangshengxian/upload/default/avatar.png","siteMemberId":0,"userId":217},{"businessId":42,"businessName":"","createTime":"2020-08-03 09:40:40","member":"","nickname":"用户：8220","portraitUri":"https://app.nbningjiang.com/ningjiangshengxian/upload/3EAA26E05248B990C6A90E3320B08DE3.jpeg","siteMemberId":0,"userId":103},{"businessId":42,"businessName":"","createTime":"2020-07-28 09:46:32","member":"","nickname":"用户：0052","portraitUri":"https://app.nbningjiang.com/ningjiangshengxian/upload/7238C1637EE1CCC6DE37B80A940AF35A.jpeg","siteMemberId":0,"userId":191},{"businessId":42,"businessName":"","createTime":"2020-07-27 15:58:01","member":"","nickname":"AA陈","portraitUri":"https://app.nbningjiang.com/ningjiangshengxian/upload/E78FD81313089CF89378956A1DC60E93.jpg","siteMemberId":0,"userId":187},{"businessId":42,"businessName":"","createTime":"2020-07-27 15:52:03","member":"","nickname":"大傻子","portraitUri":"https://app.nbningjiang.com/ningjiangshengxian/upload/EDD42E4ABE993030274507D9F1F36D09.jpeg","siteMemberId":0,"userId":189},{"businessId":42,"businessName":"","createTime":"2020-07-24 10:30:37","member":"","nickname":"2","portraitUri":"https://app.nbningjiang.com/ningjiangshengxian/upload/C23676DE74F88D573BC3BD4B7B02ED87.jpeg","siteMemberId":0,"userId":104},{"businessId":42,"businessName":"","createTime":"2020-07-24 09:12:51","member":"","nickname":"用户：0052","portraitUri":"https://app.nbningjiang.com/ningjiangshengxian/upload/20DCCA46534BD6545DCE22C9AF55B11E.jpg","siteMemberId":0,"userId":102}]
     * listCount : 7
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
         * businessId : 42
         * businessName :
         * createTime : 2020-08-03 10:36:32
         * member :
         * nickname : 用户：0406
         * portraitUri : https://app.nbningjiang.com/ningjiangshengxian/upload/default/avatar.png
         * siteMemberId : 0
         * userId : 217
         */

        private int businessId;
        private String businessName;
        private String createTime;
        private String member;
        private String nickname;
        private String portraitUri;
        private int siteMemberId;
        private int userId;

        public int getBusinessId() {
            return businessId;
        }

        public void setBusinessId(int businessId) {
            this.businessId = businessId;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getMember() {
            return member;
        }

        public void setMember(String member) {
            this.member = member;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPortraitUri() {
            return portraitUri;
        }

        public void setPortraitUri(String portraitUri) {
            this.portraitUri = portraitUri;
        }

        public int getSiteMemberId() {
            return siteMemberId;
        }

        public void setSiteMemberId(int siteMemberId) {
            this.siteMemberId = siteMemberId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
