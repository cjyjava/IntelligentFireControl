package cn.com.bgy.ifc.entity.vo.fireinspection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author: ZhangCheng
 * @description:
 * @date: 2019-01-04 15:05
 **/
public class FireTaskTrainVo {

    private Long id;

    /**
     * 消防培训任务名称
     */
    @NotBlank(message = "消防培训任务名称不能为空！")
    @Size(max = 50, message = "消防培训任务名称长度不能超过50字符！")
    private String name;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 人数
     */
    private Integer number;

    /**
     * 培训对象
     */
    @Size(max = 50, message = "培训对象长度不能超过50字符！")
    private String trainingObject;

    /**
     * 消防培训结果
     */
    private String trainResults;

    /**
     * 培训部门
     */
    @Size(max = 50, message = "培训部门长度不能超过50字符！")
    private String department;

    /**
     * 合格率
     */
    private Integer passRate;

    /**
     * 区域ID
     */
    @NotNull(message="所属区域不能为空！")
    private Long regionId;

    /**
     * 培训内容
     */
    private String content;


    /**
     * 任务周期
     */
    @NotNull(message="任务周期不能为空！")
    private Integer planCycle;

    /**
     * 工作状态 未处理-1、处理中-2、已完成-3
     */
    @NotNull(message="工作状态不能为空！")
    private Integer complete;

    /**
     * 创建、修改时间
     */
    private Date createTime;

    /**
     * 是否逻辑删除
     */
    private Boolean logicRemove;

    /**
     * 开始日期时间
     */
    private String startStr;

    /**
     * 结束日期时间
     */
    private String endStr;

    /**
     * 关键字
     */
    private String keywords;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTrainingObject() {
        return trainingObject;
    }

    public void setTrainingObject(String trainingObject) {
        this.trainingObject = trainingObject;
    }

    public String getTrainResults() {
        return trainResults;
    }

    public void setTrainResults(String trainResults) {
        this.trainResults = trainResults;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getPassRate() {
        return passRate;
    }

    public void setPassRate(Integer passRate) {
        this.passRate = passRate;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPlanCycle() {
        return planCycle;
    }

    public void setPlanCycle(Integer planCycle) {
        this.planCycle = planCycle;
    }

    public Integer getComplete() {
        return complete;
    }

    public void setComplete(Integer complete) {
        this.complete = complete;
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

    public String getStartStr() {
        return startStr;
    }

    public void setStartStr(String startStr) {
        this.startStr = startStr;
    }

    public String getEndStr() {
        return endStr;
    }

    public void setEndStr(String endStr) {
        this.endStr = endStr;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
