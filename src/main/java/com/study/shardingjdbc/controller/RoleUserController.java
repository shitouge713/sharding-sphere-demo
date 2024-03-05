/*
 * All rights Reserved, Designed By Nemo
 * 2020/9/3 13:40
 */
package com.study.shardingjdbc.controller;

import com.study.shardingjdbc.entity.RoleUser;
import com.study.shardingjdbc.entity.SysUser;
import com.study.shardingjdbc.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色用户关联
 *
 * @author: susu
 */
@RestController
@RequestMapping("/roleuser")
public class RoleUserController {

    @Autowired
    private RoleUserService roleUserService;

    /**
     * 新增
     *
     * @param
     * @return
     */
    @GetMapping("/insert")
    public String insert(RoleUser roleUser) {
        roleUserService.insert(roleUser);
        return "SUCCESS";
    }

    @GetMapping("/select")
    public List<SysUser> insert(Integer roleId) {
        return roleUserService.selectByRoleId(roleId);
    }

    @GetMapping("/delete")
    public String delete(Integer roleId) {
       roleUserService.deleteByRoleId(roleId);
       return "SUCCESS";
    }
}
