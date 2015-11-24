package com.MemAndMybatis.main;

/**
 * Created by xiaobai on 15-11-11.
 */
import java.io.Reader;

import com.MemAndMybatis.Dao.IUser;
import com.MemAndMybatis.Vo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisTest {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;
    static {
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            //使用接口的方法唯一改变感觉就是如下使用了IUser.class
            //注意要在映射文件中注册接口
            IUser userOperation = session.getMapper(IUser.class);
            User user = userOperation.selectUserByID(4);
            System.out.println(user.getUname());
            System.out.println(user.getUpass());
        } finally {
            session.close();
        }
    }
}
