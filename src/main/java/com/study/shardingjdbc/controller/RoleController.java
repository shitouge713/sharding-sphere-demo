/*
 * All rights Reserved, Designed By Nemo
 * 2020/9/3 13:40
 */
package com.study.shardingjdbc.controller;

import com.study.shardingjdbc.entity.Role;
import com.study.shardingjdbc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色操作测试controller
 *
 * @author: susu
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 新增
     *
     * @param role
     * @return
     */
    @GetMapping("/insert")
    public String insert(Role role) {
        roleService.insert(role);
        return "SUCCESS";
    }
    @GetMapping("/delete")
    public String delete(Integer id) {
        roleService.delete(id);
        return "SUCCESS";
    }

    @GetMapping("/deleteRoleAndRoleUser")
    public String deleteRoleAndRoleUser(Integer id) {
        roleService.deleteRoleAndRoleUser(id);
        return "SUCCESS";
    }

}
