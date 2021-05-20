package com.power.fresh.bean.requestBean;

/**
 * @author AlienChao
 * @date 2020/04/24 18:14
 */
public class SmsLoginBody {

    /**
     * username : 18458794212
     * verifyCode : 123
     * clientType : 3
     * logPort : 1
     */

    private String username;
    private String verifyCode;
    private int clientType;
    private int logPort;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public int getClientType() {
        return clientType;
    }

    public void setClientType(int clientType) {
        this.clientType = clientType;
    }

    public int getLogPort() {
        return logPort;
    }

    public void setLogPort(int logPort) {
        this.logPort = logPort;
    }
}
