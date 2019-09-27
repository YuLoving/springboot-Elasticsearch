package com.example.ccq.springelasticsearch.config.shiro;


import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfiguration {

    @Autowired
    private UserRealm userRealm;


    /**
     * 定义安全管理器securityManager,注入自定义的realm
     */
    @Bean("securityManager")
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     *  @Qualifier("XXX") Spring的Bean注入配置注解，该注解指定注入的Bean的名称，
     *  Spring框架使用byName方式寻找合格的bean，这样就消除了byType方式产生的歧义。
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager manager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        System.out.println(factoryBean);
        factoryBean.setSecurityManager(manager);
        factoryBean.setLoginUrl("/login"); //提供登录到url
        factoryBean.setSuccessUrl("/index"); //提供登陆成功的url
        factoryBean.setUnauthorizedUrl("/unauthorized");

        /**
         * 可以看DefaultFilter,这是一个枚举类，定义了很多的拦截器authc,anon等分别有对应的拦截器
         */
        //配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap  = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/index", "authc"); //代表着前面的url路径，用后面指定的拦截器进行拦截
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/loginUser", "anon");
        filterChainDefinitionMap.put("/excel/**", "anon");
        filterChainDefinitionMap.put("/flowable/**", "anon");
        filterChainDefinitionMap.put("/admin", "roles[admin]"); //admin的url，要用角色是admin的才可以登录,对应的拦截器是RolesAuthorizationFilter
        filterChainDefinitionMap.put("/update", "perms[update]"); //拥有update权限的用户才有资格去访问
        //filterChainDefinitionMap.put("/druid/**", "anon"); //所有的druid请求，不需要拦截，anon对应的拦截器不会进行拦截
        filterChainDefinitionMap.put("/**", "user"); //所有的路径都拦截，被UserFilter拦截，这里会判断用户有没有登陆
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);//设置一个拦截器链
        return factoryBean;
    }

}
