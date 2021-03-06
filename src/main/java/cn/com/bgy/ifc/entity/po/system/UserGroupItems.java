package cn.com.bgy.ifc.entity.po.system;

public class UserGroupItems {
    private Long id;

    private Long regionId;

    private Long projectId;

    private Long groupId;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserGroupItems{" +
                "id=" + id +
                ", regionId=" + regionId +
                ", projectId=" + projectId +
                ", groupId=" + groupId +
                ", userId=" + userId +
                '}';
    }
}