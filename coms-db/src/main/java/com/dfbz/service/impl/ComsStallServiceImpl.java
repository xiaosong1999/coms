package com.dfbz.service.impl;

import com.dfbz.dao.ComsStallMapper;
import com.dfbz.domain.ComsStall;
import com.dfbz.domain.ComsStallExample;
import com.dfbz.domain.ComsSupplierExample;
import com.dfbz.service.ComsStallService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComsStallServiceImpl implements ComsStallService {

    @Resource
    ComsStallMapper comsStallMapper;

    /***
     * 显示所有档口信息
     * @return
     */
    @Override
    public List<ComsStall> listAllStall() {
        ComsStallExample example = new ComsStallExample();
        example.or().andNameIsNotNull();
        return comsStallMapper.selectByExample(example);
    }

    /***
     * 档口帐号状态
     * @param id
     * @param statusId
     * @return
     */
    @Override
    public Integer editStatus(Integer id, Integer statusId) {
        ComsStall stall = new ComsStall();
        stall.setStatus(statusId);
        ComsStallExample example = new ComsStallExample();
        example.or().andIdEqualTo(id);

        return comsStallMapper.updateByExampleSelective(stall, example);
    }

    /***
     * 按id查档口信息
     * @param id
     * @return
     */
    @Override
    public ComsStall findById(Integer id) {
        ComsStallExample example = new ComsStallExample();
        example.or().andIdEqualTo(id);
        return comsStallMapper.selectOneByExample(example);
    }

    @Override
    public ComsStall selectOneByPhone(String phone) {
        ComsStallExample example = new ComsStallExample();
        example.or().andPhoneEqualTo(phone);
        return comsStallMapper.selectOneByExample(example);
    }

    @Override
    public long getAcount() {
        return comsStallMapper.countByExample(new ComsStallExample());
    }

    @Override
    public List<ComsStall> queryByName(String name) {
        ComsStallExample example = new ComsStallExample();
        example.or().andNameLike("%"+name+"%");
        return comsStallMapper.selectByExample(example);
    }

    @Override
    public int removeById(Integer id) {
        return comsStallMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int add(ComsStall stall) {
        stall.setCreateTime(LocalDateTime.now());
        stall.setUpdateTime(LocalDateTime.now());
        return comsStallMapper.insert(stall);
    }

    @Override
    public int edit(ComsStall stall) {
        stall.setUpdateTime(LocalDateTime.now());
        return comsStallMapper.updateByPrimaryKeySelective(stall);
    }

    /**
     * 第二版
     */

    @Override
    public List<ComsStall> findByPhone(ComsStallExample example) {
        return comsStallMapper.selectByExample(example);
    }


}
