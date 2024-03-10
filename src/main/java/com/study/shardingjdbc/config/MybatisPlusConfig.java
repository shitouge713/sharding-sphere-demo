package com.study.shardingjdbc.config;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.study.shardingjdbc.suanfa.SequenceOwner;
import com.study.shardingjdbc.suanfa.Snowflake64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 解决mybatis-plus 雪花算法，workId和dataCenterId一样的问题
 * 通过随机数的方式解决
 */
@Slf4j
@Configuration
public class MybatisPlusConfig {
    /**
     * description: 自定义ID主键生成器 <br>
     * date: 2022/6/1 11:38 <br>
     * author: flygo <br>
     *
     * @return com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator
     */
    /**
     * DefaultIdentifierGenerator 是生成雪花算法id 的默认实现
     * 这里自定义的原因是解决workerId、dataCenterId重复的问题
     *
     * @return 0
     */
    // 以免多线程批量插入，ID主键生成有重复，主键冲突错误
    // 1-31 随机数
    private long workerId = RandomUtil.randomLong(1, 31);
    private long dataCenterId = RandomUtil.randomLong(1, 31);

    @Bean
    public DefaultIdentifierGenerator defaultIdentifierGenerator() {
        log.info("自定义生成的workerId:{},dataCenterId:{}", workerId, dataCenterId);
        return new DefaultIdentifierGenerator(workerId, dataCenterId);
    }

    @Bean
    public SequenceOwner sequence() {
        log.info("自定义生成的workerId:{},dataCenterId:{}", workerId, dataCenterId);
        return new SequenceOwner(workerId, dataCenterId);
    }

    @Bean
    public Snowflake64 snowflake64() {
        // 以免多线程批量插入，ID主键生成有重复，主键冲突错误
        // 1-31 随机数
        long workerId = RandomUtil.randomLong(1, 31);
        // 1-31 随机数
        long dataCenterId = RandomUtil.randomLong(1, 31);

        return new Snowflake64(workerId, dataCenterId);
    }
}
