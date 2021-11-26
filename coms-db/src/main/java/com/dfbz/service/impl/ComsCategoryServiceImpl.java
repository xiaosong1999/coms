package com.dfbz.service.impl;

import com.dfbz.dao.ComsCategoryMapper;
import com.dfbz.domain.ComsCartExample;
import com.dfbz.domain.ComsCategory;
import com.dfbz.domain.ComsCategoryExample;
import com.dfbz.service.ComsCategoryService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 类别的业务实现
 * @author 17352
 */
@Service
public class ComsCategoryServiceImpl implements ComsCategoryService {

    @Resource
    private ComsCategoryMapper categoryMapper;

    @Override
    public List<ComsCategory> queryAll() {
        return categoryMapper.selectByExample(null);
    }

    /**
     * 查询所有一级分类的条目
     *
     * @return
     */
    @Override
    public List<ComsCategory> queryL1() {
        ComsCategoryExample example = new ComsCategoryExample();
        example.or().andParentIdEqualTo(0);
        return categoryMapper.selectByExample(example);
    }

    /**
     * 查询所有二级分类的条目
     *
     * @return
     */
    @Override
    public List<ComsCategory> queryL2() {
        ComsCategoryExample example = new ComsCategoryExample();
        example.or().andParentIdNotEqualTo(0);
        return categoryMapper.selectByExample(example);
    }
    /**
     * 查询所有一级分类的条目 并设置页码格式
     *
     * @return
     */
    /*@Override
    public List<ComsCategory> queryL1(int offset, int limit) {
        ComsCategoryExample example = new ComsCategoryExample();
        example.or().andParentIdEqualTo(0);
        PageHelper.startPage(offset, limit);
        return categoryMapper.selectByExample(example);
    }*/

    /**
     * 通过parentId查询其 子类别
     *
     * @param parentId
     * @return
     */
    @Override
    public List<ComsCategory> queryByParentId(Integer parentId) {
        ComsCategoryExample example = new ComsCategoryExample();
        example.or().andParentIdEqualTo(parentId);
        return categoryMapper.selectByExample(example);
    }

    @Override
    public ComsCategory findById(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ComsCategory> findByName(String name) {
        ComsCategoryExample example = new ComsCategoryExample();
        example.or().andNameLike("%"+name+"%");
        return categoryMapper.selectByExample(example);
    }

    @Override
    public int removeById(Integer cateId) {
        return categoryMapper.deleteByPrimaryKey(cateId);
    }

    @Override
    public Integer update(ComsCategory category) {
        category.setUpdateTime(LocalDateTime.now());
        return categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public Integer add(ComsCategory category) {
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        return categoryMapper.insertSelective(category);
    }
}
