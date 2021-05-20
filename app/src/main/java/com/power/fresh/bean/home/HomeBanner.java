package com.power.fresh.bean.home;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AlienChao
 * @date 2020/04/27 09:36
 */
public class HomeBanner {

    /**
     * list : [{"content":"2","createTime":"2020-03-31 16:12:12","id":3,"imgUrl":"http://ku.90sjimg.com/back_pic/03/85/39/9757cc2ead441ba.jpg","seat":2,"sort":2,"status":1,"title":"123456","type":2},{"content":"17","createTime":"2020-03-31 16:11:35","id":2,"imgUrl":"http://ku.90sjimg.com/back_pic/03/85/39/9757cc2ead441ba.jpg","seat":1,"sort":2,"status":1,"title":"title1","type":1},{"content":"16","createTime":"2020-03-24 14:53:11","id":1,"imgUrl":"http://ku.90sjimg.com/back_pic/03/85/39/9757cc2ead441ba.jpg","seat":1,"sort":1,"status":1,"title":"title","type":1}]
     * listCount : 3
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
        if (list == null) {
            list = new ArrayList<>();
            list.add(new ListBean());
        }
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * content : 2
         * createTime : 2020-03-31 16:12:12
         * id : 3
         * imgUrl : http://ku.90sjimg.com/back_pic/03/85/39/9757cc2ead441ba.jpg
         * seat : 2
         * sort : 2
         * status : 1
         * title : 123456
         * type : 2
         */

        private String content;
        private String createTime;
        private int id;
        private String imgUrl;
        private int seat;
        private int sort;
        private int status;
        private String title;
        private int type;

        public String getContent() {
            return content == null ? "" : content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return createTime == null ? "" : createTime;
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
            return imgUrl == null ? "" : imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getSeat() {
            return seat;
        }

        public void setSeat(int seat) {
            this.seat = seat;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title == null ? "" : title;
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
    }
}
