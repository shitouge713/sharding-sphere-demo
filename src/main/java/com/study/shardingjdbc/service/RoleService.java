/*
 * All rights Reserved, Designed By Nemo
 * 2020/9/3 13:40
 */
package com.study.shardingjdbc.service;

import com.study.shardingjdbc.entity.Role;

/**
 * 用户相关业务
 *
 * @author: susu
 */
public interface RoleService {

    /**
     * 新增
     *
     * @param sysUser
     */
    void insert(Role sysUser);


    void delete(Integer id);

    void deleteRoleAndRoleUser(Integer id);
}
