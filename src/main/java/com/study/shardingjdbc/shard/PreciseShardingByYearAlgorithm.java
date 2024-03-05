package com.study.shardingjdbc.shard;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;


/**
 * 标准分片策略允许您根据某个字段的具体值范围来分割数据。例如，您可以将数据按照时间范围进行分表，每个表代表一段时间的数据。
 * 自定义精确分片策略
 * -- 配置文件中引用策略
 * spring:
 *   shardingsphere:
 *     sharding:
 *       tables:
 *         order:
 *           actualDataNodes: ds${0..1}.order_${201901..201912}
 *           tableStrategy:
 *             standard:
 *               shardingColumn: create_time
 *               preciseAlgorithmClassName: com.example.order.PreciseShardingByYearAlgorithm
 */
public class PreciseShardingByYearAlgorithm implements PreciseShardingAlgorithm<Date> {

    // 从日期中获取年份
    private int getYear(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return Integer.parseInt(sdf.format(date));
    }

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Date> preciseShardingValue) {
        // 获取传入的日期
        Date createTime = preciseShardingValue.getValue();
        // 根据日期计算年份
        int year = getYear(createTime);
        // 构建表名，假设表名为 order_年份
        String tableName = "order_" + year;
        // 返回计算后的表名
        return tableName;
    }
}
