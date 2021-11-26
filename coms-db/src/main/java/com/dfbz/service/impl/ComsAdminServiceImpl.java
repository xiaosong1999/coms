package com.dfbz.service.impl;

import com.dfbz.dao.ComsAdminMapper;
import com.dfbz.domain.ComsAdmin;
import com.dfbz.domain.ComsAdminExample;
import com.dfbz.service.ComsAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ComsAdminServiceImpl implements ComsAdminService {

    @Resource
    private ComsAdminMapper adminMapper;

    /**
     * 根据username查询管理员信息
     *
     * @param username
     * @return
     */
    @Override
    public List<ComsAdmin> findAdmin(String username) {
        ComsAdminExample example = new ComsAdminExample();
        example.or().andUsernameEqualTo(username);
        return adminMapper.selectByExample(example);
    }

    /**
     * 根据id查询管理员信息
     *
     * @param id
     * @return
     */
    @Override
    public ComsAdmin findAdmin(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    /**
     * 管理员登录
     * 根据账号和密码查询用户
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public ComsAdmin login(String username, String password) {
        ComsAdminExample example = new ComsAdminExample();
        example.or().andUsernameEqualTo(username).andPasswordEqualTo(password);
        return adminMapper.selectOneByExample(example);
    }

    /**
     * 管理员修改信息
     * 根据管理员id进行修改该管理员提交的信息
     *
     * @param comsAdmin
     * @return
     */
    @Override
    public int updateById(ComsAdmin comsAdmin) {
        return adminMapper.updateByPrimaryKeySelective(comsAdmin);
    }

    /**
     * 获取管理员个数
     *
     * @return
     */
    @Override
    public long getAccount() {
        return adminMapper.countByExample(null);
    }

    /***
     * 展示所有管理员
     * @return
     */
    @Override
    public List<ComsAdmin> listAllAdmin() {
        return adminMapper.selectByExample(null);
    }

    /***
     * 添加管理员
     * @param comsAdmin
     * @return
     */
    @Override
    public int addAdmin(ComsAdmin comsAdmin) {
        return adminMapper.insert(comsAdmin);
    }

    /**
     * 根据name查询管理员信息
     *
     * @param name
     * @return
     */
    @Override
    public List<ComsAdmin> findAdminByName(String name) {
        ComsAdminExample example = new ComsAdminExample();
        example.or(example.createCriteria().andNameLike("%"+name+"%"));
        return adminMapper.selectByExample(example);
    }

    /***
     * 删除管理员
     * @param adminId
     * @return
     */
    @Override
    public int removeAdminById(Integer adminId) {
        int count = adminMapper.deleteByPrimaryKey(adminId);
        return count;
    }
}
