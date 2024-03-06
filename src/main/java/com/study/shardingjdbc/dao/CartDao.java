package com.study.shardingjdbc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.shardingjdbc.entity.Cart;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CartDao extends BaseMapper<Cart> {

    @Insert("insert into o_cart(id, user_id, sku_id) values(#{id}, #{userId}, #{skuId})")
    int insertSharding(Cart cart);

}
