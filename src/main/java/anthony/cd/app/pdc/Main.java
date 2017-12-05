package anthony.cd.app.pdc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@MapperScan("anthony.cd.app.pdc.*.mapper")
@ServletComponentScan("anthony.cd.app.pdc.common.filter")
public class Main {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        SpringApplication.run(Main.class, args);
    }
}


