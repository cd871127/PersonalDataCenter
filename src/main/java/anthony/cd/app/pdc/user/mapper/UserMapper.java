package anthony.cd.app.pdc.user.mapper;

import anthony.cd.app.pdc.user.dto.UserInfoDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    UserInfoDTO getUserInfoByUserName(String userName);

    int addUserInfo(UserInfoDTO userInfoDTO);

}
