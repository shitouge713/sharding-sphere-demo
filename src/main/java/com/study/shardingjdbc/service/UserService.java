/*
 * All rights Reserved, Designed By Nemo
 * 2020/9/3 13:40
 */
package com.study.shardingjdbc.service;


import com.study.shardingjdbc.entity.BaseQueryBean;
import com.study.shardingjdbc.entity.PageResultBean;
import com.study.shardingjdbc.entity.SysUser;

/**
 * 用户相关业务
 *
 * @author: susu
 */
public interface UserService {

    /**
     * 新增
     *
     * @param sysUser
     */
    void insert(SysUser sysUser);

    /**
     * 更新
     *
     * @param sysUser
     */
    void update(SysUser sysUser);

    /**
     * 根据ID删除
     *
     * @param id
     */
    void deleteByPrimaryKey(Integer id);

    /**
     * 根据ID获取
     *
     * @param id
     * @return
     */
    SysUser selectByPrimaryKey(Integer id);

    /**
     * 分页获取
     *
     * @param queryBean
     * @return
     */
    PageResultBean<SysUser> page(BaseQueryBean queryBean);

    /**
     * 批量新增测试
     *
     * @param size
     * @return
     */
    int batchInsert(Integer size);
}
