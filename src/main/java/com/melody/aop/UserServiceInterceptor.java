package com.melody.aop;

import com.melody.Vo.User;
import com.melody.mem.MemcachedCache;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by xiaobai on 15-11-11.
 */
@Aspect
public class UserServiceInterceptor {
    public static final Logger log=Logger.getLogger(UserServiceInterceptor.class);

    @Autowired
    private MemcachedCache memcachedCache;

    @Pointcut("execution(* com.melody.service.UserService.*(..))")
    public void aPointcut(){

    }

    @Around("aPointcut()&&args(id)")
    public User doFindUserByIdAround(ProceedingJoinPoint call,int id){
        User user=null;
        if(memcachedCache.getCache().containsKey("selectUserByID_"+id)){
            user= (User) memcachedCache.get("selectUserByID_"+id);
            log.debug("从缓存中取出"+id);
        }else {
            try{
                user= (User) call.proceed();
                if(user!=null){
                    memcachedCache.put("selectUserByID_"+id,user);
                    log.debug("缓存"+user);
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return user;
    }
}
