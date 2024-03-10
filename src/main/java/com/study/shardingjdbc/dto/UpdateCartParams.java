package com.study.shardingjdbc.dto;

import com.study.shardingjdbc.suanfa.Snowflake64;
import lombok.Data;

/**
 * 测试分库分表问题
 */
@Data
public class UpdateCartParams {
    private Long id;
    private Integer kcSize;

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        Snowflake64 snowflake = new Snowflake64();
        for (int i = 0; i < 3; i++) {
            String snowflakeNextId = snowflake.nextIdStr();
            System.out.println(snowflakeNextId);
        }

    }
}
