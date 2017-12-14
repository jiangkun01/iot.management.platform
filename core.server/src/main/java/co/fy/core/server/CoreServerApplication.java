package co.fy.core.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * CREATED IN  2017-12-03 下午6:09
 *
 * @AUTHOR: 姜坤
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration
@ServletComponentScan
@EnableSwagger2
@EnableScheduling
@ComponentScan
@MapperScan("server")
public class CoreServerApplication {
    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(CoreServerApplication.class,args);
    }
}
