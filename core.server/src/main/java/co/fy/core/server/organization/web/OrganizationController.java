package co.fy.core.server.organization.web;

import co.fy.core.server.custom.annotation.PageAnnotated;
import co.fy.core.server.organization.api.OrganizationServiceApi;
import co.fy.core.server.organization.model.Organization;
import co.fy.core.server.utils.DateFormateUtil;
import co.fy.core.server.utils.Result;
import com.github.pagehelper.PageInfo;
import com.netflix.client.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public Result<List> list(String name, String createTime, String endTime){
        PageInfo pageInfo=new PageInfo(organizationServiceApi.getList(name, createTime, endTime));
        List<Organization> list=pageInfo.getList();
        List<OrganizationVo> listVo =list.stream().map(o -> {
            OrganizationVo vo=new OrganizationVo();
            vo.setName(o.getName());
            vo.setId(o.getId());
            vo.setAddtime(DateFormateUtil.format(o.getAddtime()));
            return vo;
        }).collect(Collectors.toList());
        pageInfo.setList(listVo);
        return Result.ok(pageInfo);
    }

    @GetMapping(path = "/delete")
    public Result<String> delete(String id){
        if(organizationServiceApi.removeOrganization(id)){
            return Result.ok("ok");
        }
        return Result.fail("删除失败请联系管理员","500");
    }

    @PostMapping(path = "/deleteMu")
    public Result<String> deleteMu(@RequestBody  DeMuVo deMuVo){
        organizationServiceApi.rmMu(deMuVo.getIds());
        return Result.ok("ok");
    }

    @PostMapping(path = "/create")
    public Result<String> create(@RequestBody  Organization organization){
        organizationServiceApi.create(organization);
        return Result.ok("ok");
    }
    @PostMapping(path = "/update")
    public Result<String> update(@RequestBody  Organization organization){
        organizationServiceApi.update(organization);
        return Result.ok("ok");
    }
}
