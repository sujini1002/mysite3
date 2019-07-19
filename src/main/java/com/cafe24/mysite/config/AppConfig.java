package com.cafe24.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.cafe24.config.app.AppSecurityConfig2;
import com.cafe24.config.app.DBConfig;
import com.cafe24.config.app.MyBatisConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.cafe24.mysite.service", "com.cafe24.mysite.repository", "com.cafe24.mysite.aspect", "com.cafe24.mysite.security"})
@Import({AppSecurityConfig2.class, DBConfig.class, MyBatisConfig.class})
public class AppConfig {
}