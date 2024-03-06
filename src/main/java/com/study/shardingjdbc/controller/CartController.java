/*
 * All rights Reserved, Designed By Nemo
 * 2020/9/3 13:40
 */
package com.study.shardingjdbc.controller;


import com.study.shardingjdbc.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 购物车相关操作测试controller
 *
 * @author: sxl
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 批量插入
     *
     * @param size
     * @return
     */
    @GetMapping("/batchInsert")
    public String batchInsert(Integer size) {
        int count = cartService.batchInsert(size);
        return "INSERT " + count + " ROWS";
    }
}
