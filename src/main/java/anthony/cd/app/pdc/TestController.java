package anthony.cd.app.pdc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {

    @RequestMapping("test")
    public String testController() {
        System.out.println(123);
        return "123";
    }

}
