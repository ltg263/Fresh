package com.power.fresh.bean;

import java.util.List;

/**
 * @author AlienChao
 * @date 2020/05/29 15:16
 */
public class CommentList {

    /**
     * list : [{"avatar":"http://img.qqzhi.com/uploads/2018-11-29/064441604.jpg","businessAvatar":"","businessName":"http://img.hao661.com/www.hao661.com/uploads/allimg/c140830/14093YO213410-2T3P.jpg","content":"1","createTime":"2020-04-28 17:01:29","id":6,"imgUrl":"","imgUrls":["http://tmp/wxf0df53148f8c3323.o6zAJs3Yyw1UGyt-caPszq-J_sY0.90OUKP2cRAj6077fbefef6c97ebe216e28d634cd5f48.jpg","http://tmp/wxf0df53148f8c3323.o6zAJs3Yyw1UGyt-caPszq-J_sY0.90OUKP2cRAj6077fbefef6c97ebe216e28d634cd5f48.jpg"],"lable":0,"nickname":"普通用户","orderId":28,"score":5,"siteId":2,"status":1,"userId":21},{"avatar":"https://app.nbningjiang.com/ningjiangshengxian/upload/4EFBD826A2E0B1C96A3C3F702347418E.jpg","businessAvatar":"","businessName":"http://img.hao661.com/www.hao661.com/uploads/allimg/c140830/14093YO213410-2T3P.jpg","content":"比较好的质量","createTime":"2020-04-23 08:56:34","id":5,"imgUrl":"","imgUrls":["http://bbs.jooyoo.net/attachment/Mon_0905/24_65548_2835f8eaa933ff6.jpg","http://bbs.jooyoo.net/attachment/Mon_0905/24_65548_2835f8eaa933ff6.jpg","http://bbs.jooyoo.net/attachment/Mon_0905/24_65548_2835f8eaa933ff6.jpg"],"lable":0,"nickname":"用户：2180","orderId":23,"score":5,"siteId":2,"status":1,"userId":22},{"avatar":"https://app.nbningjiang.com/ningjiangshengxian/upload/4EFBD826A2E0B1C96A3C3F702347418E.jpg","businessAvatar":"","businessName":"http://img.hao661.com/www.hao661.com/uploads/allimg/c140830/14093YO213410-2T3P.jpg","content":"Add add a awcawecawec ap过年更广泛","createTime":"2020-04-21 16:29:28","id":3,"imgUrl":"","imgUrls":["https://app.nbningjiang.com/ningjiangshengxian/upload/DE67C0BF33264E7CF60ED2B1610C8769.jpg","https://app.nbningjiang.com/ningjiangshengxian/upload/7464B2D177B6D3297C0C9AB71696C29A.jpg","https://app.nbningjiang.com/ningjiangshengxian/upload/F9C8912A019AEB4E5C4319D34347E4C4.jpg","https://app.nbningjiang.com/ningjiangshengxian/upload/656F2B94ED4F618E5EC507D401C06202.jpg"],"lable":0,"nickname":"用户：2180","orderId":23,"score":4.5,"siteId":2,"status":1,"userId":22},{"avatar":"https://app.nbningjiang.com/ningjiangshengxian/upload/4EFBD826A2E0B1C96A3C3F702347418E.jpg","businessAvatar":"","businessName":"http://img.hao661.com/www.hao661.com/uploads/allimg/c140830/14093YO213410-2T3P.jpg","content":"Sdfsdfsdf sfafeawefaw dfsafe sawed aefweg","createTime":"2020-04-21 16:12:56","id":2,"imgUrl":"","imgUrls":["https://app.nbningjiang.com/ningjiangshengxian/upload/DE67C0BF33264E7CF60ED2B1610C8769.jpg","https://app.nbningjiang.com/ningjiangshengxian/upload/7464B2D177B6D3297C0C9AB71696C29A.jpg"],"lable":0,"nickname":"用户：2180","orderId":23,"score":5,"siteId":2,"status":1,"userId":22},{"avatar":"http://img.qqzhi.com/uploads/2018-11-29/064441604.jpg","businessAvatar":"","businessName":"http://img.hao661.com/www.hao661.com/uploads/allimg/c140830/14093YO213410-2T3P.jpg","content":"比较好的质量","createTime":"2020-04-15 15:41:36","id":1,"imgUrl":"","imgUrls":["http://bbs.jooyoo.net/attachment/Mon_0905/24_65548_2835f8eaa933ff6.jpg","http://bbs.jooyoo.net/attachment/Mon_0905/24_65548_2835f8eaa933ff6.jpg","http://bbs.jooyoo.net/attachment/Mon_0905/24_65548_2835f8eaa933ff6.jpg"],"lable":0,"nickname":"普通用户","orderId":16,"score":5,"siteId":2,"status":1,"userId":21}]
     * listCount : 5
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
         * avatar : http://img.qqzhi.com/uploads/2018-11-29/064441604.jpg
         * businessAvatar :
         * businessName : http://img.hao661.com/www.hao661.com/uploads/allimg/c140830/14093YO213410-2T3P.jpg
         * content : 1
         * createTime : 2020-04-28 17:01:29
         * id : 6
         * imgUrl :
         * imgUrls : ["http://tmp/wxf0df53148f8c3323.o6zAJs3Yyw1UGyt-caPszq-J_sY0.90OUKP2cRAj6077fbefef6c97ebe216e28d634cd5f48.jpg","http://tmp/wxf0df53148f8c3323.o6zAJs3Yyw1UGyt-caPszq-J_sY0.90OUKP2cRAj6077fbefef6c97ebe216e28d634cd5f48.jpg"]
         * lable : 0
         * nickname : 普通用户
         * orderId : 28
         * score : 5
         * siteId : 2
         * status : 1
         * userId : 21
         */

        private String avatar;
        private String businessAvatar;
        private String businessName;
        private String content;
        private String createTime;
        private int id;
        private String imgUrl;
        private int lable;
        private String nickname;
        private int orderId;
        private double score;
        private int siteId;
        private int status;
        private int userId;
        private List<String> imgUrls;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getBusinessAvatar() {
            return businessAvatar;
        }

        public void setBusinessAvatar(String businessAvatar) {
            this.businessAvatar = businessAvatar;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getLable() {
            return lable;
        }

        public void setLable(int lable) {
            this.lable = lable;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public int getSiteId() {
            return siteId;
        }

        public void setSiteId(int siteId) {
            this.siteId = siteId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public List<String> getImgUrls() {
            return imgUrls;
        }

        public void setImgUrls(List<String> imgUrls) {
            this.imgUrls = imgUrls;
        }
    }
}
