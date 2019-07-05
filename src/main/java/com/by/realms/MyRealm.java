package com.by.realms;

import com.by.model.User;
import com.by.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Set;

/**
 * Created by gcq on 2019/7/3.
 */
@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = null;
        if(!StringUtils.isEmpty(username)){
            user = userService.findByName(username);

        }

        //Object principal, Object credentials, String realmName
        //用户名，密码，当前realm 名称
        SimpleAuthenticationInfo simpleAuthenticationInfo = null;
        if(user != null){
            String principal = user.getUserName();
            String credentials = user.getUserPswd();
            String realmName = getName();
            ByteSource credentialsSalt = ByteSource.Util.bytes(username);//盐值加密（“唯一”）
            simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal,credentials,credentialsSalt,realmName);
        }

        return simpleAuthenticationInfo;


    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String username = (String)principalCollection.getPrimaryPrincipal();

        Set<String> roles = userService.findRoleByUserName(username);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

        Set<String> permissions = userService.findPermissionByUserName(username);
        info.addStringPermissions(permissions);

        return info;
    }
}
