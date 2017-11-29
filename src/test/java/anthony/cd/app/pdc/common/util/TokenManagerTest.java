package anthony.cd.app.pdc.common.util;

import anthony.cd.app.pdc.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = Main.class) // 指定我们SpringBoot工程的Application启动类
//@WebAppConfiguration
public class TokenManagerTest {
    @Test
    public void generateTokenByTimeAndUserName() throws Exception {
        TokenManager tokenManager=new TokenManager();
        String token=tokenManager.generateTokenByTimeAndUserName("cd871127");
        System.out.println(token);
    }

}