package anthony.cd.app.pdc.common.util;

import java.nio.charset.Charset;

public class SystemConst {
    private SystemConst() {

    }

    public final static Charset CHARSET = Charset.forName("Utf-8");

    public enum RequestResult {

        SUCCESS("000","操作成功"),NO_TOKEN("001", "用户未登录"),USER_EXISTED("002","用户名已被注册"),ADD_USER_ERROR("003","插入用户失败");

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
