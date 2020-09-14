
package com.example.bloodbank.data.model.generalSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralSource2 {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private GeneralSourceData generalSource2Data;

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

    public GeneralSourceData getGeneralSource2Data() {
        return generalSource2Data;
    }

    public void setGeneralSource2Data(GeneralSourceData generalSource2Data) {
        this.generalSource2Data = generalSource2Data;
    }

}
