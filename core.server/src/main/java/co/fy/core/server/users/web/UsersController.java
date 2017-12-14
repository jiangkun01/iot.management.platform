package co.fy.core.server.users.web;

import co.fy.core.server.custom.annotation.PageAnnotated;
import co.fy.core.server.users.api.UsersServiceApi;
import co.fy.core.server.utils.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * CREATED IN  2017-12-03 下午7:04
 *
 * @AUTHOR: 姜坤
 **/
@RestController
@RequestMapping(path = "/users")
public class UsersController {
    @Autowired
    private UsersServiceApi usersServiceApi;
    @PageAnnotated
    @GetMapping(path = "/list")
    public Result<List> list(HttpServletRequest request, HttpServletResponse response){
        request.getHeader("username");
        System.out.print("+++++++++++++++++++++++++++++++++==============="+request.getHeader("username"));
        return Result.ok(new PageInfo(usersServiceApi.getUsersList()));
    }
}
