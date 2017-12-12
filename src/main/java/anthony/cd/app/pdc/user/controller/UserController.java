package anthony.cd.app.pdc.user.controller;

import anthony.cd.app.pdc.common.controller.AbstractController;
import anthony.cd.app.pdc.common.util.ServerResponse;
import anthony.cd.app.pdc.common.util.SystemConst;
import anthony.cd.app.pdc.common.util.TokenManager;
import anthony.cd.app.pdc.user.action.UserAction;
import anthony.cd.app.pdc.user.dto.UserInfoDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static anthony.cd.app.pdc.common.util.SystemConst.RequestResult.LOGIN_FAILED;
import static anthony.cd.app.pdc.common.util.SystemConst.RequestResult.SUCCESS;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/users")
public class UserController extends AbstractController {

    @Resource
    private TokenManager tokenManager;

    @Resource
    private UserAction userAction;

    @RequestMapping(value = "token/{userName}", method = GET)
    @CrossOrigin(origins = "http://localhost:3000", methods = {GET})
    ServerResponse login(@PathVariable String userName, @RequestHeader("password") String passWord, @RequestHeader("keyid") String keyId) {

        UserInfoDTO userInfoDTO = userAction.userLogin(userName, passWord, keyId);
        ServerResponse<UserInfoDTO> serverResponse = new ServerResponse<>();
        if (userInfoDTO == null) {
            serverResponse.setResult(LOGIN_FAILED);
        } else {
            //设置TOKEN
            serverResponse.setToken(tokenManager.generateTokenByTimeAndUserName(userName));
            serverResponse.setResult(SUCCESS);
            serverResponse.setData(userInfoDTO);
        }
        return serverResponse;
    }


    @RequestMapping(value = "registry", method = POST)
    @CrossOrigin(origins = "http://localhost:3000", methods = {POST})
    ServerResponse addUser(@RequestBody UserInfoDTO userInfoDTO, @RequestHeader String keyId) {
        SystemConst.RequestResult requestResult = userAction.userRegister(userInfoDTO, keyId);
        ServerResponse serverResponse = new ServerResponse(requestResult);
        if (SUCCESS.equals(requestResult)) {
            //生成TOKEN
            String token = tokenManager.generateTokenByTimeAndUserName(userInfoDTO.getUserName());
            serverResponse.setToken(token);
        }
        return serverResponse;
    }


    @RequestMapping(value = "info/{userName}", method = DELETE)
    ServerResponse deleteUserInfo(@PathVariable String userName) {
        return new ServerResponse(userAction.deleteUserInfo(userName));
    }

    @RequestMapping(value = "info/{userName}", method = PUT)
    ServerResponse updateUserInfo(@RequestBody UserInfoDTO userInfoDTO) {
        return new ServerResponse(userAction.updateUserInfo(userInfoDTO));
    }


}
