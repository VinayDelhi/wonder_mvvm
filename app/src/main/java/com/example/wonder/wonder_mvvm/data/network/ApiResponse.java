package com.example.wonder.wonder_mvvm.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResponse<T> {
    @SerializedName("reqUid")
    @Expose
    private String reqId;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("currentDateTime")
    @Expose
    private String currentDateTime;
    @SerializedName("statusMsg")
    @Expose
    private String statusMsg;
    @SerializedName("data")
    @Expose
    private T data;

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(String currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
