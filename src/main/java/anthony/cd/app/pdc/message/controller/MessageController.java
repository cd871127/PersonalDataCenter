package anthony.cd.app.pdc.message.controller;


import anthony.cd.app.pdc.common.util.ServerResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    //添加消息到缓存
    @RequestMapping(value = "{userNameFrom}/{userNameTo}", method = RequestMethod.POST)
    public ServerResponse pushMessage(@PathVariable("userNameFrom") String fromUser, @PathVariable("userNameTo") String toUser) {
        System.out.println(fromUser);
        System.out.println(toUser);
        return null;
    }

    //从缓存接受消息
    @RequestMapping(value = "{userNameFrom}/{userNameTo}", method = RequestMethod.GET)
    public ServerResponse pullMessage(@PathVariable("userNameFrom") String fromUser, @PathVariable("userNameTo") String toUser) {
        System.out.println(fromUser);
        System.out.println(toUser);
        return null;
    }
}
