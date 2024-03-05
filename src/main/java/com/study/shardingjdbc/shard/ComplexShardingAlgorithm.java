package com.study.shardingjdbc.shard;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;
import java.util.HashSet;

/**
 * 自定义复合分片策略的实现
 * 复合分片策略（Complex Algorithm）：
 * 复合分片策略允许您使用多个分片键来确定数据应该被路由到哪个库和表。这在复杂的场景中很有用，可以根据多个条件来选择数据的位置。
 */
public class ComplexShardingAlgorithm implements ComplexKeysShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Long> shardingValue) {
        Collection<String> result = new HashSet<>();

        Long userId = shardingValue.getColumnNameAndShardingValuesMap().get("user_id").iterator().next();
        Long orderId = shardingValue.getColumnNameAndShardingValuesMap().get("order_id").iterator().next();

        // 自定义分片逻辑，根据userId和orderId决定数据路由到哪个库和表
        // 这里只是一个示例，您需要根据实际需求编写合适的分片逻辑

        // 假设有两个库 ds0 和 ds1，两个表 order_0 和 order_1
        if (userId % 2 == 0) {
            result.add("ds0.order_" + orderId % 2);
        } else {
            result.add("ds1.order_" + orderId % 2);
        }

        return result;
    }
}
