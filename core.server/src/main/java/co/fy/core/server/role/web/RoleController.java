package co.fy.core.server.role.web;

import co.fy.core.server.custom.annotation.PageAnnotated;
import co.fy.core.server.role.api.RoleServiceApi;
import co.fy.core.server.utils.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/role")
public class RoleController {
    @Autowired
    private RoleServiceApi roleServiceApi;
    @GetMapping(path = "/list")
    @PageAnnotated
    public Result<List> list(){
        return Result.ok(new PageInfo(roleServiceApi.list()));
    }
}
