package co.fy.core.server.users.impl;

import co.fy.core.server.users.api.UsersServiceApi;
import co.fy.core.server.users.dao.UsersMapper;
import co.fy.core.server.users.model.Users;
import co.fy.core.server.utils.DateFormateUtil;
import co.fy.core.server.utils.GetRandomNumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    @Override
    public Boolean creatUser(Users user) {
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        user.setUserId(Long.valueOf(GetRandomNumberUtil.getIntegerRandom(15)));
        user.setPassword(encode.encode(user.getPassword()));
        user.setAddTime(DateFormateUtil.format(new Date()));
        if(usersMapper.insertSelective(user)>0){
            return true;
        }else{
            return  false;
        }
    }

    @Override
    public Boolean updateUser(Users users) {
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        if(!"".equals(users.getPassword())&&users.getPassword()!=null) users.setPassword(encode.encode(users.getPassword()));
        try {
            usersMapper.updateByPrimaryKeySelective(users);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("====================================================>/user/update"+e);
            return  false;
        }
    }

    /**
     *  添加用户的时候验证唯一性 如过用户名已存在 返回false
     * @param username
     * @return
     */
    @Override
    public Boolean validateUniqueByUserName(String username) {
        if(usersMapper.selectByPrimaryUsername(username)>0){
            return  false;
        }
        return  true;
    }

    @Override
    public Users getUser(Long userId) {
        return usersMapper.selectByPrimaryKey(userId);
    }
}
