package co.fy.logs.server.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CREATED IN  2017-11-25 下午4:55
 *
 * @AUTHOR: 姜坤
 **/
@RestController
@RequestMapping(path = "/api/logs")
public class CountController {

    @RequestMapping(path = "/account")
    public String getCount(){

        return "1";
    }
}
