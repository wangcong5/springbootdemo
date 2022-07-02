package com.fly.springbootdemo.mybatisplusdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fly.springbootdemo.repository.mapper.UserMapper;
import com.fly.springbootdemo.repository.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import java.util.*;

/**
 * 说明:
 * <p>
 * 通用 CRUD 封装BaseMapper (opens new window)接口，为 Mybatis-Plus 启动时自动解析实体表关系映射转换为 Mybatis 内部对象注入容器
 * 泛型 T 为任意实体对象
 * 参数 Serializable 为任意类型主键 Mybatis-Plus 不推荐使用复合主键约定每一张表都有自己的唯一 id 主键
 * 对象 Wrapper 为 条件构造器
 */
@SpringBootTest
public class MapperCRUDDemo {
    @Autowired(required = false)
    private UserMapper userMapper;

    /**
     * Insert：插入
     */
    @Test
    @Description("往数据库插入单条数据")
    public void insert() {
        User user = new User();
        user.setId(8L);
        user.setName("zhangsan");
        user.setAge(19);
        user.setEmail("zhangsan@qq.com");

        System.out.println(userMapper.insert(user));
    }


    /**
     * Delete：删除
     * <p>
     * 参数说明
     * 类型	参数名	描述
     * T	entity	实体对象
     */
    @Test
    @Description("根据 entity 条件，删除记录")
    public void delete() {
        // Wrapper<T>, 实体对象封装操作类（可以为 null），为null 删除全部
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id",6);// 删除id=6的记录
        System.out.println(userMapper.delete(queryWrapper));
    }

    @Test
    @Description("删除（根据ID 批量删除）")
    public void deleteBatchIds() {
        Collection<Long> idList = new ArrayList<>();
        idList.add(7L);
        idList.add(8L);
        System.out.println(userMapper.deleteBatchIds(idList));
    }

    @Test
    @Description("根据 ID 删除）")
    public void deleteById() {
        // Serializable id ,表示 主键id
        System.out.println(userMapper.deleteById(6L));
    }

    @Test
    @Description("根据 columnMap 条件，删除记录")
    public void deleteByMap() {
        Map<String, Object> cm = new HashMap<>();
        cm.put("id", 8);// 删除id=8的记录
//        cm.put("name","zhangsan");// 删除 id=8 and name="zhangsan"
        System.out.println(userMapper.deleteByMap(cm));
    }


    /**
     * Update
     * <p>
     * 类型	参数名	描述
     * Wrapper<T>	wrapper	实体对象封装操作类（可以为 null）
     * Collection<? extends Serializable>	idList	主键 ID 列表(不能为 null 以及 empty)
     * Serializable	id	主键 ID
     * Map<String, Object>	columnMap	表字段 map 对象
     */

    @Test
    @Description("根据 whereWrapper 条件，更新记录")
    public void update() {
//        1.更新
//        User updateEntity = new User();
//        updateEntity.setName("lisi");
//        updateEntity.setEmail("lisi@qq.com");
//
//        // 根据查询条件查询符合条件的数据，将id更新为6，name更新为 "lisi"，
//        // 可以为null，为null更新所有记录
//        QueryWrapper<User> whereWrapper = new QueryWrapper<>();
//        whereWrapper.eq("id", 5);
//
//        System.out.println(userMapper.update(updateEntity, whereWrapper));

//        2. UpdateWrapper可以直接更新值，QueryWrapper不行
        User updateEntity = new User();
        updateEntity.setName("Sandy3");
        updateEntity.setEmail("lisi@qq.com");// 与updateWrapper中set的字段相同时，会被覆盖
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.between("age", 30, 50)
                .and(x -> x.eq("name", "Sandy2"))//更新条件
                .set("email", "sandy@qq.com");// 更新字段

        System.out.println(userMapper.update(updateEntity, updateWrapper));
    }

    @Test
    @Description("根据 ID 修改")
    public void updateById() {

        // 根据 id 更新字段，示例：将id=4的记录，name更新为"zhangsan"，email更新为"zhangsan@qq.com"
        User entity = new User();
        entity.setId(4L);
        entity.setName("zhangsan");
        entity.setEmail("zhangsan@qq.com");
        System.out.println(userMapper.updateById(entity));
    }


    /**
     * Select：查询接口
     * <p>
     * 参数说明
     * 类型	参数名	描述
     * T	entity	实体对象 (set 条件值,可为 null)
     * Wrapper<T>	updateWrapper	实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句）
     */
    @Test
    @Description("根据 ID 查询")
    public void selectById() {
        System.out.println(userMapper.selectById(1));
    }

