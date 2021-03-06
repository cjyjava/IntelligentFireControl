package cn.com.bgy.ifc.entity.po.synchro;

import java.util.Date;

/**
 * @author: ZhangCheng
 * @description:碧桂园集成平台项目数据,用于数据同步
 * @date: 2019-01-22 10:07
 **/
public class BgyRegionProject {
    /**
     * ID
     */
    private Long id;
    /**
     * 平台ID
     */
    private Long organizationId;
    /**
     * 区域ID
     */
    private Long regionId;
    /**
     * 编码
     */
    private String code;
    /**
     * 项目名
     */
    private String name;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 归属地
     */
    private String ascription;
    /**
     * 创建、修改时间
     */
    private Date createTime;
    /**
     * 是否删除
     */
    private Boolean logicRemove;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAscription() {
        return ascription;
    }

    public void setAscription(String ascription) {
        this.ascription = ascription;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getLogicRemove() {
        return logicRemove;
    }

    public void setLogicRemove(Boolean logicRemove) {
        this.logicRemove = logicRemove;
    }
}
