package com.power.fresh.bean.reponse;

/**
 * @author AlienChao
 * @date 2020/05/24 15:44
 */
public class UploadReponse {

    /**
     * fileName : D2A88E30FB40F997F6E5713730BF5899.jpg
     * localPhysical : C:/img/ningjiangshengxian/upload/
     * size : 502872
     * url : https://app.nbningjiang.com/ningjiangshengxian/upload/D2A88E30FB40F997F6E5713730BF5899.jpg
     */

    private String fileName;
    private String localPhysical;
    private String size;
    private String url;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLocalPhysical() {
        return localPhysical;
    }

    public void setLocalPhysical(String localPhysical) {
        this.localPhysical = localPhysical;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
