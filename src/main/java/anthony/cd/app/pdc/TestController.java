package anthony.cd.app.pdc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;

@RestController
@RequestMapping("/")
public class TestController {

    @RequestMapping("test")
    public String testController(ServletRequest request) {
        System.out.println(124367);

        return "1231";
    }

    @RequestMapping("test1")
    public String testController2() {
        System.out.println(1243678);

        return "1234";
    }

}
