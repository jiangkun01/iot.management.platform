package co.fy.core.server.users.impl;

import co.fy.core.server.users.api.UsersServiceApi;
import co.fy.core.server.users.dao.UsersMapper;
import co.fy.core.server.users.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CREATED IN  2017-12-03 下午7:15
 *
 * @AUTHOR: 姜坤
 **/
@Service
public class UsersServiceImpl implements UsersServiceApi{
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public List<Users> getUsersList() {
        return usersMapper.selectList();
    }
}
