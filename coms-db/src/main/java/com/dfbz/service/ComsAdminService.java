package com.dfbz.service;

import com.dfbz.domain.ComsAdmin;

import java.util.List;

/**
 * 管理员的业务接口
 * @author 17352
 */
public interface ComsAdminService {

    /**
     * 根据username查询管理员信息
     * @param username
     * @return
     */
    public List<ComsAdmin> findAdmin(String username);

    /**
     * 根据id查询管理员信息
     * @param id
     * @return
     */
    public ComsAdmin findAdmin(Integer id);

    /**
     * 管理员登录
     * 根据账号和密码查询用户
     * @param username
     * @param password
     * @return
     */
    public ComsAdmin login(String username,String password);

    /**
     * 管理员修改信息
     * 根据管理员id进行修改该管理员提交的信息
     * @param comsAdmin
     * @return
     */
    public int updateById(ComsAdmin comsAdmin);

    /**
     * 获取管理员个数
     * @return
     */
    public long getAccount();


    /***
     * 显示所有管理员
     * @return
     */
    List<ComsAdmin> listAllAdmin();


    /**
     * 添加管理员
     */
    public int addAdmin(ComsAdmin comsAdmin);

    /**
     * 根据name查找admin
     * @param name
     * @return
     */
    public List<ComsAdmin> findAdminByName(String name);

    /**
     * 删除管理员
     */
    public int removeAdminById(Integer adminId);
}
