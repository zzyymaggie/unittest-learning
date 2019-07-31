package com.example.dbunit.entity;

public class Response {

    private static final String OK = "ok";
    private static final String ERROR = "error";

    private Meta meta;
    private Object data;

    public Response success() {
        this.meta = new Meta(0, OK);
        return this;
    }

    public Response success(Object data) {
        this.meta = new Meta(0, OK);
        this.data = data;
        return this;
    }

    public Response success(Object data, int retCode) {
        this.data = data;
        this.meta = new Meta(retCode, OK);
        return this;
    }

    public Response success(Object data, int retCode, String msg) {
        this.data = data;
        this.meta = new Meta(retCode, msg);
        return this;
    }

    public Response failure(int errCode, String message) {
        this.meta = new Meta(errCode, message);
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public Object getData() {
        return data;
    }

    public class Meta {
        private int errCode;
        private String msg;

        public Meta(int errCode, String msg) {
            this.errCode = errCode;
            this.msg = msg;
        }

        public int getErrCode() {
            return this.errCode;
        }

        public String getMsg() {
            return this.msg;
        }
    }
}
