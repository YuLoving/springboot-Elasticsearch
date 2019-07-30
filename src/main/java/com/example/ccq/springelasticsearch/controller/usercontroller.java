package com.example.ccq.springelasticsearch.controller;


import com.example.ccq.springelasticsearch.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class usercontroller {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }

    /**
     * 拥有admin角色的人才能访问
     * @return
     */
    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    /**
     * 拥有update权限的人才能访问
     * @return
     */
    @RequestMapping("/update")
    public String update(){
        return "update";
    }


    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();//取出当前验证主体
        if(subject != null){
            subject.logout(); //执行一次logout的操作，将session全部清空
        }
        return "login";
    }

    /**
     *  整个form表单的验证流程：
     *  将登陆的用户/密码传入UsernamePasswordToken，当调用subject.login(token)开始，
     *  调用Relam的doGetAuthenticationInfo方法，开始密码验证
     *  此时这个时候执行我们自己编写的CredentialMatcher（密码匹配器），
     *  执行doCredentialsMatch方法，具体的密码比较实现在这实现
     */
    @RequestMapping(value = "/loginUser")
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session) {
        UsernamePasswordToken token = new UsernamePasswordToken();
        token.setUsername(username);
        token.setPassword(password.toCharArray());
        //获取当前用户主体
        Subject subject = SecurityUtils.getSubject();
        //开始登陆
        subject.login(token);
      /*  //session
        Session session = subject.getSession();
        System.out.println("session id:"+session.getId());
        //设置session过期时间
        session.setTimeout(45*100000);*/

        /**
         * 使用shiro时，如果正常登陆（执行subject.login(token)成功）
         * 就能在全局通过SecurityUtils.getSubject().getPrincipal()获取用户信息。
         */
        if(SecurityUtils.getSubject().getPrincipal()!=null) {
            User user = (User) subject.getPrincipal();
            session.setAttribute("user", user);
            return "index";
        }else {
          /*  result.put("code", 400);
            result.put("msg", "用戶名或密码错误!");
            return result;*/
            return "login";
        }

    }

}
