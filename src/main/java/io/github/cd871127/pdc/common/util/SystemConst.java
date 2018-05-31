package io.github.cd871127.pdc.common.util;

import java.nio.charset.Charset;

public class SystemConst {
    private SystemConst() {

    }

    public final static Charset CHARSET = Charset.forName("Utf-8");

    public final static String FILE_PATH ="D:/tmpfile";

    public enum RequestResult {

        SUCCESS("000", "操作成功"), NO_TOKEN("001", "用户未登录"), USER_EXISTED("002", "用户名已被注册"), ADD_USER_ERROR("003", "插入用户失败"),
        INVALID_TOKEN("004", "无效的token"),LOGIN_FAILED("005","用户不存在或密码错误"),OPERATION_FAILED("006","操作失败"),
        FILE_TOO_LARGE("007","文件大于10MB"),EMPTY_FILE_NAME("008","文件名为空"),UPLOAD_FAILED("009","文件上传失败");

        private String code;
        private String msg;

        public String getCode() {
            return code;
        }

        public void setValue(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        RequestResult(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }


    }
}
