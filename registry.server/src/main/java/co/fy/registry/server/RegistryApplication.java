package co.fy.registry.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * CREATED IN  2017-11-24 下午6:30
 *
 * @AUTHOR: 姜坤
 **/
@EnableEurekaServer     // Eureka Server 标识
@SpringBootApplication  // Spring Boot 应用标识
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class RegistryApplication {
    public  static void  main(String[] args){
        SpringApplication.run(RegistryApplication.class,args);
    }
}
