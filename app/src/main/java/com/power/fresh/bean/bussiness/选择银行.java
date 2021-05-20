package com.power.fresh.bean.bussiness;

/**
 * @author AlienChao
 * @date 2020/05/25 17:31
 */
public class 选择银行 {

    /**
     * bank : 安徽省农村信用社
     * code : ARCU
     * id : 76
     * image : https://apimg.alipay.com/combo.png?d=cashier&t=ARCU
     */

    private String bank;
    private String code;
    private int id;
    private String image;

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}
