package com.dfbz.service;

import com.dfbz.domain.ComsSupplier;
import com.github.pagehelper.Page;

import java.util.List;

public interface ComsSupplierService {
    /***
     * 供应商登陆验证
     * @param username
     * @param password
     * @return
     */
    public ComsSupplier login(String username, String password);

    /***
     * 供应商状态修改
     * @param id
     * @param status
     * @return
     */
    public Integer editStatus(Integer id,Integer status);

    /***
     * 显示所有供应商
     * @return
     */
    public List<ComsSupplier> listAllSupplier();

    /***
     * 按id查找供应商
     */
    public ComsSupplier findById(Integer id);

    /***
     * 添加供应商
     * @param supplier
     * @return
     */
    public Integer add(ComsSupplier supplier);


    /***
     * 修改供应商
     * @param supplier
     * @return
     */
    public Integer update(ComsSupplier supplier);

    /***
     * 删除供应商
     * @param supplier
     * @return
     */
    public Integer delete(ComsSupplier supplier);

    /**
     * 获取总数
     * @return
     */
    public long getAcount();

    List<ComsSupplier> queryByName(String supName);
}
