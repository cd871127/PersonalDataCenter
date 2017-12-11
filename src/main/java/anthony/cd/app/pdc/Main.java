package anthony.cd.app.pdc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("anthony.cd.app.pdc.*.mapper")
@ServletComponentScan("anthony.cd.app.pdc.common.filter")
public class Main {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Main.class);
        springApplication.addListeners(new ApplicationStartup());
        SpringApplication.run(Main.class, args);
    }
}


