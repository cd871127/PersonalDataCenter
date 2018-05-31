package io.github.cd871127.pdc.user.mapper;

import io.github.cd871127.pdc.user.dto.UserInfoDTO;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserMapper {

    UserInfoDTO getUserInfo(Map<String, String> paraMap);

    boolean addUserInfo(UserInfoDTO userInfoDTO);

    boolean isUserExist(String userName);

    boolean insertUserHisTbl(String userName);

    boolean deleteUserInfoByUserName(String userName);

    boolean updateUserInfo(UserInfoDTO userInfoDTO);

}
