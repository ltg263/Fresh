package com.power.fresh.bean.alipay;

/**
 * @author AlienChao
 * @date 2020/05/31 19:23
 */
public class AlipayResponse {


    /**
     * alipay_trade_app_pay_response : {"code":"10000","msg":"Success","app_id":"2021001164602335","auth_app_id":"2021001164602335","charset":"utf-8","timestamp":"2020-05-31 19:21:19","out_trade_no":"ALI200531192109022630","total_amount":"0.01","trade_no":"2020053122001472381407256431","seller_id":"2088831475576550"}
     * sign : PufWIyCvmgM37pbQaJEmOoqHa7KbLWI4P3WKup74ZEu594RvaYCd4x4xgpWBxRD8G8x7BEWOeJQJFnVsqkKEWx9qrHYKUD/RKsp6/G4ZTI3ljvrSbrEyeytSOz09LrNqsiaXbzbjyz4QLAZ1DV7gvitDBSUYAj09l9EZwrXOkoTsTerEawOu/10Y/Wjqsi1lbFllv79JOGDXEh/GbnfIFq15NT4p6BygJP6Nvr5gRtuQRh5JGcXpAz5sTSE0v9Foh0K2mHHYAtWwTkYo74jaP2dpu1tquPmHp/D1AE0ivd8xBIM0tAPLxGAWL3sm7lgxQpJa1U//HEif95XCZumZsA==
     * sign_type : RSA2
     */

    private AlipayTradeAppPayResponseBean alipay_trade_app_pay_response;
    private String sign;
    private String sign_type;

    public AlipayTradeAppPayResponseBean getAlipay_trade_app_pay_response() {
        return alipay_trade_app_pay_response;
    }

    public void setAlipay_trade_app_pay_response(AlipayTradeAppPayResponseBean alipay_trade_app_pay_response) {
        this.alipay_trade_app_pay_response = alipay_trade_app_pay_response;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public static class AlipayTradeAppPayResponseBean {
        /**
         * code : 10000
         * msg : Success
         * app_id : 2021001164602335
         * auth_app_id : 2021001164602335
         * charset : utf-8
         * timestamp : 2020-05-31 19:21:19
         * out_trade_no : ALI200531192109022630
         * total_amount : 0.01
         * trade_no : 2020053122001472381407256431
         * seller_id : 2088831475576550
         */

        private String code;
        private String msg;
        private String app_id;
        private String auth_app_id;
        private String charset;
        private String timestamp;
        private String out_trade_no;
        private String total_amount;
        private String trade_no;
        private String seller_id;


        public String getCode() {
            return code == null ? "" : code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg == null ? "" : msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getApp_id() {
            return app_id == null ? "" : app_id;
        }

        public void setApp_id(String app_id) {
            this.app_id = app_id;
        }

        public String getAuth_app_id() {
            return auth_app_id == null ? "" : auth_app_id;
        }

        public void setAuth_app_id(String auth_app_id) {
            this.auth_app_id = auth_app_id;
        }

        public String getCharset() {
            return charset == null ? "" : charset;
        }

        public void setCharset(String charset) {
            this.charset = charset;
        }

        public String getTimestamp() {
            return timestamp == null ? "" : timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getOut_trade_no() {
            return out_trade_no == null ? "" : out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        public String getTotal_amount() {
            return total_amount == null ? "" : total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public String getTrade_no() {
            return trade_no == null ? "" : trade_no;
        }

        public void setTrade_no(String trade_no) {
            this.trade_no = trade_no;
        }

        public String getSeller_id() {
            return seller_id == null ? "" : seller_id;
        }

        public void setSeller_id(String seller_id) {
            this.seller_id = seller_id;
        }
    }
}
