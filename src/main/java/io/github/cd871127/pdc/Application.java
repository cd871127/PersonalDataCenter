package io.github.cd871127.pdc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//        (exclude = {
//        DataSourceAutoConfiguration.class,
//        QuartzAutoConfiguration.class
//})
//@ComponentScan("io.github.cd871127.*")
@MapperScan("io.github.cd871127.pdc.*.mapper")
@ServletComponentScan("io.github.cd871127.pdc.common.filter")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}


