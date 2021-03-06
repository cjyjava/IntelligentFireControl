package cn.com.bgy.ifc.entity.po.alarm;

import cn.com.bgy.ifc.entity.po.project.RegionInfo;
import cn.com.bgy.ifc.entity.po.project.RegionProject;
import cn.com.bgy.ifc.entity.po.system.Account;

import java.util.Date;

public class AlarmObject {
    /**
     * ID
     */
    private Long id;

    /**
     * 所属区域ID
     */
    private Long regionId;

    /**
     * 所属项目ID
     */
    private Long projectId;

    /**
     * 告警通知人ID
     */
    private Long userId;


    /**
     * 联系方式
     */
    private String contactWay;

    /**
     * 启用状态 禁用-0 启用-1
     */
    private Integer state;

    /**
     * 短信通知
     */
    private Boolean smsNotice;

    /**
     * 语音电话
     */
    private Boolean voiceCall;

    /**
     * APP通知
     */
    private Boolean appNotice;

    /**
     * 创建、修改时间
     */
    private Date createTime;

    /**
     * 是否逻辑删除
     */
    private Boolean logicRemove;

    /**
     * 所属区域
     */
    private RegionInfo regionInfo;

    /**
     * 所属项目
     */
    private RegionProject regionProject;

    /**
     * 成员
     */
    private Account account;

    /**
     * ID
     * @return id ID
     */
    public Long getId() {
        return id;
    }

    /**
     * ID
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 所属项目ID
     * @return project_id 所属项目ID
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * 所属项目ID
     * @param projectId 所属项目ID
     */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    /**
     * 告警通知人ID
     * @return user_id 告警通知人ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 告警通知人ID
     * @param userId 告警通知人ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }


    /**
     * 联系方式
     * @return contact_way 联系方式
     */
    public String getContactWay() {
        return contactWay;
    }

    /**
     * 联系方式
     * @param contactWay 联系方式
     */
    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    /**
     * 启用状态 禁用-0 启用-1
     * @return state 启用状态 禁用-0 启用-1
     */
    public Integer getState() {
        return state;
    }

    /**
     * 启用状态 禁用-0 启用-1
     * @param state 启用状态 禁用-0 启用-1
     */
    public void setState(Integer state) {
        this.state = state;
    }

    public Boolean getSmsNotice() {
        return smsNotice;
    }

    public void setSmsNotice(Boolean smsNotice) {
        this.smsNotice = smsNotice;
    }

    public Boolean getVoiceCall() {
        return voiceCall;
    }

    public void setVoiceCall(Boolean voiceCall) {
        this.voiceCall = voiceCall;
    }

    public Boolean getAppNotice() {
        return appNotice;
    }

    public void setAppNotice(Boolean appNotice) {
        this.appNotice = appNotice;
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

    /**
     * 是否逻辑删除
     * @return logic_remove 是否逻辑删除
     */
    public Boolean getLogicRemove() {
        return logicRemove;
    }

    /**
     * 是否逻辑删除
     * @param logicRemove 是否逻辑删除
     */
    public void setLogicRemove(Boolean logicRemove) {
        this.logicRemove = logicRemove;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public RegionInfo getRegionInfo() {
        return regionInfo;
    }

    public void setRegionInfo(RegionInfo regionInfo) {
        this.regionInfo = regionInfo;
    }

    public RegionProject getRegionProject() {
        return regionProject;
    }

    public void setRegionProject(RegionProject regionProject) {
        this.regionProject = regionProject;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}