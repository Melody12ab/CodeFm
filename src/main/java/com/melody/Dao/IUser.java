package com.melody.Dao;

import com.melody.Vo.User;

/**
 * Created by xiaobai on 15-11-11.
 */
public interface IUser {
    public User selectUserByID(int id);
}
