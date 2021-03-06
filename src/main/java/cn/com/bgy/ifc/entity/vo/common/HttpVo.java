package cn.com.bgy.ifc.entity.vo.common;

import java.io.Serializable;
import java.util.Map;

/**
 * @author: ZhangCheng
 * @description:请求VO
 * @date: 2018-12-18 09:16
 **/
public class HttpVo {


    /**
     * 请求地址
     */
    private String url;

    /**
     * 请求头部
     */
    private Map<String, Object> headerMap;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getHeaderMap() {
        return headerMap;
    }

    public void setHeaderMap(Map<String, Object> headerMap) {
        this.headerMap = headerMap;
    }


}
