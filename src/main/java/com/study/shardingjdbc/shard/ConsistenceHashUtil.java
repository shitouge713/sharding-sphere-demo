package com.study.shardingjdbc.shard;

import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 一致性哈希算法工具类
 * 使用FNV1_32_HASH算法/MurmurHash3算法/其他算法计算key的Hash值
 *
 * @author sxl
 * @Date 2024/3/5
 */
public class ConsistenceHashUtil {

    private static String preFix = "-manji";
    //存储所有节点，按照hash值排序的，使用treeMap，key为hash值，value为节点
    @Getter
    private SortedMap<Long, String> virtualNodes = new TreeMap<>();


    // 设置虚拟节点的个数
    private static final int VIRTUAL_NODES = 3;


    public ConsistenceHashUtil() {
    }

    /**
     * virtualTableNodes 传入的hash环节点
     *
     * @param virtualTableNodes
     */
    public ConsistenceHashUtil(SortedMap<Long, String> virtualTableNodes) {
        this.virtualNodes = virtualTableNodes;
    }

    /**
     * 只有真实节点的hash环
     *
     * @param tableNodes
     * @return
     */
    public SortedMap<Long, String> realNodesToHashLoop(Collection<String> tableNodes) {
        SortedMap<Long, String> virtualTableNodes = new TreeMap<>();
        for (String node : tableNodes) {
            long hash = getHash(node);
            virtualTableNodes.put(hash, node);
        }
        System.out.println("realTableNodes:" + virtualTableNodes);
        return virtualTableNodes;
    }

    /**
     * 有虚拟节点的hash环
     *
     * @param tableNodes
     * @return
     */
    public SortedMap<Long, String> initNodesToHashLoop(Collection<String> tableNodes) {
        SortedMap<Long, String> virtualTableNodes = new TreeMap<>();
        for (String node : tableNodes) {
            for (int i = 0; i < VIRTUAL_NODES; i++) {
                String s = String.valueOf(i);
                String virtualNodeName = node + preFix + s;
                //如果发现hash不是32位，可能是由于显示或输出格式的问题导致的
                long hash = getHash(virtualNodeName);
                virtualTableNodes.put(hash, virtualNodeName);
            }
        }
        System.out.println("virtualTableNodes:" + virtualTableNodes);
        return virtualTableNodes;
    }

    /**
     * 获取节点并映射成真实节点
     *
     * @param key
     * @return
     */
    public String getTableNode(String key) {
        //获取临近的下一个节点
        String virtualNode = getVirtualTableNode(key);
        //如果是虚拟节点，虚拟节点映射成真实节点
        if (!StringUtils.isEmpty(virtualNode) && virtualNode.contains(preFix)) {
            return virtualNode.substring(0, virtualNode.indexOf("-"));
        } else if (!StringUtils.isEmpty(virtualNode)) {
            return virtualNode;
        }
        return null;
    }

    /**
     * 获取数据key临近下一个hash环上的节点
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
     * 使用FNV1_32_HASH算法/MurmurHash3算法计算key的Hash值
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
