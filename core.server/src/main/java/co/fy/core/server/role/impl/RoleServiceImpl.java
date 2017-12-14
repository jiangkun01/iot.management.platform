package co.fy.core.server.role.impl;

import co.fy.core.server.role.api.RoleServiceApi;
import co.fy.core.server.role.dao.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CREATED IN  2017-12-12 下午2:30
 *
 * @AUTHOR: 姜坤
 **/
@Service
public class RoleServiceImpl implements RoleServiceApi {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public Boolean inspect(String userName, String api) {
        if(roleMapper.selectByUsernameAndPath(userName,api)>0){
            return true;
        };
        return false;
    }
}