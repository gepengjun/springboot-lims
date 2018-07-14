package com.gepengjun.lims.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Permission implements Serializable{

    private static final long serialVersionUID = -3011393100721672782L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long permissionId;

    private String name;

    private String sourceType;

    private String permissionStr;

    private String url;

    private Long parentId;

    private String parentIds;

    private Boolean available = Boolean.FALSE;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ref_permission_role",joinColumns = {@JoinColumn(name = "permissionId")},
                inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<Role> roleList;

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermissionStr() {
        return permissionStr;
    }

    public void setPermissionStr(String permissionStr) {
        this.permissionStr = permissionStr;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
