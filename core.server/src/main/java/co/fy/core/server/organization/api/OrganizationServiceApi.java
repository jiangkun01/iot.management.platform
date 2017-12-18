package co.fy.core.server.organization.api;

import co.fy.core.server.organization.model.Organization;

import java.util.List;

/**
 * CREATED IN  2017-12-18 下午5:12
 *
 * @AUTHOR: 姜坤
 **/
public interface OrganizationServiceApi {
    List<Organization> getList();
}
