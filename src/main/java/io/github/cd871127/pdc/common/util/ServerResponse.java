package io.github.cd871127.pdc.common.util;

public class ServerResponse<T> {
    private String code;
    private String msg;
    private String token;
    private T data;

    public ServerResponse() {
    }

    public ServerResponse(SystemConst.RequestResult requestResult) {
        this();
        setResult(requestResult);
    }

    public void setResult(SystemConst.RequestResult requestResult) {
        this.code = requestResult.getCode();
        this.msg = requestResult.getMsg();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
