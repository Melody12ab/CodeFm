package com.melody.main;

/**
 * Created by xiaobai on 15-11-11.
 */

import com.melody.Dao.IUser;
import com.melody.Vo.User;
import com.melody.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
public class SpringMemTest {

    private static ApplicationContext ctx;

    static
    {
        ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
    }

    public static void main(String[] args)
    {
        UserService userService= (UserService) ctx.getBean("userService");
        //测试id=1的用户查询，根据数据库中的情况，可以改成你自己的.
        System.out.println("得到用户id=4的用户信息");
        User user = userService.getUserById(4);
        System.out.println(user.getUname());
    }

}
