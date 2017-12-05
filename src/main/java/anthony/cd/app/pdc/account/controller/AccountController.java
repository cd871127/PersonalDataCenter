package anthony.cd.app.pdc.account.controller;

import anthony.cd.app.pdc.account.action.AccountAction;
import anthony.cd.app.pdc.common.util.ServerResponse;
import anthony.cd.app.pdc.common.util.SystemConst;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Resource
    private AccountAction accountAction;

    @RequestMapping(value = "{userName}", method = RequestMethod.GET)
    public ServerResponse getAccountList(@PathVariable String userName) {

        return new ServerResponse(SystemConst.RequestResult.SUCCESS);
    }


}
