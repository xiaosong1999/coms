package com.dfbz.service;

import com.dfbz.domain.ComsSupCategory;

import java.util.List;

public interface ComsSupCateService {
    /***
     * 产品id查供应商与分类
     */
    public Object findSupByProId(Integer id);


    /**
     * 供应商id查商品分类
     */
    public Object findCateBySup(Integer id);

    /**
     * 查询商品
     */
    public ComsSupCategory findById(Integer supCateId);

    /**
     *
     * @param cateId
     * @return
     */
    public List<ComsSupCategory> findByCategory(Integer cateId);

    /**
     * 查询所有
     * @return
     */
    public List<ComsSupCategory> findAll();

    /**
     * 根据供应商id与类别查询中间表
     * @param cateId
     * @param supId
     * @return
     */
    public ComsSupCategory findBySupIdAndCateId(Integer cateId,Integer supId);

    /**
     * 添加中间项
     * @param supCategory
     * @return
     */
    public int add(ComsSupCategory supCategory);

    int removeBySupId(Integer supId);
}
