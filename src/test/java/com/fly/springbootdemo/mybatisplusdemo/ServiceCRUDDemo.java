package com.fly.springbootdemo.mybatisplusdemo;

import com.fly.springbootdemo.repository.po.User;
import com.fly.springbootdemo.repository.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * 说明:
 *
 * 通用 Service CRUD 封装IService (opens new window)接口，进一步封装 CRUD 采用 get 查询单行 remove 删除 list 查询集合 page 分页 前缀命名方式区分 Mapper 层避免混淆，
 * 泛型 T 为任意实体对象
 * 建议如果存在自定义通用 Service 方法的可能，请创建自己的 IBaseService 继承 Mybatis-Plus 提供的基类
 * 对象 Wrapper 为 条件构造器
 *
 * 如果是多数据源，需要再XXServiceImpl类上加@DS注解，指定数据源，数据源在 application.yaml 中配置
 */
@SpringBootTest
public class ServiceCRUDDemo{
    @Autowired
    private UserService userService;


    /**
     * 插入
     * // 插入一条记录（选择字段，策略插入）
     */
    @Test
    public void save(){
        User user=new User();
        user.setId(10L);
        user.setName("liudehua");
        user.setAge(40);
        user.setEmail("liudehua@qq.com");
        userService.save(user);
    }

    /**
     * // 插入（批量）
     */
    @Test
    public void saveBatch(){
        Collection<User> entityList=new ArrayList<>();
        User user1=new User();
        user1.setId(1L);
        user1.setName("liudehua");
        user1.setAge(40);
        user1.setEmail("liudehua@qq.com");

        User user2=new User();
        user2.setId(2L);
        user2.setName("liudehua2");
        user2.setAge(40);
        user2.setEmail("liudehua2@qq.com");

        entityList.add(user1);
        entityList.add(user2);
        userService.saveBatch(entityList);// batchSize默认1000，一次插入1000条，超过1000时分批插入，可自定义设置
    }

    /**
     * 参数说明
     * 类型	参数名	描述
     * T	entity	实体对象
     * Collection<T>	entityList	实体对象集合
     * int	batchSize	插入批次数量
     *
     * // 根据updateWrapper尝试更新，否继续执行saveOrUpdate(T)方法
     */
    @Test
    public void SaveOrUpdate(){
        Collection<User> entityList=new ArrayList<>();
        User user1=new User();
        user1.setId(5L);
        user1.setName("liudehua");
        user1.setAge(40);
        user1.setEmail("liudehua@qq.com");

        User user2=new User();
        user2.setId(6L);
        user2.setName("liudehua2");
        user2.setAge(40);
        user2.setEmail("liudehua2@qq.com");

        entityList.add(user1);
        entityList.add(user2);
//        userService.SaveOrUpdate(entityList,5);
    }

}
