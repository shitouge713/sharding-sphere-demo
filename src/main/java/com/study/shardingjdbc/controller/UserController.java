/*
 * All rights Reserved, Designed By Nemo
 * 2020/9/3 13:40
 */
package com.study.shardingjdbc.controller;


import com.study.shardingjdbc.entity.BaseQueryBean;
import com.study.shardingjdbc.entity.PageResultBean;
import com.study.shardingjdbc.entity.SysUser;
import com.study.shardingjdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关操作测试controller
 * @author: susu
 * 
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 新增
     * @param sysUser
     * @return
     */
    @GetMapping("/insert")
    public String insert(SysUser sysUser){
        userService.insert(sysUser);
        return "SUCCESS";
    }

    /**
     * 更新
     * @param sysUser
     * @return
     */
    @GetMapping("/update")
    public String update(SysUser sysUser){
        userService.update(sysUser);
        return "SUCCESS";
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public String deleteByPrimaryKey(Integer id){
        userService.deleteByPrimaryKey(id);
        return "SUCCESS";
    }

    /**
     * 获取数据
     * @param id
     * @return
     */
    @GetMapping("/get")
    public SysUser selectByPrimaryKey(Integer id){
        return userService.selectByPrimaryKey(id);
    }

    /**
     * 分页
     * @param queryBean
     * @return
     */
    @GetMapping("/page")
    public PageResultBean<SysUser> page(BaseQueryBean queryBean){
        return userService.page(queryBean);
    }

    /**
     * 批量插入
     * @param size
     * @return
     */
    @GetMapping("/batchInsert")
    public String batchInsert(Integer size){
        int count = userService.batchInsert(size);
        return "INSERT "+count+" ROWS";
    }
}
