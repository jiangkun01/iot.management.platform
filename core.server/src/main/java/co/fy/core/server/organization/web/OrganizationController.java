package co.fy.core.server.organization.web;

import co.fy.core.server.custom.annotation.PageAnnotated;
import co.fy.core.server.organization.api.OrganizationServiceApi;
import co.fy.core.server.utils.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * CREATED IN  2017-12-18 下午5:14
 *
 * @AUTHOR: 姜坤
 **/
@RestController
@RequestMapping(path = "/organization")
public class OrganizationController {
    @Autowired
    private OrganizationServiceApi organizationServiceApi;
    @PageAnnotated
    @GetMapping(path = "/list")
    public Result<List> list(){
        return Result.ok(new PageInfo(organizationServiceApi.getList()));
    }
}
