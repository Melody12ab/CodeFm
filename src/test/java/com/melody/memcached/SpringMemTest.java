package com.melody.memcached;

import com.melody.Vo.User;
import com.melody.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class SpringMemTest {

    @Autowired
    private UserService userService;

    @Test
    public void test1(String[] args)
    {
        //测试id=1的用户查询，根据数据库中的情况，可以改成你自己的.
        System.out.println("得到用户id=4的用户信息");
        User user = userService.getUserById(4);
        System.out.println(user.getUname());
    }

}
