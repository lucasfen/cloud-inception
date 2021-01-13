package com.lucas.util;

import java.net.InetAddress;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * @author: lucasfen
 * @create: 2021/01/11
 */
@Slf4j
public class IotUtil {

    public static String getIpAddrFromReq(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if ((!StringUtils.hasText(ip)) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if ((!StringUtils.hasText(ip)) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((!StringUtils.hasText(ip)) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1")) {
                try {
                    //根据网卡取本机配置的IP
                    InetAddress inet = InetAddress.getLocalHost();
                    ip = inet.getHostAddress();
                } catch (Exception e) {
                    log.error("failed to get localHost", e);
                }
            }
        }
        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }
}
