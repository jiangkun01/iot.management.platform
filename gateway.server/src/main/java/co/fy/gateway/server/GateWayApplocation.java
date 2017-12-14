package co.fy.gateway.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

/**
 * CREATED IN  2017-11-24 下午9:25
 *
 * @AUTHOR: 姜坤
 **/
@EnableZuulProxy
@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableResourceServer
@EnableAuthorizationServer
@EnableScheduling
@ComponentScan
public class GateWayApplocation {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplocation.class, args);
    }
}
