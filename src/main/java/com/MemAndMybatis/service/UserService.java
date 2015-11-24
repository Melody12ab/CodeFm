package com.MemAndMybatis.service;

import com.MemAndMybatis.Dao.IUser;
import com.MemAndMybatis.Vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiaobai on 15-11-11.
 */
@Service
public class UserService {

    @Autowired
    private IUser iuser;

    public User getUserById(int id){
        return iuser.selectUserByID(id);
    }

}
