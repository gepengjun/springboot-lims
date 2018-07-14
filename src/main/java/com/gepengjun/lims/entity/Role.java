package com.gepengjun.lims.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Role implements Serializable{

    private static final long serialVersionUID = -5141208259161252367L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;

    private String roleName;

    private String description;

    private Boolean available = Boolean.FALSE;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ref_role_user",joinColumns = {@JoinColumn(name = "roleId")},
                inverseJoinColumns = {@JoinColumn(name = "userId")})
    List<User> userList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ref_permission_role",joinColumns = {@JoinColumn(name = "roleId")},
                inverseJoinColumns = {@JoinColumn(name = "permissionId")})
    List<Permission> permissionList;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
