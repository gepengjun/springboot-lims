package com.gepengjun.lims.config.shiro;

import com.gepengjun.lims.entity.Permission;
import com.gepengjun.lims.entity.Role;
import com.gepengjun.lims.entity.User;
import com.gepengjun.lims.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection collection) {
        System.out.println("权限配置--------doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User user = (User) collection
                .getPrimaryPrincipal();
        for (Role role : user.getRoleList()) {
            info.addRole(role.getRoleName());
            for (Permission permission : role.getPermissionList()) {
                info.addStringPermission(permission.getPermissionStr());
            }
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("登录验证--------AuthenticationInfo");
        String username = (String) token.getPrincipal();
        User user = userService.findByUsername(username);
        System.out.println("user--------"+user);
        if (user == null){
            return  null;
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),
                ByteSource.Util.bytes(user.getCreditialsSalt()),user.getRealName());
        System.out.println("info----------"+info);
        return info;
    }
}
