package co.fy.core.server.users.api;

import co.fy.core.server.users.model.Users;

import java.util.List;

/**
 * CREATED IN  2017-12-03 下午7:14
 *
 * @AUTHOR: 姜坤
 **/
public interface UsersServiceApi {
    List<Users> getUsersList(String name, String roleId);
    Boolean deleteUser(String id);
    void deleteUserMut(String[] ids) throws Exception;
    Boolean creatUser(Users user);
    Boolean updateUser(Users users);
}
