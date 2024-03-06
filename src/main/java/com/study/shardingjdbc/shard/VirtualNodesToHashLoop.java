package com.study.shardingjdbc.shard;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.core.rule.DataNode;
import org.apache.shardingsphere.core.rule.ShardingRule;
import org.apache.shardingsphere.core.rule.TableRule;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.stream.Collectors;

/**
 * 包含虚拟节点的hash环
 *
 * @author sxl
 * @Date 2024/3/5
 */
@Slf4j
@Component
//@Lazy
public class VirtualNodesToHashLoop {
    @Resource
    private ShardingDataSource shardingDataSource;

    //key:table表，value：对应的hash环上的所有节点
    @Getter
    private HashMap<String, SortedMap<Long, String>> tableVirtualNodes = new HashMap<>();

    @PostConstruct
    public void init() {
        try {
            ShardingRule rule = shardingDataSource.getShardingContext().getShardingRule();
            Collection<TableRule> tableRules = rule.getTableRules();
            ConsistenceHashUtil consistenceHashUtil = new ConsistenceHashUtil();
            for (TableRule tableRule : tableRules) {
                String logicTable = tableRule.getLogicTable();
                tableVirtualNodes.put(logicTable,
                        consistenceHashUtil.initNodesToHashLoop(
                                tableRule.getActualDataNodes()
                                        .stream()
                                        .map(DataNode::getTableName)
                                        .collect(Collectors.toList()))
                );
            }
            System.out.println("tableVirtualNodes:" + tableVirtualNodes);
        } catch (Exception e) {
            log.error("分表节点初始化失败 {}", e);
        }
    }
}
