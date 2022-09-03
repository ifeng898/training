package com.example.demo;

public class AjaxResponse {
    private boolean isok;
    private int code;//200 400 500
    private String message;
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
