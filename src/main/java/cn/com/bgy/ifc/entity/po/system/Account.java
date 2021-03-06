package cn.com.bgy.ifc.entity.po.system;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class Account {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 机构id
     */
    private Long organizationId;
    /**
     * 部门id
     */
    private Long departmentId;

    private String departmentName;

    /**
     * 电话号码
     */
    private String telephone;
    /**
     * 工号
     */
    private String jobNumber;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 密码盐
     */
    private String passwordSalt;
    /**
     * 身份证号
     */
    private String identityNumber;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 注册日期
     */
    //@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registTime;

    private Date registTimeStart;

    private Date registTimeEnd;

    /**
     * 第三方用户ID
     */
    private Long thirdUserId;

    /**
     * 角色ID
     */
    private Long roleId;

    private SystemRole systemRole;

    /**
     * 是否禁用0启用；1禁用
     */
    private Integer isDisable;
    /**
     * 登陆ip
     */
    private String currentIp;
    /**
     * 登陆时间
     */
    private Date currentTime;
    /**
     * 最后一次登陆ip
     */
    private String lastIp;
    /**
     * 最后一次登陆时间
     */
    private Date lastTime;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 备注
     */
    private String remark;

    private Integer hasRole;

    private String keyWords;

    private List<SystemRole> roleList;

    private List<SystemPower> powerList;

    /**
     * 菜单权限列表
     */
    private List<SystemMenu> menuPermission;
    /**
     * 权限列表字符串
     */
    private String powerListStr;



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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public Date getRegistTimeStart() {
        return registTimeStart;
    }

    public void setRegistTimeStart(Date registTimeStart) {
        this.registTimeStart = registTimeStart;
    }

    public Date getRegistTimeEnd() {
        return registTimeEnd;
    }

    public void setRegistTimeEnd(Date registTimeEnd) {
        this.registTimeEnd = registTimeEnd;
    }

    public Long getThirdUserId() {
        return thirdUserId;
    }

    public void setThirdUserId(Long thirdUserId) {
        this.thirdUserId = thirdUserId;
    }

    public Integer getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(Integer isDisable) {
        this.isDisable = isDisable;
    }

    public String getCurrentIp() {
        return currentIp;
    }

    public void setCurrentIp(String currentIp) {
        this.currentIp = currentIp;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getHasRole() {
        return hasRole;
    }

    public void setHasRole(Integer hasRole) {
        this.hasRole = hasRole;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public List<SystemRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SystemRole> roleList) {
        this.roleList = roleList;
    }

    public List<SystemPower> getPowerList() {
        return powerList;
    }

    public void setPowerList(List<SystemPower> powerList) {
        this.powerList = powerList;
    }

    public String getPowerListStr() {
        return powerListStr;
    }

    public void setPowerListStr(String powerListStr) {
        this.powerListStr = powerListStr;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public SystemRole getSystemRole() {
        return systemRole;
    }

    public void setSystemRole(SystemRole systemRole) {
        this.systemRole = systemRole;
    }

    public List<SystemMenu> getMenuPermission() {
        return menuPermission;
    }

    public void setMenuPermission(List<SystemMenu> menuPermission) {
        this.menuPermission = menuPermission;
    }
}