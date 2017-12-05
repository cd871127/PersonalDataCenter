package anthony.cd.app.pdc.account.mapper;

import anthony.cd.app.pdc.account.dto.AccountDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountMapper {
    List<AccountDTO> getAccountList(String userName);
}
