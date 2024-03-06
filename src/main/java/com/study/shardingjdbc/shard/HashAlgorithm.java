package com.study.shardingjdbc.shard;

import cn.hutool.extra.spring.SpringUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedMap;

/**
 * 自定义哈希算法 + 真实节点实现数据分片
 * PreciseShardingAlgorithm，精确分片算法
 *
 * @author sxl
 * @Date 2024/3/5
 */
public class HashAlgorithm implements PreciseShardingAlgorithm<Long> {

    /**
     * @param collection           collection 配置文件中解析到的所有分片节点
     * @param preciseShardingValue 解析到的sql值 ，哪个表，哪个字段，值是多少，根据该值去计算路由
     * @return
     */
    @Override
    public String doSharding(Collection collection, PreciseShardingValue preciseShardingValue) {
        //获取hash环对象
        NodesToHashLoop nodesToHashLoop = SpringUtil.getBean(NodesToHashLoop.class);
        if (CollectionUtils.isEmpty(collection)) {
            return preciseShardingValue.getLogicTableName();
        }
        //这里主要为了兼容当联表查询时，如果两个表非关联表则
        //当对副表分表时shardingValue这里传递进来的依然是主表的名称，
        //但availableTargetNames中确是副表名称，所有这里要从availableTargetNames中匹配真实表
        ArrayList<String> availableTargetNameList = new ArrayList<>(collection);
        String logicTableName = availableTargetNameList.get(0).replaceAll("[^(a-zA-Z_)]", "");
        //获取数据表对应的hash环上的节点
        SortedMap<Long, String> tableHashNode = nodesToHashLoop.getTableRealNodes().get(logicTableName);
        ConsistenceHashUtil consistentHashAlgorithm = new ConsistenceHashUtil(tableHashNode);
        //根据分片的key，计算出该条数据应该存储在哪个节点上
        String realNode = consistentHashAlgorithm.getTableNode(String.valueOf(preciseShardingValue.getValue()));
        return realNode;
    }
}
