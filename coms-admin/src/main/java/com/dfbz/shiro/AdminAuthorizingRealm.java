package com.dfbz.shiro;


import com.dfbz.domain.ComsAdmin;
import com.dfbz.service.ComsAdminService;
import com.dfbz.util.ComsRole;
import com.dfbz.util.bcrypt.BCryptPasswordEncoder;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdminAuthorizingRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(AdminAuthorizingRealm.class);
    @Autowired
    private ComsAdminService adminService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        ComsAdmin admin = (ComsAdmin) getAvailablePrincipal(principals);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set set = new HashSet<>();
        if(admin.getType()==1){
            set.add(ComsRole.ADMIN_L1);
        }else{
            set.add(ComsRole.ADMIN_L2);
        }
        info.setRoles(set);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password=new String(upToken.getPassword());

        if (StringUtils.isEmpty(username)) {
            throw new AccountException("用户名不能为空");
        }
        if (StringUtils.isEmpty(password)) {
            throw new AccountException("密码不能为空");
        }

        List<ComsAdmin> adminList = adminService.findAdmin(username);
        Assert.state(adminList.size() < 2, "同一个用户名存在两个账户");
        if (adminList.size() == 0) {
            throw new UnknownAccountException("找不到用户（"+username+"）的帐号信息");
        }
        ComsAdmin admin = adminList.get(0);


        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("password:"+encoder.encode(admin.getPassword()));
        if (!encoder.matches(password, admin.getPassword())) {
            throw new UnknownAccountException("找不到用户（"+username+"）的帐号信息");
        }

        return new SimpleAuthenticationInfo(admin,password,getName());
    }

}
