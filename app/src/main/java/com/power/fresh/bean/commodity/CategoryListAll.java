package com.power.fresh.bean.commodity;

import java.util.List;

/**
 * @author AlienChao
 * @date 2020/09/21 17:12
 */
public class CategoryListAll {

    /**
     * categoryName : 蔬菜
     * categoryNo : 01
     * categoryOrder : 0
     * children : [{"categoryName":"叶菜","categoryNo":"0101","categoryOrder":0,"children":null,"createTime":"2020-07-23 17:04:01","id":101,"image":"","level":2,"parentId":1,"status":1},{"categoryName":"根茎","categoryNo":"0102","categoryOrder":0,"children":null,"createTime":"2020-07-23 17:04:10","id":102,"image":"","level":2,"parentId":1,"status":1},{"categoryName":"瓜果","categoryNo":"0103","categoryOrder":0,"children":null,"createTime":"2020-07-23 17:04:13","id":103,"image":"","level":2,"parentId":1,"status":1},{"categoryName":"葱姜蒜","categoryNo":"0104","categoryOrder":0,"children":null,"createTime":"2020-07-23 17:04:13","id":104,"image":"","level":2,"parentId":1,"status":1},{"categoryName":"豆类","categoryNo":"0105","categoryOrder":0,"children":null,"createTime":"2020-07-23 17:04:13","id":105,"image":"","level":2,"parentId":1,"status":1},{"categoryName":"茵菇","categoryNo":"0106","categoryOrder":0,"children":null,"createTime":"2020-07-23 17:04:13","id":106,"image":"","level":2,"parentId":1,"status":1},{"categoryName":"速冻蔬菜","categoryNo":"0107","categoryOrder":0,"children":null,"createTime":"2020-07-23 17:04:13","id":107,"image":"","level":2,"parentId":1,"status":1},{"categoryName":"净菜区","categoryNo":"0108","categoryOrder":0,"children":null,"createTime":"2020-07-23 17:04:13","id":108,"image":"","level":2,"parentId":1,"status":1},{"categoryName":"腌制蔬菜","categoryNo":"0109","categoryOrder":0,"children":null,"createTime":"2020-07-23 17:04:13","id":109,"image":"","level":2,"parentId":1,"status":1},{"categoryName":"蔬菜","categoryNo":"01","categoryOrder":0,"children":null,"createTime":"2020-07-23 17:04:00","id":1,"image":"","level":1,"parentId":0,"status":1}]
     * createTime : 2020-07-23 17:04:00
     * id : 1
     * image :
     * level : 1
     * parentId : 0
     * status : 1
     */

    private String categoryName;
    private String categoryNo;
    private int categoryOrder;
    private String createTime;
    private int id;
    private String image;
    private int level;
    private int parentId;
    private int status;
    private List<ChildrenBean> children;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(String categoryNo) {
        this.categoryNo = categoryNo;
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
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    public static class ChildrenBean {
        /**
         * categoryName : 叶菜
         * categoryNo : 0101
         * categoryOrder : 0
         * children : null
         * createTime : 2020-07-23 17:04:01
         * id : 101
         * image :
         * level : 2
         * parentId : 1
         * status : 1
         */

        private String categoryName;
        private String categoryNo;
        private int categoryOrder;
        private Object children;
        private String createTime;
        private int id;
        private String image;
        private int level;
        private int parentId;
        private int status;

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getCategoryNo() {
            return categoryNo;
        }

        public void setCategoryNo(String categoryNo) {
            this.categoryNo = categoryNo;
        }

        public int getCategoryOrder() {
            return categoryOrder;
        }

        public void setCategoryOrder(int categoryOrder) {
            this.categoryOrder = categoryOrder;
        }

        public Object getChildren() {
            return children;
        }

        public void setChildren(Object children) {
            this.children = children;
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
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
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
