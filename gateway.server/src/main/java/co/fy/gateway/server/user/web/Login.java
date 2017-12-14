package co.fy.gateway.server.user.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CREATED IN  2017-11-27 上午11:45
 *
 * @AUTHOR: 姜坤
 **/
@RestController
public class Login {
    @GetMapping(path = "/test")
    public String r(){
        return "0";
    }
    @GetMapping(path = "/test1")
    public String r1(){
        return "1";
    }
}
