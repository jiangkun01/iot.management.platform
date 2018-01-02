package co.fy.core.server.users.web;

import co.fy.core.server.custom.annotation.PageAnnotated;
import co.fy.core.server.users.api.UsersServiceApi;
import co.fy.core.server.users.model.Users;
import co.fy.core.server.utils.Result;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * CREATED IN  2017-12-03 下午7:04
 *
 * @AUTHOR: 姜坤
 **/
@RestController
@RequestMapping(path = "/user")
public class UsersController {
    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UsersServiceApi usersServiceApi;
    @PageAnnotated
    @GetMapping(path = "/list")
    public Result<List> list(HttpServletRequest request, HttpServletResponse response, String name, String role){
        // role 是role 的id 根据前台的select option 不能为空 设定为1 转义为空
        if("1".equals(role))role="";
        return Result.ok(new PageInfo(usersServiceApi.getUsersList(name,role)));

    }
    @GetMapping(path = "/delete")
    public Result<String> delete(String userId){
        if(usersServiceApi.deleteUser(userId)){
            return Result.ok("ok");
        }
        return Result.fail("删除失败请联系管理员","500");
    }

    @PostMapping(path = "/deleteMu")
    public Result<String> deleteMu(@RequestBody DeMuVo deMuVo){
        try {
            usersServiceApi.deleteUserMut(deMuVo.getUserIds());
            return Result.ok("ok");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("====================================>/user/deleteMu"+e);
            return Result.fail("删除失败请联系管理员","500");
        }
    }
    @PostMapping(path = "/create")
    public Result<String> create(@RequestBody CrUserVo user){
        if(usersServiceApi.creatUser(user.getUser())){
            return Result.ok("ok");
        }
        return Result.fail("添加失败请联系管理员","500");
    }
    @PostMapping(path = "/update")
    public Result<String> update(@RequestBody CrUserVo user){
        if(usersServiceApi.updateUser(user.getUser())){
            return Result.ok("ok");
        }
        return Result.fail("修改失败请联系管理员","500");
    }
}
