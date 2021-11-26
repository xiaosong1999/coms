package com.dfbz.service.impl;

import com.dfbz.dao.ComsSupplierMapper;
import com.dfbz.domain.ComsStallExample;
import com.dfbz.domain.ComsSupplier;
import com.dfbz.domain.ComsSupplierExample;
import com.dfbz.service.ComsSupplierService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ComsSupplierServiceImpl implements ComsSupplierService {
    @Resource
    private ComsSupplierMapper supplierMapper;

    /***
     * 登陆实现
     * @param username
     * @param password
     * @return
     */
    @Override
    public ComsSupplier login(String username, String password) {
        ComsSupplierExample example = new ComsSupplierExample();
        example.or().andPhoneEqualTo(username).andPasswordEqualTo(password);
        return supplierMapper.selectOneByExample(example);
    }

    /***
     * 供应商账号状态
     * @param id
     * @param status
     * @return
     */
    @Override
    public Integer editStatus(Integer id, Integer status) {
        ComsSupplier supplier = new ComsSupplier();
        supplier.setStatus(status);
        ComsSupplierExample example = new ComsSupplierExample();
        example.or().andIdEqualTo(id);
        return supplierMapper.updateByExampleSelective(supplier, example);
    }

    /***
     * 显示所有供应商
     * @return
     */
    @Override
    public List<ComsSupplier> listAllSupplier() {
        ComsSupplierExample example = new ComsSupplierExample();
        example.or().andNameIsNotNull();
        return supplierMapper.selectByExample(example);
    }


    /***
     * 按id查供应商
     * @param id
     * @return
     */
    @Override
    public ComsSupplier findById(Integer id) {
        ComsSupplierExample example = new ComsSupplierExample();
        example.or().andIdEqualTo(id);
        return supplierMapper.selectOneByExample(example);
    }

    /***
     * 添加供应商
     * @param supplier
     * @return
     */
    @Override
    public Integer add(ComsSupplier supplier) {
        return supplierMapper.insert(supplier);
    }

    @Override
    public Integer update(ComsSupplier supplier) {
        return supplierMapper.updateByPrimaryKeySelective(supplier);
    }

    @Override
    public Integer delete(ComsSupplier supplier) {

        return supplierMapper.deleteByPrimaryKey(supplier.getId());
    }

    @Override
    public long getAcount() {
        return supplierMapper.countByExample(new ComsSupplierExample());
    }

    @Override
    public List<ComsSupplier> queryByName(String supName) {
        ComsSupplierExample example = new ComsSupplierExample();
        example.or().andNameLike("%"+supName+"%");
        return supplierMapper.selectByExample(example);
    }
}
