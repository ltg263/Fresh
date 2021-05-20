package com.power.fresh.bean.bussiness;

import com.power.common_opensurce.App;

import java.util.List;

/**
 * @author AlienChao
 * @date 2020/05/29 17:04
 */
public class 待入库 {

    /**
     * list : [{"businessAvatar":"https://app.nbningjiang.com/ningjiangshengxian/upload/06C902D848BD94CBF27AA19A62F16009.jpg","businessName":"qqq","commodityName":"红富士苹果","createTime":"2020-05-26 09:52:39","id":19,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3352382498,1894897267&fm=26&gp=0.jpg","logNo":"203-20200526095238","norms":"20斤","normsId":181,"num":1,"orderId":134,"productNickName":"红富士苹果","qrLine":"https://app.nbningjiang.com/ningjiangshengxian/upload/order/qr/203-20200526095238.jpg","siteId":22,"status":2,"supplyAvatar":"https://app.nbningjiang.com/ningjiangshengxian/upload/0C4E2433522C9DB6AA60ECB62CFB2C1B.jpg","supplyId":11,"supplyName":"供应商2180","unit":"","updateTime":"2020-05-26 09:52:39"},{"businessAvatar":"https://app.nbningjiang.com/ningjiangshengxian/upload/06C902D848BD94CBF27AA19A62F16009.jpg","businessName":"qqq","commodityName":"红富士苹果","createTime":"2020-05-26 09:52:32","id":18,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3352382498,1894897267&fm=26&gp=0.jpg","logNo":"203-20200526095232","norms":"20斤","normsId":181,"num":1,"orderId":134,"productNickName":"红富士苹果","qrLine":"https://app.nbningjiang.com/ningjiangshengxian/upload/order/qr/203-20200526095232.jpg","siteId":22,"status":2,"supplyAvatar":"https://app.nbningjiang.com/ningjiangshengxian/upload/0C4E2433522C9DB6AA60ECB62CFB2C1B.jpg","supplyId":11,"supplyName":"供应商2180","unit":"","updateTime":"2020-05-26 09:52:32"},{"businessAvatar":"https://app.nbningjiang.com/ningjiangshengxian/upload/06C902D848BD94CBF27AA19A62F16009.jpg","businessName":"qqq","commodityName":"蓝色妖姬","createTime":"2020-05-26 02:37:48","id":17,"image":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3502336091,4207013521&fm=26&gp=0.jpg","logNo":"205-20200526023747","norms":"99支","normsId":183,"num":9,"orderId":133,"productNickName":"蓝色妖姬","qrLine":"https://app.nbningjiang.com/ningjiangshengxian/upload/order/qr/205-20200526023747.jpg","siteId":22,"status":2,"supplyAvatar":"https://app.nbningjiang.com/ningjiangshengxian/upload/0C4E2433522C9DB6AA60ECB62CFB2C1B.jpg","supplyId":11,"supplyName":"供应商2180","unit":"","updateTime":"2020-05-26 02:37:48"},{"businessAvatar":"https://app.nbningjiang.com/ningjiangshengxian/upload/06C902D848BD94CBF27AA19A62F16009.jpg","businessName":"qqq","commodityName":"蓝色妖姬","createTime":"2020-05-26 02:36:45","id":16,"image":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3502336091,4207013521&fm=26&gp=0.jpg","logNo":"205-20200526023644","norms":"99支","normsId":183,"num":1,"orderId":132,"productNickName":"蓝色妖姬","qrLine":"https://app.nbningjiang.com/ningjiangshengxian/upload/order/qr/205-20200526023644.jpg","siteId":22,"status":2,"supplyAvatar":"https://app.nbningjiang.com/ningjiangshengxian/upload/0C4E2433522C9DB6AA60ECB62CFB2C1B.jpg","supplyId":11,"supplyName":"供应商2180","unit":"","updateTime":"2020-05-26 02:36:45"},{"businessAvatar":"https://app.nbningjiang.com/ningjiangshengxian/upload/06C902D848BD94CBF27AA19A62F16009.jpg","businessName":"qqq","commodityName":"黄金圣女果","createTime":"2020-05-26 00:45:02","id":15,"image":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=107487147,1705962821&fm=15&gp=0.jpg","logNo":"204-20200526004501","norms":"20斤","normsId":182,"num":1,"orderId":131,"productNickName":"黄金圣女果","qrLine":"https://app.nbningjiang.com/ningjiangshengxian/upload/order/qr/204-20200526004501.jpg","siteId":22,"status":2,"supplyAvatar":"https://app.nbningjiang.com/ningjiangshengxian/upload/0C4E2433522C9DB6AA60ECB62CFB2C1B.jpg","supplyId":11,"supplyName":"供应商2180","unit":"","updateTime":"2020-05-26 00:45:02"},{"businessAvatar":"https://app.nbningjiang.com/ningjiangshengxian/upload/06C902D848BD94CBF27AA19A62F16009.jpg","businessName":"qqq","commodityName":"蓝色妖姬","createTime":"2020-05-25 20:40:02","id":14,"image":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3502336091,4207013521&fm=26&gp=0.jpg","logNo":"205-20200525204001","norms":"99支","normsId":183,"num":1,"orderId":132,"productNickName":"蓝色妖姬","qrLine":"https://app.nbningjiang.com/ningjiangshengxian/upload/order/qr/205-20200525204001.jpg","siteId":22,"status":2,"supplyAvatar":"https://app.nbningjiang.com/ningjiangshengxian/upload/0C4E2433522C9DB6AA60ECB62CFB2C1B.jpg","supplyId":11,"supplyName":"供应商2180","unit":"","updateTime":"2020-05-25 20:40:02"},{"businessAvatar":"https://app.nbningjiang.com/ningjiangshengxian/upload/06C902D848BD94CBF27AA19A62F16009.jpg","businessName":"qqq","commodityName":"蓝色妖姬","createTime":"2020-05-25 18:03:51","id":13,"image":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3502336091,4207013521&fm=26&gp=0.jpg","logNo":"205-20200525180350","norms":"99支","normsId":183,"num":9,"orderId":133,"productNickName":"蓝色妖姬","qrLine":"https://app.nbningjiang.com/ningjiangshengxian/upload/order/qr/205-20200525180350.jpg","siteId":22,"status":2,"supplyAvatar":"https://app.nbningjiang.com/ningjiangshengxian/upload/0C4E2433522C9DB6AA60ECB62CFB2C1B.jpg","supplyId":11,"supplyName":"供应商2180","unit":"","updateTime":"2020-05-25 18:03:51"},{"businessAvatar":"https://app.nbningjiang.com/ningjiangshengxian/upload/06C902D848BD94CBF27AA19A62F16009.jpg","businessName":"qqq","commodityName":"黄金圣女果","createTime":"2020-05-25 16:11:00","id":12,"image":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=107487147,1705962821&fm=15&gp=0.jpg","logNo":"204-20200525161059","norms":"20斤","normsId":182,"num":1,"orderId":131,"productNickName":"黄金圣女果","qrLine":"https://app.nbningjiang.com/ningjiangshengxian/upload/order/qr/204-20200525161059.jpg","siteId":22,"status":2,"supplyAvatar":"https://app.nbningjiang.com/ningjiangshengxian/upload/0C4E2433522C9DB6AA60ECB62CFB2C1B.jpg","supplyId":11,"supplyName":"供应商2180","unit":"","updateTime":"2020-05-25 16:11:00"}]
     * listCount : 8
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
         * businessAvatar : https://app.nbningjiang.com/ningjiangshengxian/upload/06C902D848BD94CBF27AA19A62F16009.jpg
         * businessName : qqq
         * commodityName : 红富士苹果
         * createTime : 2020-05-26 09:52:39
         * id : 19
         * image : https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3352382498,1894897267&fm=26&gp=0.jpg
         * logNo : 203-20200526095238
         * norms : 20斤
         * normsId : 181
         * num : 1
         * orderId : 134
         * productNickName : 红富士苹果
         * qrLine : https://app.nbningjiang.com/ningjiangshengxian/upload/order/qr/203-20200526095238.jpg
         * siteId : 22
         * status : 2
         * supplyAvatar : https://app.nbningjiang.com/ningjiangshengxian/upload/0C4E2433522C9DB6AA60ECB62CFB2C1B.jpg
         * supplyId : 11
         * supplyName : 供应商2180
         * unit :
         * updateTime : 2020-05-26 09:52:39
         */

