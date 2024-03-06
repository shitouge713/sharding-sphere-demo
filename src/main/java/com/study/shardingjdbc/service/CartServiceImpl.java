/*
 * All rights Reserved, Designed By Nemo
 * 2020/9/3 13:41
 */
package com.study.shardingjdbc.service;

import com.study.shardingjdbc.dao.CartDao;
import com.study.shardingjdbc.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 购物车相关业务实现
 *
 * @author: sxl
 */
@Service
public class CartServiceImpl implements CartService {

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            10,
            30,
            50,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(30),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Autowired
    private CartDao cartDao;

    @Override
    public int batchInsert(Integer size) {
        if (size == null || size <= 0) {
            return 0;
        }
        for (int i = 0; i < size; i++) {
            final int idx = i;
            threadPoolExecutor.submit(() -> {
                Cart cart = new Cart();
                cart.setUserId(String.valueOf(idx));
                cart.setSkuId("Test" + idx);
                cartDao.insertSharding(cart);
            });
        }
        return size;
    }
}
