package cn.com.bgy.ifc.entity.vo.system;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
/**
 * 机构实体
 */
public class SystemOrganizationVo {
    /**
     * 系统自增Id
     */
    private Long id;

    /**
     * 机构编码
     */
    private String code;

    /**
     * 机构名称
     */
    @NotBlank(message="机构名称不能为空！")
    @Size(max=50,message="机构名称长度不能超过50字符！")
    private String name;

    /**
     * 状态，0表示禁用；1表示启用
     */
    private Integer state;

    /**
     * 机构管理员，用户表Id
     */
    @NotNull(message="机构管理员不能为空！")
    private Long userId;

    /**
     * 创建、修改时间
     */
    private Date createTime;

    /**
     * 注册时间(yyyy-mm-dd HH:mm:ss)
     */
    private Date registerTime;

    /**
     * 是否逻辑删除
     */
    private Boolean logicRemove;

    /**
     * 系统自增Id
     * @return id 系统自增Id
     */
    public Long getId() {
        return id;
    }

    /**
     * 系统自增Id
     * @param id 系统自增Id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 机构名称
     * @return name 机构名称
     */
    public String getName() {
        return name;
    }

    /**
     * 机构名称
     * @param name 机构名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 状态，0表示禁用；1表示启用
     * @return state 状态，0表示禁用；1表示启用
     */
    public Integer getState() {
        return state;
    }

    /**
     * 状态，0表示禁用；1表示启用
     * @param state 状态，0表示禁用；1表示启用
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 机构管理员，用户表Id
     * @return user_id 机构管理员，用户表Id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 机构管理员，用户表Id
     * @param userId 机构管理员，用户表Id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }
}