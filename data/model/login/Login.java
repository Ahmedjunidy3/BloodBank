
package com.example.bloodbank.data.model.login;

import com.example.bloodbank.data.model.generalSource.GeneralSourceData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {


    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private LoginData loginData;
    @SerializedName("resetPassData")
    @Expose
    private GeneralSourceData resetPassData;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LoginData getLoginData() {
        return loginData;
    }

    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }

    public GeneralSourceData getGeneralSourceData() {
        return resetPassData;
    }

    public void setGeneralSourceData(GeneralSourceData resetPassData) {
        this.resetPassData = resetPassData;
    }



}
