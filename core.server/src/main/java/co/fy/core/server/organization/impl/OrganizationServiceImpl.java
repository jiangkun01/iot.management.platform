package co.fy.core.server.organization.impl;

import co.fy.core.server.organization.api.OrganizationServiceApi;
import co.fy.core.server.organization.dao.OrganizationMapper;
import co.fy.core.server.organization.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Organization> getList() {
        return organizationMapper.selectOrganizations();
    }
}
