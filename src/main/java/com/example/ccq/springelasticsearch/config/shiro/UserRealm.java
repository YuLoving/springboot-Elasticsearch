package com.example.ccq.springelasticsearch.config.shiro;

import com.example.ccq.springelasticsearch.pojo.Module;
import com.example.ccq.springelasticsearch.pojo.User;
import com.example.ccq.springelasticsearch.pojo.role;
import com.example.ccq.springelasticsearch.service.Userservice;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义的realm
 * AuthenticatingRealm是抽象类，用于认证授权
*/
@Component
public class UserRealm extends AuthorizingRealm {


    @Autowired
    private Userservice userservice;

    /**
     * 用户授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //因为addRoles和addStringPermissions方法需要的参数类型是Collection
            //所以先创建两个collection集合
           Collection<String> rolescollection = new HashSet<>();
           Collection<String> perscollection = new HashSet<>();
            //获取user的Role的set集合
            Set<role> roles = user.getRoles();
            //遍历集合
            for (role role: roles) {
                //将每一个role的name装进collection集合
                rolescollection.add(role.getRname());
                //获取每一个Role的permission的set集合
                Set<Module> modules = role.getModules();
                for (Module module: modules) {
                  //将每一个permission的name装进collection集合
                    perscollection.add(module.getMname());
                    System.out.println("对应的权限名字："+module.getMname());
                }
                //为用户授权
                info.addStringPermissions(perscollection);
            }
            //为用户授予角色
            info.addRoles(rolescollection);
            return info;



    }

    /**
     * 用于认证登录，认证接口实现方法，该方法的回调一般是通过subject.login(token)方法来实现的
     * AuthenticationToken 用于收集用户提交的身份（如用户名）及凭据（如密码）：
     * AuthenticationInfo是包含了用户根据username返回的数据信息，用于在匹马比较的时候进行相互比较
     *
     * shiro的核心是java servlet规范中的filter，通过配置拦截器，使用拦截器链来拦截请求，如果允许访问，则通过。
     * 通常情况下，系统的登录、退出会配置拦截器。登录的时候，调用subject.login(token),token是用户验证信息，
     * 这个时候会在Realm中doGetAuthenticationInfo方法中进行认证。这个时候会把用户提交的验证信息与数据库中存储的认证信息，将所有的数据拿到，在匹配器中进行比较
     * 这边是我们自己实现的CredentialMatcher类的doCredentialsMatch方法，返回true则一致，false则登陆失败
     * 退出的时候，调用subject.logout()，会清除回话信息
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //token携带了用户信息
        UsernamePasswordToken usernamePasswordToken= (UsernamePasswordToken) token;
        //获取页面上输入的用户名、密码
        String username = usernamePasswordToken.getUsername();
        String pwd = String.valueOf(usernamePasswordToken.getPassword());

        //根据用户名查询数据库中对应的记录
        User user = userservice.findByUserName(username);
        if(user!=null){
            throw new RuntimeException("账号不存在");
        }
        if(pwd.equals(user.getPassword())){
            throw new RuntimeException("密码不匹配");
        }
         /*
         //当前realm对象的name
        String realmName = getName();
        //盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername());
        //封装用户信息，构建AuthenticationInfo对象并返回
        AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user, user.getPassword(),
        credentialsSalt, realmName);
                */
        return new SimpleAuthenticationInfo(user,user.getPassword(),this.getClass().getName());
    }
}
