package com.alvin.seckill.service;

import com.alvin.seckill.mapper.UserMapper;
import com.alvin.seckill.pojo.User;
import com.alvin.seckill.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public boolean register(HttpServletResponse response , String userName , String passWord , String salt) {
        User user =  new User();
        user.setNickname(userName);
        String DBPassWord =  MD5Utils.formPassToDBPass(passWord , salt);
        user.setPassword(DBPassWord);
        user.setRegister_date(new Date());
        user.setSalt(salt);
        user.setNickname(userName);

        try {
            userMapper.insert(user);

            User tuser = userMapper.getByNickname(user.getNickname());
            if(tuser == null){
                return false;
            }
            //生成cookie 将session返回游览器 分布式session
/*            String token= UUIDUtil.uuid();
            addCookie(response, token, user);*/
        } catch (Exception e) {
            // logger.error("注册失败",e);
            return false;
        }
        return true;
    }
}
