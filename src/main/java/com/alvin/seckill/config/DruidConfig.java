package com.alvin.seckill.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
/*
* Druid 的配置操作
* */
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDatasource(){
        return new DruidDataSource();
    }

    // 后台监控: 相当于web.xml
    /* SpringBoot内置了 servlet 容器，所以没有web.xml
    * 替代方法：ServletRegistrationBean
    * */
    @Bean
    public ServletRegistrationBean statViewServlet(){

        ServletRegistrationBean<StatViewServlet> bean
                = new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");

        // 后台需要有人登录
        HashMap<String, String> intParams = new HashMap<>();
        intParams.put("loginUsername","admin"); // loginUsername 是固定的
        intParams.put("loginPassword","123456");
        // 允许谁可以访问
        intParams.put("allow","");

        bean.setInitParameters(intParams); // 设置初始化参数
        return  bean;
    }

    // Filter
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        // 过滤的请求
        HashMap<String, String> intParams = new HashMap<>();

        // 这些东西不进行统计
        intParams.put("exclusion","*.js,*.css,/druid/*");
        bean.setInitParameters(intParams);

        return bean;
    }
}
/*
访问：http://localhost:8080/druid/login.html
出现：localhost 将您重定向的次数过多
ServletRegistrationBean<StatViewServlet> bean
        = new ServletRegistrationBean<>(new StatViewServlet());
改为：
ServletRegistrationBean<StatViewServlet> bean
        = new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");

* */