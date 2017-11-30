package anthony.cd.app.pdc.user.mapper;

import anthony.cd.app.pdc.user.dto.UserInfoDTO;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserMapper {

    UserInfoDTO getUserInfo(Map<String,String> paraMap);

    boolean addUserInfo(UserInfoDTO userInfoDTO);

    boolean isUserExist(String userName);

}
