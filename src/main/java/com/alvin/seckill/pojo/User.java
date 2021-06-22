package com.alvin.seckill.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
    private Long id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Date register_date;
    private Date last_login_date;
    private Integer login_count;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", head='" + head + '\'' +
                ", register_date=" + register_date +
                ", last_login_date=" + last_login_date +
                ", login_count=" + login_count +
                '}';
    }
}
