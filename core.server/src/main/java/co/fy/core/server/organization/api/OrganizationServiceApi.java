package co.fy.core.server.organization.api;

import co.fy.core.server.organization.model.Organization;

import java.util.List;

/**
 * CREATED IN  2017-12-18 下午5:12
 *
 * @AUTHOR: 姜坤
 **/
public interface OrganizationServiceApi {
    List<Organization> getList(String name, String createTime, String endTime);
    Boolean removeOrganization(String id);
    void rmMu(String[] ids);
    void create(Organization organization);
    void update(Organization organization);
}