    /**
     * 根据 entity 条件，查询一条记录
     * T selectOne(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
     */
    @Test
    public void selectOne() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 符合条件的查询结果必须只有1条
        queryWrapper.eq("id", 1);
        queryWrapper.eq("name", "lisi");
        System.out.println(userMapper.selectOne(queryWrapper));
    }

    /**
     * // 查询（根据ID 批量查询）
     * List<T> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
     */
    @Test
    public void selectBatchIds() {
        Collection<Long> userCollection = new ArrayList<>();
        // 查询id=1 和id=2的记录
        userCollection.add(1L);
        userCollection.add(2L);
        System.out.println(userMapper.selectBatchIds(userCollection));
    }

    /**
     * // 根据 entity 条件，查询全部记录
     * List<T> selectList(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
     * Wrapper: 通过条件构造器查询一个list集合
     */
    @Test
    public void selectList() {
        // 1)若没有条件，则可以设置null为参数
//        List<Users> users=userMapper.selectList(null);

        // 2)通过querMapper过滤器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id",3);// id=3
//        queryWrapper.ne("id",3);// id!=3
//        queryWrapper.ge("id",2);//  gt:大于; lt: 小于，id<2; le: 小于等于; ge：大于等于

        // 3)lambda表达式
        queryWrapper.lambda().select(User::getId, User::getName, User::getEmail);// lambda表达式，查询所有id、name字段

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * // 查询（根据 columnMap 条件）
     * List<T> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
     */
    @Test
    public void selectByMap() {
        Map<String, Object> cm = new HashMap<>();
        // id=1 且name=lisi的记录
        cm.put("id", 1);
        cm.put("name", "lisi");

        List<User> list = userMapper.selectByMap(cm);
        System.out.println(list);

    }

    /**
     * // 根据 Wrapper 条件，查询全部记录
     * List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
     * <p>
     * 【使用场景】
     * 1.只查询部分列
     * 当某个表的列特别多，而SELECT的时候只需要选取个别列，查询出的结果也没必要封装成Java实体类对象时（
     * 只查部分列时，封装成实体后，实体对象中的很多属性会是null），则可以用selectMaps，获取到
     * 指定的列后，再自行进行处理即可
     * <p>
     * 2.进行数据统计
     */
    @Test
    public void selectMaps() {
//        0.基本用法
//        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
//        queryWrapper.eq("id",1);
//
//        List<Map<String, Object>> list=userMapper.selectMaps(queryWrapper);
//        System.out.println(list);

//        //        1.只查询部分列
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.select("id", "name", "email").likeRight("name", "li");//likeRight <=> like 'li%'
//        List<Map<String, Object>> list = userMapper.selectMaps(queryWrapper);
//        System.out.println(list);

        //        2.数据统计，按姓氏分组，查看每个姓 的平均年龄，最大年龄，最小年龄
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name,avg(age)", "max(age)", "min(age)").groupBy("name").having("sum(age)>{0}", 10);
        List<Map<String, Object>> list = userMapper.selectMaps(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * // 根据 Wrapper 条件，查询全部记录。注意： 只会返回第一个字段（第一列）的值，其他字段会被舍弃
     * List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
     */
    @Test
    public void selectObjs() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", 1);
        List<Object> list = userMapper.selectObjs(queryWrapper);
        System.out.println(list);
    }

    /**
     * 分页查询
     * 使用mp进行分页查询时，需要创建一个分页拦截器（Interceptor），注册到Spring容器中，随后查询时，通过传入一个分页对象（Page对象）进行查询即可。
     * 单表查询时，可以使用BaseMapper提供的selectPage或selectMapsPage方法。复杂场景下（如多表联查），使用自定义SQL。
     *
     * BaseMapper中提供了2个方法进行分页查询，分别是selectPage和selectMapsPage，
     * 前者会将查询的结果封装成Java实体对象，后者会封装成Map<String,Object>
     */
    /**
     * 分页查询1
     * <p>
     * // 根据 entity 条件，查询全部记录（并翻页）
     * IPage<T> selectPage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
     */
    @Test
    public void selectPage() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("id", 1);

//        IPage<User> iPage = new Page<>(2, 4);// 设置分页信息，查询第2页，每页4条信息
        IPage<User> iPage = new Page<>();// 设置分页信息，查询第2页，每页4条信息
        iPage.setCurrent(2);
        iPage.setSize(4);
        IPage<User> page = userMapper.selectPage(iPage, queryWrapper);

        System.out.println("总记录数 = " + page.getTotal());
        System.out.println("总页数 = " + page.getPages());
        System.out.println("当前页码 = " + page.getCurrent());
        //
        System.out.println("=============获取分页查询结果=============");
        List<User> list = page.getRecords();
        list.forEach(System.out::println);
    }

    /**
     * 分页查询2
     * <p>
     * // 根据 Wrapper 条件，查询全部记录（并翻页）
     * IPage<Map<String, Object>> selectMapsPage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
     * <p>
     */
    @Test
    public void selectMapsPage() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("id", 1);

        IPage<Map<String, Object>> iPage = new Page<>();// 设置分页信息，查询第2页，每页4条信息
        iPage.setCurrent(2);
        iPage.setSize(4);

        IPage<Map<String, Object>> page = userMapper.selectMapsPage(iPage, queryWrapper);

        System.out.println("总记录数 = " + page.getTotal());
        System.out.println("总页数 = " + page.getPages());
        System.out.println("当前页码 = " + page.getCurrent());

        List<Map<String, Object>> list = iPage.getRecords();
        list.forEach(System.out::println);
    }

    /**
     * // 根据 Wrapper 条件，查询满足条件的总记录数
     * Integer selectCount(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
     * <p>
     * 注意，使用这个方法，不能调用QueryWrapper的select方法设置要查询的列了。这个方法会自动添加select count(1)
     */
    @Test
    public void selectCount() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "li");

        Long count = userMapper.selectCount(queryWrapper);

        System.out.println(count);
    }

    /**
     * 自定义SQL
     */
    @Test
    public void selectUserPage() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("id", 1);

        Page<User> iPage = new Page<>();// 设置分页信息，查询第2页，每页4条信息
        iPage.setCurrent(2);
        iPage.setSize(4);

        Page<User> page = userMapper.selectUserPage(iPage, queryWrapper);

        List<User> list = page.getRecords();

        list.forEach(System.out::println);

    }

    @Test
    public void selectUser() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("id", 1);

        List<User> users = userMapper.selectUser(queryWrapper);

        users.forEach(System.out::println);
    }


}
