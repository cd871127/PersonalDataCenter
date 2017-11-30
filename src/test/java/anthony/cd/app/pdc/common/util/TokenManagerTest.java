package anthony.cd.app.pdc.common.util;

import org.junit.Test;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = Main.class) // 指定我们SpringBoot工程的Application启动类
//@WebAppConfiguration
public class TokenManagerTest {
    @Test
    public void generateTokenByTimeAndUserName() throws Exception {
        TokenManager tokenManager = new TokenManager();
        String token = tokenManager.generateTokenByTimeAndUserName("cd871127");
        System.out.println(token);
    }

}