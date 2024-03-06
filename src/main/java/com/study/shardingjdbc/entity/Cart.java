package com.study.shardingjdbc.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 购物车表
 * </p>
 *
 * @author zgd
 * @version V1.0
 * @date 2022-07-08
 */
@Data
@EqualsAndHashCode
public class Cart {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 业务线 1：到柜 2：到家， 3，x平台
     */
    private Integer bizType;

    /**
     * 商家ID
     */
    private String merchantCode;

    /**
     * shopId， 目前x平台在用
     */
    private String shopId;

    /**
     * skuID
     */
    private String skuId;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 是否有效标识 1：有效 0：无效
     */
    private Boolean valid;


    private LocalDateTime gmtCreate;


    private LocalDateTime gmtModify;

}
