package cn.com.bgy.ifc.entity.vo.project;

import java.util.Date;

public class RegionInfoVo {
    /**
     * 集成平台Id
     */
    private Long id;

    /**
     * 机构表Id，用于区分平台
     */
    private Long organizationId;

    /**
     * 区域编码
     */
    private String code;

    /**
     * 区域名称
     */
    private String name;

    /**
     * 创建、修改时间
     */
    private Date createTime;

    
    /**
     * @Author huxin
     * @Description 查询关键字
     * @Date 2018/12/18 16:31
     */
    private String keyword;

    /**
     * 集成平台Id
     * @return id 集成平台Id
     */
    public Long getId() {
        return id;
    }

    /**
     * 集成平台Id
     * @param id 集成平台Id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 机构表Id，用于区分平台
     * @return organization_id 机构表Id，用于区分平台
     */
    public Long getOrganizationId() {
        return organizationId;
    }

    /**
     * 机构表Id，用于区分平台
     * @param organizationId 机构表Id，用于区分平台
     */
    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * 区域编码
     * @return code 区域编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 区域编码
     * @param code 区域编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 区域名称
     * @return name 区域名称
     */
    public String getName() {
        return name;
    }

    /**
     * 区域名称
     * @param name 区域名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 创建、修改时间
     * @return create_time 创建、修改时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建、修改时间
     * @param createTime 创建、修改时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}