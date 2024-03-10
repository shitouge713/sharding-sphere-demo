package com.study.shardingjdbc.suanfa;

import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * 查看应用的数据标识 ID
 */
public class ViewDataID {
    public static void main(String args[]) throws Exception {
        InetAddress inetAddress = InetAddress.getLocalHost();
        NetworkInterface network = NetworkInterface.getByInetAddress(inetAddress);
        long id = 0L;
        if (null == network) {
            id = 1L;
        } else {
            byte[] mac = network.getHardwareAddress();
            if (null != mac) {
                id = ((0x000000FF & (long) mac[mac.length - 2]) | (0x0000FF00 & (((long) mac[mac.length - 1]) << 8))) >> 6;
                id = id % (32);
            }
        }
        System.out.println(id);
    }
}
