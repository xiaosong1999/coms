package com.dfbz.service;


import com.dfbz.domain.ComsCategory;

import java.util.List;

/**
 * 类别的业务接口
 */
public interface ComsCategoryService {
    /**
     * 查询所有
     * @return
     */
     public List<ComsCategory> queryAll();

    /**
     * 查询所有一级分类的条目
     * @return
     */
    public List<ComsCategory> queryL1();

    /**
     * 查询所有二级分类的条目
     * @return
     */
    public List<ComsCategory> queryL2();

    /**
     * 通过parentId查询其 子类别
     * @param parentId
     * @return
     */
    public List<ComsCategory> queryByParentId(Integer parentId);

    /**
     * 根据id查询目录
     * @param id
     * @return
     */
    ComsCategory findById(Integer id);

    /**
     * 类目进行模糊查询
     * @param name
     * @return
     */
    List<ComsCategory> findByName(String name);

    int removeById(Integer cateId);

    Integer update(ComsCategory category);

    Integer add(ComsCategory category);

}
