package co.fy.ribbon.server.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
/**
 * CREATED IN  2017-11-21 下午2:32
 *
 * @AUTHOR: 姜坤
 **/
@RestController
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private RestTemplate restTemplate; // HTTP 访问操作类
    @RequestMapping("/login121")
    @HystrixCommand(fallbackMethod = "fallback")
    public String customer() {
        String providerMsg = restTemplate.getForEntity("http://login-server/hello",
                String.class).getBody();

        return "Hello,Customer! msg from provider : <br/><br/> " + providerMsg;
    }

    public String fallback() {

        return "熔断器";
    }
}