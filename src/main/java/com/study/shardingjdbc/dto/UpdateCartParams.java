package com.study.shardingjdbc.dto;

import lombok.Data;

/**
 * 测试分库分表问题
 */
@Data
public class UpdateCartParams {
    private Long id;
    private Integer kcSize;
}
