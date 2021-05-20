package com.power.fresh.bean.home;

import java.util.List;

/**
 * @author AlienChao
 * @date 2020/04/27 10:20
 */
public class HomeBooth {

    /**
     * list : [{"categoryName":"苹果","categoryOrder":1,"createTime":"2020-01-15 17:55:18","id":6,"image":"https://app.nbningjiang.com/ningjiangshengxian/upload/category-default.png","parentId":2,"status":1},{"categoryName":"猪肉","categoryOrder":1,"createTime":"2020-01-15 17:54:39","id":3,"image":"https://app.nbningjiang.com/ningjiangshengxian/upload/category-default.png","parentId":1,"status":1},{"categoryName":"肉食品","categoryOrder":1,"createTime":"2020-01-15 17:46:42","id":1,"image":"https://app.nbningjiang.com/ningjiangshengxian/upload/category-default.png","status":1},{"categoryName":"香蕉","categoryOrder":2,"createTime":"2020-01-15 17:55:31","id":7,"image":"https://app.nbningjiang.com/ningjiangshengxian/upload/category-default.png","parentId":2,"status":1},{"categoryName":"牛肉","categoryOrder":2,"createTime":"2020-01-15 17:54:58","id":4,"image":"https://app.nbningjiang.com/ningjiangshengxian/upload/category-default.png","parentId":1,"status":1},{"categoryName":"水果","categoryOrder":2,"createTime":"2020-01-15 17:54:02","id":2,"image":"https://app.nbningjiang.com/ningjiangshengxian/upload/category-default.png","status":1},{"categoryName":"青菜","categoryOrder":3,"createTime":"2020-01-15 17:55:46","id":8,"image":"https://app.nbningjiang.com/ningjiangshengxian/upload/category-default.png","parentId":2,"status":1},{"categoryName":"羊肉","categoryOrder":3,"createTime":"2020-01-15 17:55:05","id":5,"image":"https://app.nbningjiang.com/ningjiangshengxian/upload/category-default.png","parentId":1,"status":1},{"categoryName":"副食品","categoryOrder":5,"createTime":"2020-03-31 16:29:31","id":10,"image":"https://app.nbningjiang.com/ningjiangshengxian/upload/category-default.png","status":1},{"categoryName":"香料","categoryOrder":5,"id":11,"image":"https://app.nbningjiang.com/ningjiangshengxian/upload/category-default.png","parentId":10,"status":1}]
     * count : 10
     */

    private int count;
    private List<ListBean> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * categoryName : 苹果
         * categoryOrder : 1
         * createTime : 2020-01-15 17:55:18
         * id : 6
         * image : https://app.nbningjiang.com/ningjiangshengxian/upload/category-default.png
         * parentId : 2
         * status : 1
         */

        private String categoryName;
        private int categoryOrder;
        private String createTime;
        private int id;
        private String image;
        private int parentId;
        private int status;

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public int getCategoryOrder() {
            return categoryOrder;
        }

        public void setCategoryOrder(int categoryOrder) {
            this.categoryOrder = categoryOrder;
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

        public String getImage() {
            return image == null ? "" : image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
