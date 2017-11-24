package anthony.cd.app.pdc.user.controller;

import anthony.cd.app.pdc.user.dto.UserInfoDTO;
import anthony.cd.app.pdc.user.mapper.UserMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    private UserMapper userMapper;



    @RequestMapping(value = "{userName}", method = POST)
    int addUser(@RequestParam UserInfoDTO userInfoDTO) {
        return userMapper.addUserInfo(userInfoDTO);
    }

    @RequestMapping(value = "{userName}", method = GET)
    UserInfoDTO getUser(@PathVariable String userName) {

        return userMapper.getUserInfoByUserName(userName);
    }

    @RequestMapping(value = "{userName}", method = DELETE)
    int delUser(@PathVariable String userName) {
        return 0;
    }

    @RequestMapping(value = "{userName}", method = PUT)
    int updateUser(@RequestParam UserInfoDTO userInfoDTO) {
        return 0;
    }


}
