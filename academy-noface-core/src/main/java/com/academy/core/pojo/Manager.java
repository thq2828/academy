package com.academy.core.pojo;

public class Manager {

    private Long id;

    private String name;

    private String pwd;

    private Long roleId;

    private String status;

    private Long createBy;

    private Long updateBy;

    private Long updateAt;

    private Long createAt;

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

    public String getPwd() {
        return pwd;
    }


    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }


    public Long getRoleId() {
        return roleId;
    }


    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", roleId=" + roleId +
                ", status='" + status + '\'' +
                ", createBy=" + createBy +
                ", updateBy=" + updateBy +
                ", updateAt=" + updateAt +
                ", createAt=" + createAt +
                '}';
    }
}