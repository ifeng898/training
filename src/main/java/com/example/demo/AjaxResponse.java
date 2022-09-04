package com.example.demo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "通用请求响应数据结构类")
public class AjaxResponse {
    @ApiModelProperty(value = "请求是否成功")
    private boolean isok;
    @ApiModelProperty(value = "代码返回 expamle 200:sucess 400 500")
    private int code;//200 400 500
    @ApiModelProperty(value = "请求状态信息")
    private String message;
    @ApiModelProperty(value = "返回的数据类")
    private Object data;

    public boolean isIsok() {
        return isok;
    }

    public void setIsok(boolean isok) {
        this.isok = isok;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public AjaxResponse() {
    }

    public static AjaxResponse sucess(Object data, String message) {
        AjaxResponse mAjax = new AjaxResponse();
        mAjax.setIsok(true);
        mAjax.setData(data);
        mAjax.setMessage(message);
        mAjax.setCode(200);
        return mAjax;
    }
    public static AjaxResponse sucess(Object data) {
        AjaxResponse mAjax = new AjaxResponse();
        mAjax.setIsok(true);
        mAjax.setData(data);
        mAjax.setMessage("query sucess!");
        mAjax.setCode(200);
        return mAjax;
    }
}
