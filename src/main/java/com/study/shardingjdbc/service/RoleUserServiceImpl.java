/*
 * All rights Reserved, Designed By Nemo
 * 2020/9/3 13:41
 */
package com.study.shardingjdbc.service;

import com.study.shardingjdbc.entity.RoleUser;
import com.study.shardingjdbc.entity.SysUser;
import com.study.shardingjdbc.dao.RoleUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户相关业务 实现
 *
 * @author: susu
 */
@Service
public class RoleUserServiceImpl implements RoleUserService {

    @Autowired
    private RoleUserDao roleUserDao;

    /**
     * 新增
     *
     * @param
     */
    @Override
    public void insert(RoleUser roleUser) {
        roleUserDao.insert(roleUser);
    }

    @Override
    public List<SysUser> selectByRoleId(Integer roleId) {
        return roleUserDao.selectByRoleId(roleId);
    }

    @Override
    public void deleteByRoleId(Integer roleId) {
        roleUserDao.deleteByRoleId(roleId);
    }


}
