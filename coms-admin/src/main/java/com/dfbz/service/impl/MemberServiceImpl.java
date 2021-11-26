package com.dfbz.service.impl;

import com.dfbz.domain.ComsAdmin;
import com.dfbz.domain.ComsStall;
import com.dfbz.domain.ComsSupplier;
import com.dfbz.service.ComsAdminService;
import com.dfbz.service.ComsStallService;
import com.dfbz.service.ComsSupplierService;
import com.dfbz.service.MemberService;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    private ComsAdminService adminService;

    @Resource
    private ComsStallService stallService;

    @Resource
    private ComsSupplierService supplierService;

    /**
     * 查询所有供应商
     *
     * @return
     */
    @Override
    public List<ComsSupplier> getAllSup() {
        return supplierService.listAllSupplier();
    }

    /**
     * 查询所有采购商
     *
     * @return
     */
    @Override
    public List<ComsStall> getAllStall() {
        return stallService.listAllStall();
    }

    /**
     * 查询所有管理员
     *
     * @return
     */
    @Override
    public List<ComsAdmin> getAllAdmin() {
        return adminService.listAllAdmin();
    }

    @Override
    public int addStall(ComsStall stall) {
        return stallService.add(stall);
    }

    @Override
    public int editStall(ComsStall stall) {
        return stallService.edit(stall);
    }

    @Override
    public int addSupplier(ComsSupplier supplier) {
        supplier.setCreateTime(LocalDateTime.now());
        supplier.setUpdateTime(LocalDateTime.now());
        return supplierService.add(supplier);
    }

    @Override
    public int removeSupplier(ComsSupplier supplier) {
        return supplierService.delete(supplier);
    }

    @Override
    public ComsSupplier findSupplierById(Integer supId) {
        return supplierService.findById(supId);
    }

    @Override
    public List<ComsSupplier> querySupByName(String supName) {
        return supplierService.queryByName(supName);
    }

    @Override
    public int editSupplier(ComsSupplier supplier) {
        return supplierService.update(supplier);
    }

    /**
     * 根据名称对档口人员进行模糊查询
     * @param name
     * @return
     */
    @Override
    public List<ComsStall> queryStallByName(String name) {
        return stallService.queryByName(name);
    }

    /**
     * 删除采购商信息
     * @param id
     * @return
     */
    @Override
    public int removeStallById(Integer id) {
        return stallService.removeById(id);
    }

    public ComsStall findStallById(Integer id){
        return stallService.findById(id);
    }

}
