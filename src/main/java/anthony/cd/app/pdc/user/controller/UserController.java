package anthony.cd.app.pdc.user.controller;

import anthony.cd.app.pdc.common.controller.AbstractController;
import anthony.cd.app.pdc.common.util.ServerResponse;
import anthony.cd.app.pdc.common.util.SystemConst;
import anthony.cd.app.pdc.common.util.TokenManager;
import anthony.cd.app.pdc.common.util.encrypt.RSAEncrypt;
import anthony.cd.app.pdc.common.util.redis.KeyPairSerializer;
import anthony.cd.app.pdc.user.action.UserAction;
import anthony.cd.app.pdc.user.dto.UserInfoDTO;
import anthony.cd.app.pdc.user.mapper.UserMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/users")
public class UserController extends AbstractController {

    @Resource
    private RSAEncrypt rsaEncrypt;

    @Resource
    private UserMapper userMapper;

    @Resource
    private KeyPairSerializer keyPairSerializer;

    @Resource
    private RedisTemplate<String, byte[]> redisTemplate;
    @Resource
    private TokenManager tokenManager;

    @Resource
    private UserAction userAction;

    @RequestMapping(value = "{userName}", method = GET)
    @CrossOrigin(origins = "http://localhost:3000", methods = {GET})
    UserInfoDTO getUserInfoDTO(HttpServletRequest request) {
        String token = request.getHeader("token");
        UserInfoDTO userInfoDTO;
        if (token != null) {
            userInfoDTO = userAction.getUserInfoDTOByUserName(null);
        } else {
            userInfoDTO = userAction.userLogin();
        }
        return userInfoDTO;
    }


    @RequestMapping(value = "registry", method = POST)
    ServerResponse addUser(@RequestBody UserInfoDTO userInfoDTO) {
        SystemConst.RequestResult requestResult = userAction.userRegister(userInfoDTO);
        ServerResponse serverResponse = new ServerResponse(requestResult);
        if (SystemConst.RequestResult.SUCCESS.equals(requestResult)) {
            //生成TOKEN
            String token = tokenManager.generateTokenByTimeAndUserName(userInfoDTO.getUserName());;
            serverResponse.setToken(token);
        }
        return serverResponse;
    }

//    @RequestMapping(value = "{userName}", method = GET)
//    @CrossOrigin(origins = "http://localhost:3000", methods = {GET, POST, PUT})
//    Map getUser(@PathVariable String userName, HttpServletRequest request) {
////        System.out.println(res);
////        System.out.println(keyId);
//        String keyId = request.getHeader("keyId");
//        String res = request.getHeader("res");
//        KeyPair keyPair = keyPairSerializer.deserialize(redisTemplate.opsForValue().get(keyId));
//        Map m = new HashMap();
//        m.put("res", new String(rsaEncrypt.decode(keyPair.getPrivate(), new Base64().decode(res)), SystemConst.CHARSET));
//        return m;
////        return userMapper.getUserInfoByUserName(userName);
//    }


    @RequestMapping(value = "{userName}", method = DELETE)
    int delUser(@PathVariable String userName) {
        return 0;
    }

    @RequestMapping(value = "{userName}", method = PUT)
    int updateUser(@RequestParam UserInfoDTO userInfoDTO) {
        return 0;
    }


}
