package com.fly.springbootdemo.listener;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
@Component
@Slf4j
public class IocCloseListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("[监听器注册成功..]"+IocCloseListener.class.getName());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("[监听到容器关闭..]"+IocCloseListener.class.getName());
    }
}
