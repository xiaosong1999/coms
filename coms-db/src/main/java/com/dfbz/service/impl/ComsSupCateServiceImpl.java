package com.dfbz.service.impl;

import com.dfbz.dao.ComsCategoryMapper;
import com.dfbz.dao.ComsSupCategoryMapper;
import com.dfbz.dao.ComsSupplierMapper;
import com.dfbz.domain.*;
import com.dfbz.service.ComsCategoryService;
import com.dfbz.service.ComsProductService;
import com.dfbz.service.ComsSupCateService;
import com.dfbz.service.ComsSupplierService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ComsSupCateServiceImpl implements ComsSupCateService {

    @Resource
    ComsSupCategoryMapper supCategoryMapper;

    @Resource
    ComsCategoryService categoryService;

    @Resource
    ComsSupplierService supplierService;

    @Resource
    ComsProductService productService;


    /***
     * 产品id查供应商与分类
     * @param id
     * @return
     */
    @Override
    public Object findSupByProId(Integer id) {
        //商品表查供应商id
        ComsProduct product = productService.findById(id);
        ComsSupCategoryExample example = new ComsSupCategoryExample();
        example.or().andIdEqualTo(product.getSupCateId());
        //sup_cate表查询分类和供应商
        ComsSupCategory supCategory = supCategoryMapper.selectOneByExample(example);
        //sup表和cate表查对应
        ComsSupplier supplier = supplierService.findById(supCategory.getSupId());
        ComsCategory category = categoryService.findById(supCategory.getCateId());
        ComsCategory cate_par = categoryService.findById(category.getParentId());
        //put进map返回
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("pro_id", id);
        data.put("pro_name", product.getName());
        data.put("cate_id", supCategory.getCateId());
        data.put("cate_name", category.getName());
        data.put("cate_par_id", category.getParentId());
        data.put("cate_par_name", cate_par.getName());
        data.put("sup_id", supplier.getId());
        data.put("sup_name", supplier.getName());
        ComsSupCategoryExample example1 = new ComsSupCategoryExample();
        return data;
        /*//商品id查供应商id与分类id
        ComsSupCategoryExample example=new ComsSupCategoryExample();
        example.or().andIdEqualTo(id);
        ComsSupCategory supCategory = supCategoryMapper.selectOneByExample(example);
        //分类子id查询
        ComsCategory cate = categoryService.findById(supCategory.getCateId());
        ComsCategory par_cate = categoryService.findById(cate.getParentId());
        //供应商id查询
        ComsSupplier sup = supplierService.findById(supCategory.getSupId());

        Map<String,Object> data=new LinkedHashMap<>();
        data.put("pro_id",id);
        data.put("pro_name",productService.findById(id).getName());
        data.put("cate_id",supCategory.getSupId());
        data.put("cate_name",cate.getName());
        data.put("cate_par_id",cate.getParentId());
        data.put("cate_par_name",par_cate.getName());
        data.put("sup_id",sup.getId());
        data.put("sup_name",sup.getName());
*/

    }

    /**
     * @param id
     */
    @Override
    public List<ComsSupCategory> findCateBySup(Integer id) {
        //获取供应商详细信息
        ComsSupplier supplier = supplierService.findById(id);
        //supplier_id查分类
        ComsSupCategoryExample supCategoryExample = new ComsSupCategoryExample();
        supCategoryExample.or().andSupIdEqualTo(id);
        return supCategoryMapper.selectByExample(supCategoryExample);
    }

    /**
     * 查询商品
     *
     * @param supCateId
     */
    @Override
    public ComsSupCategory findById(Integer supCateId) {
        return supCategoryMapper.selectByPrimaryKey(supCateId);
    }

    @Override
    public List<ComsSupCategory> findByCategory(Integer cateId) {
        ComsSupCategoryExample example = new ComsSupCategoryExample();
        example.or().andCateIdEqualTo(cateId);
        return supCategoryMapper.selectByExample(example);
    }

    @Override
    public List<ComsSupCategory> findAll() {
        return supCategoryMapper.selectByExample(null);
    }

    @Override
    public ComsSupCategory findBySupIdAndCateId(Integer cateId, Integer supId) {
        ComsSupCategoryExample example = new ComsSupCategoryExample();
        example.or().andSupIdEqualTo(supId).andCateIdEqualTo(cateId);
        return supCategoryMapper.selectOneByExample(example);
    }

    @Override
    public int add(ComsSupCategory supCategory) {
        return supCategoryMapper.insertSelective(supCategory);
    }

    @Override
    public int removeBySupId(Integer supId) {
        ComsSupCategoryExample example = new ComsSupCategoryExample();
        example.or().andSupIdEqualTo(supId);
        return supCategoryMapper.deleteByExample(example);
    }


}
