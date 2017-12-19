package co.fy.core.server.organization.impl;

import co.fy.core.server.organization.api.OrganizationServiceApi;
import co.fy.core.server.organization.dao.OrganizationMapper;
import co.fy.core.server.organization.model.Organization;
import co.fy.core.server.utils.GetRandomNumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * CREATED IN  2017-12-18 下午5:13
 *
 * @AUTHOR: 姜坤
 **/
@Service
public class OrganizationServiceImpl implements OrganizationServiceApi{
    @Autowired
    private OrganizationMapper organizationMapper;
    @Override
    public List<Organization> getList(String name, String createTime, String endTime) {
        return organizationMapper.selectOrganizations(name, createTime, endTime);
    }

    @Override
    public Boolean removeOrganization(String id) {
        if(organizationMapper.deleteByPrimaryKey(id)!=0){
            return true;
        }else{
            return  false;
        }
    }
    @Override
    @Transactional
    public void rmMu(String[] ids){
        for (int i=0;i<ids.length;i++){
            organizationMapper.deleteByPrimaryKey(ids[i]);
        }
    }

    @Override
    public void create(Organization organization) {
        organization.setId(GetRandomNumberUtil.getStringIntegerRandom(19));
        organization.setAddtime(new Date());
        organizationMapper.insertSelective(organization);
    }

    @Override
    public void update(Organization organization) {
        organizationMapper.updateByPrimaryKeySelective(organization);
    }
}
