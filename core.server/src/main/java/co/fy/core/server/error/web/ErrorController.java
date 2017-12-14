package co.fy.core.server.error.web;

import co.fy.core.server.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CREATED IN  2017-12-05 上午10:35
 *
 * @AUTHOR: 姜坤
 **/
@RestController
@RequestMapping(path = "/error")
public class ErrorController {
    @GetMapping(path = "/401")
    public Result<String> error401(){
        return Result.fail("没有权限请求借口", "401");
    }
    @GetMapping(path = "/402")
    public Result<String> error402(){
        return Result.fail("token不正确", "402");
    }
}
