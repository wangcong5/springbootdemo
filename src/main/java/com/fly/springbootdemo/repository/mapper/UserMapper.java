package com.fly.springbootdemo.repository.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fly.springbootdemo.repository.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Mapper //如果在服务启动入口中配置了 @MapperScan 此处可省略，否则必须在Mapper文件中加@Mapper注解
public interface UserMapper extends BaseMapper<User> {

    /**
     * 自定义SQL，这里采用纯注解方式。当然，若SQL比较复杂，建议还是采用XML的方式
     * @param wrapper
     * @return
     */
    // SQL中不写WHERE关键字，且固定使用${user}
    @Select("SELECT * FROM user ${ew.customSqlSegment}")
    List<User> selectUser(@Param(Constants.WRAPPER) Wrapper<User> wrapper);

    @Select("SELECT * FROM user where id>11 and name like 'J%'")
    Page<User> selectUserPage(Page<User> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper);
}
