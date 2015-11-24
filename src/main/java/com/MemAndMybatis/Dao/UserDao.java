package com.MemAndMybatis.Dao;

import com.MemAndMybatis.Vo.User;
import com.MemAndMybatis.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaobai on 15-11-10.
 */
public class UserDao{
    Connection conn=null;
    PreparedStatement pstm=null;
    ResultSet rs=null;

    public User getUserById(int uid){
        User user=null;
        String sql="select * from user where uid=?";
        try {
            conn=JDBCUtils.getConnection();
            pstm=conn.prepareStatement(sql);
            pstm.setInt(1,uid);
            rs=pstm.executeQuery();
            while(rs.next()){
                user=new User();
                user.setUid(rs.getInt("uid"));
                user.setUname(rs.getString("uname"));
                user.setUpass(rs.getString("upass"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return user;
    }
    public List getUserList() {
        List userList = new ArrayList();
        String sql = "select * from user";

        try {
            conn=JDBCUtils.getConnection();
            pstm=conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next())
            {
                User user = new User();
                user.setUid(rs.getInt("uid"));
                user.setUname(rs.getString("uname"));
                user.setUpass(rs.getString("upass"));
                userList.add(user);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
