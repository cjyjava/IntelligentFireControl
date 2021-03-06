package cn.com.bgy.ifc.entity.po.maintenance;

import java.util.Date;
import java.util.List;

public class MaintenanceContract {
	 /**合同ID*/
    private Long id;
    /**机构id*/
    private Long orgId;
    /**机构名称*/
    private String orgName;
    /**区域ID*/
    private Long rId;
    /**区域名称*/
    private String rName;
    /**项目ID*/
    private Long pId;
    /**项目名称*/
    private String pName;
    /**合同名称*/
    private String contractName;
    /**合同编号*/
    private String contractNo;
    /**维保公司C_Id*/
    private Long companyId;
    /**维保公司名称*/
    private String companyName;
    /**主联系人*/
    private String masterContact;
    /**联系电话*/
    private String contactPhone;
    /**合同开始日期*/
    private Date startDate;
    /**合同结束日期*/
    private Date endDate;
    /**状态：0有效，1失效，2作废*/
    private Integer state;
    /**创建时间*/
    private Date createTime;
    /**是否逻辑删除*/
    private Boolean logicRemove;
    /**备注信息*/
    private String remark;
    //查询关键字
    private String keyword;

    public List<MaintenanceContractFile> fileList;

    public List<MaintenanceContractFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<MaintenanceContractFile> fileList) {
        this.fileList = fileList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getrId() {
        return rId;
    }

    public void setrId(Long rId) {
        this.rId = rId;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractNo() {
        return contractNo;
    }
    

    public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getMasterContact() {
        return masterContact;
    }

    public void setMasterContact(String masterContact) {
        this.masterContact = masterContact;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}