package io.github.cd871127.pdc.account.action;


import io.github.cd871127.pdc.account.dto.AccountDTO;
import io.github.cd871127.pdc.account.mapper.AccountMapper;
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
