package com.chen.concise.api;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AlienChao
 * @date 2020/01/11 20:06
 */
public class NoticeEntity {


    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    /**
     * versionCode : 1
     * result : [{"noticeTitle":"微信抢红包必看","jump":"跳转的界面","isClick":true,"noticeType":"local","noticeContent":"新版本将会在2.24更新"},{"noticeTitle":"微信抢红包必看","jump":"跳转的界面","isClick":true,"noticeType":"local","noticeContent":"新版本将会在2.24更新"}]
     */

    private int versionCode;
    private List<ResultBean> result;

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public List<ResultBean> getResult() {
        if (result == null) {
            return new ArrayList<>();
        }
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * noticeTitle : 微信抢红包必看
         * jump : 跳转的界面
         * isClick : true
         * noticeType : local
         * noticeContent : 新版本将会在2.24更新
         */

        private String noticeTitle;
        private String jump;
        private boolean isClick;
        private String noticeType;
        private String noticeContent;

        public String getNoticeTitle() {
            return noticeTitle == null ? "" : noticeTitle;
        }

        public void setNoticeTitle(String noticeTitle) {
            this.noticeTitle = noticeTitle;
        }

        public String getJump() {
            return jump == null ? "" : jump;
        }

        public void setJump(String jump) {
            this.jump = jump;
        }

        public boolean isClick() {
            return isClick;
        }

        public void setClick(boolean click) {
            isClick = click;
        }

        public String getNoticeType() {
            return noticeType == null ? "" : noticeType;
        }

        public void setNoticeType(String noticeType) {
            this.noticeType = noticeType;
        }

        public String getNoticeContent() {
            return noticeContent == null ? "" : noticeContent;
        }

        public void setNoticeContent(String noticeContent) {
            this.noticeContent = noticeContent;
        }
    }
}
