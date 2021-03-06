package cn.com.bgy.ifc.bgy.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: ZhangCheng
 * @description:IP地址转化
 * @date: 2019-01-05 10:19
 **/
public class IpUtil {

    /**
     * @author: ZhangCheng
     * @description:获取IP地址
     * @param: [request]
     * @return: java.lang.String
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        String unknown="unknown";
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
