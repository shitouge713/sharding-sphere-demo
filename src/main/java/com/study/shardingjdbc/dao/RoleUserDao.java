/*
 * All rights Reserved, Designed By Nemo
 * 2020/9/3 13:41
 */
package com.study.shardingjdbc.dao;

import com.study.shardingjdbc.entity.RoleUser;
import com.study.shardingjdbc.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: susu
 */
@Mapper
public interface RoleUserDao {

    /**
     * 新增
     *
     * @param
     */
    void insert(RoleUser roleUser);


    List<SysUser> selectByRoleId(Integer roleId);

    void deleteByRoleId(Integer roleId);
}
