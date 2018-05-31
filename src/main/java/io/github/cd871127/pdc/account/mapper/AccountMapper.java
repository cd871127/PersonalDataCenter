package io.github.cd871127.pdc.account.mapper;

import io.github.cd871127.pdc.account.dto.AccountDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountMapper {
    List<AccountDTO> getAccountList(String userName);
}
