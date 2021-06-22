package com.alvin.seckill.config.Shiro;


import com.alvin.seckill.mapper.UserMapper;
import com.alvin.seckill.pojo.User;
import com.alvin.seckill.service.LoginService;
import com.alvin.seckill.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserMapper userMapper;

    /**
     * 授权操作
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了 =》 授权 doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("user:all");


        Subject subject = SecurityUtils.getSubject();       // 获取当前登录的这个对象，LoginController中登录的subject
        User currentUser = (User) subject.getPrincipal();   // 获取到User对象

        // 设置当前用户的权限
        // info.addStringPermission(currentUser.getPerms());
        // 本应该使用用户具有的权限，但是我的设计中最开始未考虑 权限，所以这儿直接写一个字符串 "user:all" 作为权限
        info.addStringPermission("user:all");
        return info;
        // return null;
    }

    /**
     * 认证操作
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了 =》 认证 doGetAuthenticationInfo");
        /* 可以链接数据库查找 */
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;

        // 从数据库中获取数据
        User user = userMapper.getByNickname(userToken.getUsername());
        if(user == null)
            return null;
        String pwd = user.getPassword();
        // 密码加密问题： MD5 和MD5 + salt 加密
        // 密码认证
        // 在这儿设置第一个参数
        return new SimpleAuthenticationInfo(user,pwd,"");
    }
}
