/*
 * All rights Reserved, Designed By Nemo
 * 2020/9/3 13:52
 */
package com.study.shardingjdbc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户基础对象
 *
 * @author: susu
 */
@Data
@EqualsAndHashCode
public class SysUser {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

}
