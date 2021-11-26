package com.dfbz.service;

import com.dfbz.domain.ComsAdmin;
import com.dfbz.domain.ComsOrder;
import com.dfbz.domain.ComsStall;
import com.dfbz.domain.ComsSupplier;

import java.util.List;

public interface MemberService {

    /**
     * 查询所有采购商
     * @return
     */
    List<ComsStall> getAllStall();

    /**
     * 根据名称对采购商进行模糊查询
     * @param name
     * @return
     */
    List<ComsStall> queryStallByName(String name);

    /**
     * 根据id查询stall
     * @param id
     * @return
     */
    ComsStall findStallById(Integer id);

    int removeStallById(Integer id);

//    int tranferStatus(Integer id);

    /**
     * 查询所有供应商
     * @return
     */
    List<ComsSupplier> getAllSup();

    /**
     * 查询所有管理员
     * @return
     */
    List<ComsAdmin> getAllAdmin();

    /**
     * 添加采购商
     * @param stall
     * @return
     */
    int addStall(ComsStall stall);

    /**
     * 编辑采购商
     * @param stall
     * @return
     */
    int editStall(ComsStall stall);

    int addSupplier(ComsSupplier supplier);

    int removeSupplier(ComsSupplier supplier);

    ComsSupplier findSupplierById(Integer supId);

    List<ComsSupplier> querySupByName(String supName);

    int editSupplier(ComsSupplier supplier);



}