        private String businessAvatar;
        private String businessName;
        private String commodityName;
        private String createTime;
        private int id;
        private String image;
        private String logNo;
        private String norms;
        private int normsId;
        private int num;
        private int orderId;
        private String productNickName;
        private String qrLine;
        private int siteId;
        private int status;
        private String supplyAvatar;
        private int supplyId;
        private String supplyName;
        private String unit;
        private String updateTime;

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

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
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

        public String getLogNo() {
            return logNo;
        }

        public void setLogNo(String logNo) {
            this.logNo = logNo;
        }

        public String getNorms() {
            return norms;
        }

        public void setNorms(String norms) {
            this.norms = norms;
        }

        public int getNormsId() {
            return normsId;
        }

        public void setNormsId(int normsId) {
            this.normsId = normsId;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getProductNickName() {
            return productNickName;
        }

        public void setProductNickName(String productNickName) {
            this.productNickName = productNickName;
        }

        public String getQrLine() {
            return qrLine;
        }

        public void setQrLine(String qrLine) {
            this.qrLine = qrLine;
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

        public String getSupplyAvatar() {
            return supplyAvatar;
        }

        public void setSupplyAvatar(String supplyAvatar) {
            this.supplyAvatar = supplyAvatar;
        }

        public int getSupplyId() {
            return supplyId;
        }

        public void setSupplyId(int supplyId) {
            this.supplyId = supplyId;
        }

        public String getSupplyName() {
            return supplyName;
        }

        public void setSupplyName(String supplyName) {
            this.supplyName = supplyName;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
