package com.study.shardingjdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShardingJdbcApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(ShardingJdbcApplication.class, args);
    }

}
