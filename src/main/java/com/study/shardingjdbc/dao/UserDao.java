/*
 * All rights Reserved, Designed By Nemo
 * 2020/9/3 13:41
 */
package com.study.shardingjdbc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.shardingjdbc.entity.BaseQueryBean;
import com.study.shardingjdbc.entity.Cart;
import com.study.shardingjdbc.entity.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: susu
 */
@Mapper
public interface UserDao extends BaseMapper<SysUser> {

    /**
     * 新增
     *
     * @param sysUser
     */
    @Insert("insert into sys_user (name) values (#{name})")
    void insertZdy(SysUser sysUser);

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
     * 根据条件获取
     *
     * @param queryBean
     * @return
     */
    List<SysUser> selectByExample(BaseQueryBean queryBean);

    /**
     * 根据条件得到总数
     *
     * @param queryBean
     * @return
     */
    long countByExample(BaseQueryBean queryBean);
}
