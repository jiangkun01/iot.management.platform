package co.fy.core.server.role.api;

import co.fy.core.server.role.model.Role;

import java.util.List;

/**
 * CREATED IN  2017-12-12 下午2:27
 *
 * @AUTHOR: 姜坤
 **/
public interface RoleServiceApi {
    Boolean inspect(String userName, String api);
    List<Role> list();
}
