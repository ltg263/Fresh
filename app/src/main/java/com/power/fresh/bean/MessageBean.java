package com.power.fresh.bean;

import java.util.List;

/**
 * @author AlienChao
 * @date 2020/06/12 14:03
 */
public class MessageBean {

    /**
     * list : [{"createTime":"2020-06-12 13:47:04","id":68,"msg":"您好,您的用户信息认证状态变更,认证成功","paramId":0,"status":1,"title":"您好,您的用户信息认证状态变更","type":10,"userId":54},{"createTime":"2020-06-12 11:39:55","id":67,"msg":"您好,您的用户信息认证状态变更,认证成功","paramId":0,"status":1,"title":"您好,您的用户信息认证状态变更","type":10,"userId":54},{"createTime":"2020-05-26 13:45:07","id":25,"msg":"","paramId":0,"status":2,"title":"您好,您的用户信息认证状态变更","type":10,"userId":54},{"createTime":"2020-05-26 13:21:00","id":22,"msg":"","paramId":0,"status":2,"title":"您好,您的用户信息认证状态变更","type":10,"userId":54},{"createTime":"2020-05-26 13:17:08","id":21,"msg":"","paramId":0,"status":2,"title":"您好,您的用户信息认证状态变更","type":10,"userId":54},{"createTime":"2020-05-26 13:12:58","id":20,"msg":"","paramId":0,"status":2,"title":"您好,您的用户信息认证状态变更","type":10,"userId":54},{"createTime":"2020-05-26 13:12:18","id":19,"msg":"","paramId":0,"status":2,"title":"您好,您的用户信息认证状态变更","type":10,"userId":54},{"createTime":"2020-05-26 13:11:25","id":18,"msg":"","paramId":0,"status":2,"title":"您好,您的用户信息认证状态变更","type":10,"userId":54},{"createTime":"2020-05-26 13:11:13","id":17,"msg":"","paramId":0,"status":2,"title":"您好,您的用户信息认证状态变更","type":10,"userId":54}]
     * listCount : 9
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
         * createTime : 2020-06-12 13:47:04
         * id : 68
         * msg : 您好,您的用户信息认证状态变更,认证成功
         * paramId : 0
         * status : 1
         * title : 您好,您的用户信息认证状态变更
         * type : 10
         * userId : 54
         */

        private String createTime;
        private int id;
        private String msg;
        private int paramId;
        private int status;
        private String title;
        private int type;
        private int userId;

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

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getParamId() {
            return paramId;
        }

        public void setParamId(int paramId) {
            this.paramId = paramId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
