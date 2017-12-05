package anthony.cd.app.pdc.account.action;

import anthony.cd.app.pdc.account.mapper.AccountMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AccountAction {
    @Resource
    private AccountMapper accountMapper;
}
