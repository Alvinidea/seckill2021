package com.alvin.seckill.mapper;


import com.alvin.seckill.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper // 表示这是一个mybatis的mapper类
@Repository
public interface UserMapper {

    public User getByNickname(@Param("nickname") String nickname);

    public User getById(@Param("id") long id) ;

    public void update(User toBeUpdate);

    public void insert(User newUser);

    public int getCountByUserName(@Param("userName") String userName);
}
