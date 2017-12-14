package co.fy.config.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * CREATED IN  2017-11-24 下午5:29
 *
 * @AUTHOR: 姜坤
 **/
@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigServer
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ConfigCoreServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCoreServerApplication.class, args);
    }
}