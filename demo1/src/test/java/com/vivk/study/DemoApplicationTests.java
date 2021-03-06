package com.vivk.study;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vivk.study.entity.User;
import com.vivk.study.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        System.out.println(("----- selectAll method test ------"));
        //UserMapper 中的 selectList() 方法的参数为 MP 内置的条件封装器 Wrapper
        //所以不填写就是无任何条件
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }
    @Test
    public void testInsert(){

        User user = new User();
        user.setName("东方不败");
        user.setAge(60);
        user.setEmail("55312@qq.com");
        int result = userMapper.insert(user);
        System.out.println(result); //影响的行数
        System.out.println(user); //id自动回填
    }
    @Test
    public void testUpdateById(){

        User user = new User();
        user.setId(1L);
        user.setAge(88);

        int result = userMapper.updateById(user);
        System.out.println(result);
    }
    /**
     * 测试 乐观锁插件
     */
    @Test
    public void testOptimisticLocker() {

        //查询
        User user = userMapper.selectById(1444948783046021122L);
        //修改数据
        user.setName("Helen Yao");
        user.setEmail("helen@qq.com");
        //执行更新
        userMapper.updateById(user);
    }
    //多个id的批量查询
    @Test
    public void testSelectDemo(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        System.out.println(users);
    }
    //多条件查询
    public void testSelectByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","Jone");
        map.put("age",18);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }
    //单表分页查询
    @Test
    public void testPage(){
        //1.创建Page对象
        //传入两个参数
        Page<User> page=new Page<>(1,3);
        //调用MP分页查询过程中，底层封装
        //把分页所有数据封装到page对象里面
        userMapper.selectPage(page,null);

        //通过page对象获取分页数据
        System.out.println(page.getCurrent());//当前页
        System.out.println(page.getRecords());//每页数据list集合
        System.out.println(page.getSize());//每页显示的记录数
        System.out.println(page.getTotal());//总记录数
        System.out.println(page.getPages());//总页数

        System.out.println(page.hasNext());//是否有下一页
        System.out.println(page.hasPrevious());//是否有上一页
    }
}
