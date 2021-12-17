package com.dfbz.web;

import com.alibaba.fastjson.JSONObject;
import com.dfbz.domain.ComsAdmin;
import com.dfbz.service.ComsAdminService;
import com.dfbz.util.ResponseUtil;
import com.dfbz.util.bcrypt.BCryptPasswordEncoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.annotation.Resource;

import static com.dfbz.util.AdminResponseCode.ADMIN_INVALID_ACCOUNT;


@RestController
@RequestMapping("/admin/auth")
@Validated
public class AdminAuthController {
    private final Log logger = LogFactory.getLog(com.dfbz.web.AdminAuthController.class);

    @Resource
    private ComsAdminService adminService;

    /***
     * { username : value, password : value }
     * @param username 用户名
     * @param password 密码
     * @return ok登陆成功/fail失败
     */
    @PostMapping("/login")
    public Object login(String username,String password) {
        System.out.println("username:"+username+",password:"+password);
        if (username.isEmpty() || password.isEmpty()) {
            return ResponseUtil.badArgument();
        }

        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(new UsernamePasswordToken(username, password));
            currentUser.login(new UsernamePasswordToken(username, password));
        } catch (UnknownAccountException uae) {
            return ResponseUtil.fail(ADMIN_INVALID_ACCOUNT, "用户帐号或密码不正确");
        } catch (LockedAccountException lae) {
            return ResponseUtil.fail(ADMIN_INVALID_ACCOUNT, "用户帐号已锁定不可用");

        } catch (AuthenticationException ae) {
            return ResponseUtil.fail(ADMIN_INVALID_ACCOUNT, "认证失败");
        }
        return ResponseUtil.ok(currentUser.getSession().getId());
    }

    @RequestMapping("/editProfile")
    public Object editProfile(){
        ModelAndView mv = new ModelAndView("edit_profile");
        ComsAdmin admin = (ComsAdmin) SecurityUtils.getSubject().getPrincipal();
        mv.addObject("admin",admin);
        return mv;
    }

    @RequestMapping("/editPfe")
    public Object editPfe(@RequestBody ComsAdmin adminMsg){
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principalCollection = subject.getPrincipals();
        String realmName = principalCollection.getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(adminMsg, realmName);
        subject.runAs(newPrincipalCollection);
        int result = adminService.updateById(adminMsg);
        if(result>0){
            return ResponseUtil.ok();
        }
        return ResponseUtil.busy();
    }

    @RequestMapping("/editPassword")
    public Object editPassword(){
        ModelAndView mv = new ModelAndView("edit_password");
        ComsAdmin admin = (ComsAdmin) SecurityUtils.getSubject().getPrincipal();
        mv.addObject("admin",admin);
        return mv;
    }

    @RequestMapping("/editPwd")
    public Object editPwd(@RequestBody JSONObject jsonParam){
        String original = jsonParam.get("original").toString();
        String password = jsonParam.get("password").toString();
        ComsAdmin admin = (ComsAdmin) SecurityUtils.getSubject().getPrincipal();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(!encoder.matches(original,admin.getPassword())){
            return ResponseUtil.fail(-5,"初始密码错误，请重新输入");
        }
        admin.setPassword(encoder.encode(password));
        adminService.updateById(admin);
        return ResponseUtil.ok();
    }


    @RequiresAuthentication
    @GetMapping("/logout")
    public Object logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("errmsg","");
        return mv;
    }

    @GetMapping("/401")
    public Object page401() {
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("errmsg","");
        return mv;
    }

    @GetMapping("/index")
    public Object pageIndex() {
        ModelAndView mv = new ModelAndView("index");
        ComsAdmin admin = (ComsAdmin)SecurityUtils.getSubject().getPrincipal();
        mv.addObject("admin",admin);
        return mv;
    }

    @GetMapping("/403")
    public Object page403() {
        return ResponseUtil.unauthz();
    }
}
