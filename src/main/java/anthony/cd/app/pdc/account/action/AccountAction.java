package anthony.cd.app.pdc.account.action;

import anthony.cd.app.pdc.account.dto.AccountDTO;
import anthony.cd.app.pdc.account.mapper.AccountMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class AccountAction {
    @Resource
    private AccountMapper accountMapper;

    List<AccountDTO> getAccountList()
    {
        return null;
    }

    AccountDTO getSingleAccountDTO()
    {
        return null;
    }
}
