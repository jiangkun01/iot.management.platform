package co.fy.core.server.users.impl;

import co.fy.core.server.users.api.UsersServiceApi;
import co.fy.core.server.users.dao.UsersMapper;
import co.fy.core.server.users.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * CREATED IN  2017-12-03 下午7:15
 *
 * @AUTHOR: 姜坤
 **/
@Service
public class UsersServiceImpl implements UsersServiceApi{
    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public List<Users> getUsersList(String name, String roleId) {
        return usersMapper.selectList(name,roleId);
    }

    @Override
    public Boolean deleteUser(String userId) {
        try {
            usersMapper.deleteByPrimaryKey(Long.valueOf(userId));
            return true;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("====================================================>/user/deleteOneUser"+e);
            return false;
        }
    }

    @Override
    @Transactional
    public void deleteUserMut(String[] userIds) throws Exception {
        for (String userId:userIds){
            usersMapper.deleteByPrimaryKey(Long.valueOf(userId));
        }
    }
}
