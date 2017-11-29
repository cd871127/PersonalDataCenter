package anthony.cd.app.pdc.common.controller;

import anthony.cd.app.pdc.common.util.SystemConst;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractController {

    @Deprecated
    protected Map<String, String> requestResultToMap(SystemConst.RequestResult requestResult) {
        Map<String, String> map = new HashMap<>();
        map.put("errorName", requestResult.name());
        map.put("errorCode", requestResult.getCode());
        map.put("errorMsg", requestResult.getMsg());
        return map;
    }

}
