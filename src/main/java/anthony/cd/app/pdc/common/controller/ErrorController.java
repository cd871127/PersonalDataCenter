package anthony.cd.app.pdc.common.controller;

import anthony.cd.app.pdc.common.util.SystemConst;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/error")
public class ErrorController extends AbstractController {

    @RequestMapping("{error}")
    Map<String, String> error(@PathVariable SystemConst.RequestResult error) {
        return requestResultToMap(error);
    }
}
