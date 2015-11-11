package com.melody.service;

import com.melody.Dao.IUser;
import com.melody.Vo.User;
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
