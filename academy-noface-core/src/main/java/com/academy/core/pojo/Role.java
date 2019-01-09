package com.academy.core.pojo;

public class Role {

    private Long id;


    private String name;

    private String status;


    private Long createBy;


    private Long updateBy;


    private Long updateAt;


    private Long createAt;


    private String permissions;


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
        this.name = name == null ? null : name.trim();
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }


    public Long getCreateBy() {
        return createBy;
    }


    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }


    public Long getUpdateBy() {
        return updateBy;
    }


    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }


    public Long getUpdateAt() {
        return updateAt;
    }


    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }


    public Long getCreateAt() {
        return createAt;
    }


    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }


    public String getPermissions() {
        return permissions;
    }


    public void setPermissions(String permissions) {
        this.permissions = permissions == null ? null : permissions.trim();
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", createBy=" + createBy +
                ", updateBy=" + updateBy +
                ", updateAt=" + updateAt +
                ", createAt=" + createAt +
                ", permissions='" + permissions + '\'' +
                '}';
    }
}