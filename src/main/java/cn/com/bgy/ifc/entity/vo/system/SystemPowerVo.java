package cn.com.bgy.ifc.entity.vo.system;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SystemPowerVo {

	/**
	 *系统自增Id
	 */
	private Long id;

	/**
	 *权限名称
	 */
	@NotBlank(message="权限名称不能为空！")
	@Size(max=50,message="权限名称长度不能超过50字符！")
	private String name;

	/**
	 *权限展示名称
	 */
	@NotBlank(message="权限展示名称不能为空！")
	@Size(max=50,message="权限展示名称长度不能超过50字符！")
	private String displayName;

	/**
	 *备注
	 */
	private String remark;

	/**
	 *所属模块Id
	 */
	@NotNull(message="所属模块不能为空！")
	private Long moduleId;

	/**
	 *是否逻辑删除
	 */
	private Boolean logicRemove;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getLogicRemove() {
		return logicRemove;
	}

	public void setLogicRemove(Boolean logicRemove) {
		this.logicRemove = logicRemove;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}
}