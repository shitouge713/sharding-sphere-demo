/*
 * All rights Reserved, Designed By Nemo
 * 2020/9/3 13:41
 */
package com.study.shardingjdbc.service;

import com.study.shardingjdbc.dao.RoleUserDao;
import com.study.shardingjdbc.entity.Role;
import com.study.shardingjdbc.dao.RoleDao;
import io.shardingsphere.transaction.annotation.ShardingTransactionType;
import io.shardingsphere.transaction.api.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户相关业务 实现
 *
 * @author: susu
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleUserDao roleUserDao;

    /**
     * 新增
     *
     * @param sysUser
     */
    @Override
    public void insert(Role sysUser) {
        roleDao.insert(sysUser);
    }

    @Override
    public void delete(Integer id) {
        roleDao.delete(id);
    }

    @ShardingTransactionType(TransactionType.XA)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteRoleAndRoleUser(Integer id) {
        roleUserDao.deleteByRoleId(id);
        int a = 1/0;
        roleDao.delete(id);
    }


}
