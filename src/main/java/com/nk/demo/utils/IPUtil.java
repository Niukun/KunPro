package com.nk.demo.utils;

import java.net.*;
import java.util.Enumeration;

public class IPUtil {


    public static final String DEFAULT_IP = "127.0.0.1";

    /**
     * 直接根据第⼀个⽹卡地址作为其内⽹ipv4地址，避免返回 127.0.0.1
     *
     * @return 第⼀个符合条件的内⽹ IPv4 地址
     */
    public static String getLocalIpByNetcard() {
        try {
            // 枚举所有的⽹络接⼝
            for (Enumeration<NetworkInterface> e =
                 NetworkInterface.getNetworkInterfaces(); e.hasMoreElements(); ) {
                // 获取当前⽹络接⼝
                NetworkInterface item = e.nextElement();

                // 遍历当前⽹络接⼝的所有地址
                for (InterfaceAddress address :
                        item.getInterfaceAddresses()) {
                    // 忽略回环地址和未启⽤的⽹络接⼝
                    if (item.isLoopback() || !item.isUp()) {
                        continue;
                    }
                    // 如果当前地址是 IPv4 地址，则返回其字符串表示
                    if (address.getAddress() instanceof Inet4Address) {
                        Inet4Address inet4Address = (Inet4Address)
                                address.getAddress();
                        return inet4Address.getHostAddress();
                    }
                }
            }
            // 如果没有找到符合条件的地址，则返回本地主机地址
            return InetAddress.getLocalHost().getHostAddress();
        } catch (SocketException | UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取本地主机地址
     *
     * @return 本地主机地址
     */
    public static String getLocalIP() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(IPUtil.getLocalIP());
        System.out.println(IPUtil.getLocalIpByNetcard());
    }

}
