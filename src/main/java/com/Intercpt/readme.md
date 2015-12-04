#倒腾下mybatis拦截器的实现
在`Main.TestTargetProxy`中是用JDK动态代理实现的一个拦截器   
####优化一下
拦截器明显的问题是拦截逻辑被写死在了代理对象中（`invoke`方法中），试着把拦截逻辑抽出来封装到一个类中，客户端在调用`TargetProxy.bind()`时将拦截逻辑一起当成参数传入，为了能够判断当前方法需不需要被拦截，将方法参数也传递给拦截器类（不需要的话可以不传）。改进后的客户端代码为`Main.TestInterceptor`   
####再优化下
迪米特原则说我们要让一个类对另一个类知道的越少越好，这样会减少类之间的耦合强度，针对我们的代码就是A类只让B类了解总要比A类让B,C,D类了解好。   
TargetProxy类中需要了解的类为：   
1. Object target 不需要了解，因为在TargetProxy中，target都被作为参数传给了别的类使用，自己不需要了解它。    
2. Interceptor interceptor 需要了解，需要调用其intercept方法。    
3. 同样，Proxy需要了解。    
4. Method method 参数需要了解，需要调用其invoke方法。     
同样，如果interceptor接口中需要使用intercept方法传过去Method类，那么也需要了解它。那么既然Interceptor都需要使用Method，还不如将Method的执行也放到Interceptor中，不再让TargetProxy类对其了解。Method的执行需要target对象，所以也需要将target对象给Interceptor。将Method，target和args封装到一个对象Invocation中，将Invocation传给Interceptor。      
**优化过的拦截器为NInterceptor。**客户端代码为`Main.TestNIterceptor`     
感觉还不错了，但是用了后就会发现上面的拦截器会拦截目标对象的多有方法，这太恶心了，性能就这样拜拜了～～～。因此再继续加上拦截目标对象的指定方法，Annotatioon实现。
####Annotation拦截目标对象的指定方法（拦截器上加注解）
这个时候客户端实现为`Main.TestAInterceptor`      


[cankao](http://www.tuicool.com/articles/RbyUfu)
[yixia](http://haohaoxuexi.iteye.com/blog/1851081)








