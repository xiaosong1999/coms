package com.dfbz.service;

import com.dfbz.domain.ComsStall;
import com.dfbz.domain.ComsStallExample;

import java.util.List;

public interface ComsStallService {
    /**
     * 显示所有档口信息
     * @return
     */
    public List<ComsStall> listAllStall();

    /***
     * 编辑档口状态
     * @param id
     * @param statusId
     * @return
     */
    public Integer editStatus(Integer id,Integer statusId);

    /***
     * 按id查档口信息
     * @param id
     * @return
     */
    public ComsStall findById(Integer id);

    ComsStall selectOneByPhone(String phone);

    /**
     * 获取总数
     * @return
     */
    public long getAcount();

    List<ComsStall> queryByName(String name);

    int removeById(Integer id);

    int add(ComsStall stall);

    int edit(ComsStall stall);

    List<ComsStall> findByPhone(ComsStallExample example);
}
