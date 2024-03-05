package com.study.shardingjdbc.shard;

import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 一致性哈希算法工具类
 * 使用FNV1_32_HASH算法计算key的Hash值
 * 也可以使用 MurmurHash3 或者别的加密方式
 * @author sxl
 * @Date 2024/3/5
 */
public class ConsistenceHashUtil {
    //存储所有节点，按照hash值排序的
    @Getter
    private SortedMap<Long, String> virtualNodes = new TreeMap<>();

    // 设置虚拟节点的个数
    private static final int VIRTUAL_NODES = 3;


    public ConsistenceHashUtil() {
    }

    //virtualTableNodes 虚拟节点  tableNodes 真实节点
    public ConsistenceHashUtil(SortedMap<Long, String> virtualTableNodes, Collection<String> tableNodes) {
        if (Objects.isNull(virtualTableNodes)) {
            virtualTableNodes = initNodesToHashLoop(tableNodes);
        }
        this.virtualNodes = virtualTableNodes;
    }

    public SortedMap<Long, String> initNodesToHashLoop(Collection<String> tableNodes) {
        SortedMap<Long, String> virtualTableNodes = new TreeMap<>();
        for (String node : tableNodes) {
            for (int i = 0; i < VIRTUAL_NODES; i++) {
                String s = String.valueOf(i);
                String virtualNodeName = node + "-manji" + s;
                long hash = getHash(virtualNodeName);

                virtualTableNodes.put(hash, virtualNodeName);
            }
        }
        return virtualTableNodes;
    }

    /**
     * 通过计算key的hash
     * 计算映射的表节点
     *
     * @param key
     * @return
     */
    public String getTableNode(String key) {
        String virtualNode = getVirtualTableNode(key);
        //虚拟节点名称截取后获取真实节点
        if (!StringUtils.isEmpty(virtualNode)) {
            return virtualNode.substring(0, virtualNode.indexOf("-"));
        }
        return null;
    }

    /**
     * 获取虚拟节点
     *
     * @param key
     * @return
     */
    public String getVirtualTableNode(String key) {
        long hash = getHash(key);
        // 得到大于该Hash值的所有Map
        SortedMap<Long, String> subMap = virtualNodes.tailMap(hash);
        String virtualNode;
        if (subMap.isEmpty()) {
            //如果没有比该key的hash值大的，则从第一个node开始
            Long i = virtualNodes.firstKey();
            //返回对应的服务器
            virtualNode = virtualNodes.get(i);
        } else {
            //第一个Key就是顺时针过去离node最近的那个结点
            Long i = subMap.firstKey();
            //返回对应的服务器
            virtualNode = subMap.get(i);
        }
        return virtualNode;
    }

    /**
     * 使用FNV1_32_HASH算法计算key的Hash值
     * 也可以使用 MurmurHash3 或者别的加密方式
     *
     * @param key
     * @return
     */
    public long getHash(String key) {
//        return MurmurHash3.murmurhash3_x86_32(key);
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < key.length(); i++)
            hash = (hash ^ key.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        // 如果算出来的值为负数则取其绝对值
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }
}
