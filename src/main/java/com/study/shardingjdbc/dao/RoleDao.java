/*
 * All rights Reserved, Designed By Nemo
 * 2020/9/3 13:41
 */
package com.study.shardingjdbc.dao;

import com.study.shardingjdbc.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: susu
 */
@Mapper
public interface RoleDao {

    /**
     * 新增
     *
     * @param sysUser
     */
    void insert(Role sysUser);


    void delete(Integer id);
}
