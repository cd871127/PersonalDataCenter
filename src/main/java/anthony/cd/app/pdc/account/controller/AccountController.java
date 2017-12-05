package anthony.cd.app.pdc.account.controller;

import anthony.cd.app.pdc.account.action.AccountAction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Resource
    private AccountAction accountAction;

}
