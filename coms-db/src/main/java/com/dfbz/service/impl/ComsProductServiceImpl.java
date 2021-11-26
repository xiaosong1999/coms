package com.dfbz.service.impl;

import com.dfbz.dao.ComsProductMapper;
import com.dfbz.dao.ComsSupCategoryMapper;
import com.dfbz.domain.*;
import com.dfbz.service.ComsCartItemService;
import com.dfbz.service.ComsProductService;
import com.dfbz.service.ComsStallService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ComsProductServiceImpl implements ComsProductService {

    @Resource
    ComsProductMapper productMapper;


    @Resource
    ComsSupCategoryMapper supCategoryMapper;

    @Resource
    ComsCartItemService cartItemService;

    @Resource
    ComsStallService stallService;

    @Resource
    ComsProductService productService;

    @Override
    public List<ComsProduct> queryByCategory(Integer cateId, int offset, int limit) {
        ComsProductExample example = new ComsProductExample();
        example.or().andSupCateIdEqualTo(cateId);
        return productMapper.selectByExample(example);
    }

    @Override
    public List<ComsProduct> findOnSale() {
        ComsProductExample example = new ComsProductExample();
        example.or().andStatusEqualTo(1);
        return productMapper.selectByExample(example);
    }

    /***
     * 按id查商品
     * @param id
     * @return
     */
    @Override
    public ComsProduct findById(Integer id) {
        ComsProductExample example = new ComsProductExample();
        example.or().andStatusNotEqualTo(-1).andIdEqualTo(id);
        return productMapper.selectOneByExample(example);
    }

    @Override
    public int count() {
        return productMapper.selectByExample(null).size();
    }

    /***
     * 显示所有商品
     * @return
     */
    @Override
    public List<ComsProduct> listAll() {
        ComsProductExample example = new ComsProductExample();
        example.or().andIdIsNotNull();
        return productMapper.selectByExample(example);
    }

    /***
     * 添加商品
     * @param product
     * @return
     */
    @Override
    public Integer add(ComsProduct product) {
        product.setCreateTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        return productMapper.insert(product);
    }

    /***
     * 修改条目
     * @param product
     * @return
     */
    @Override
    public Integer update(ComsProduct product) {
        ComsProductExample productExample = new ComsProductExample();
        productExample.or().andIdEqualTo(product.getId());
        List<ComsStall> stalls = stallService.listAllStall();
        for (ComsStall stall : stalls) {
            for (ComsCartItem cartItem : cartItemService.queryCartProdForList(stall)) {
                if (cartItem.getProdId().equals(product.getId())) {
                    cartItem.setPicUrl(product.getPicUrl());
                    cartItem.setName(product.getName());
                    cartItem.setPrice(product.getPrice());
                    cartItemService.update(stall, cartItem);
                }
            }
        }


        return productMapper.updateByExampleSelective(product, productExample);

    }


    /**
     * 根据name匹配模糊查询
     */
    public List<ComsProduct> queryProductByName(String name) {
        ComsProductExample productExample = new ComsProductExample();
        productExample.or(productExample.createCriteria().andNameLike('%' + name + '%'));
        return productMapper.selectByExample(productExample);
    }

    /**
     * 根据分类Id查询
     *
     * @param cateId
     * @return
     */
    @Override
    public List<ComsProduct> findByCateId(Integer cateId) {
        ComsProductExample example = new ComsProductExample();
        example.or().andSupCateIdEqualTo(cateId);
        return productMapper.selectByExample(example);
    }

    @Override
    public long getAcount() {
        return productMapper.countByExample(new ComsProductExample());
    }

    @Override
    public int removeById(Integer prodId) {
        return productMapper.deleteByPrimaryKey(prodId);
    }


    /**
     * 第二版 接口方法
     */

    @Override
    public List<ComsProduct> queryProductByKey(String name) {
        ComsProductExample productExample = new ComsProductExample();
        productExample.or().andNameLike("%" + name + "%").andStatusEqualTo(1);
        return productMapper.selectByExample(productExample);
    }


    @Override
    public List<ComsProduct> queryByCategory(Integer cateId) {
        ComsProductExample example = new ComsProductExample();
        example.or().andSupCateIdEqualTo(cateId).andStatusEqualTo(1);
        return productMapper.selectByExample(example);
    }


    //更改status逻辑删除商品仓库的商品
    @Override
    public Integer deleteByUpdateStatus(String[] idList) {
        return productMapper.deleteByUpdateStatus(idList);
    }

    /**
     * 删除今日报价
     *
     * @param prodId
     * @return
     */
    @Override
    public Integer deleteTodayPrice(Integer prodId) {
        return productMapper.deleteTodayPrice(prodId);
    }

    /***
     * 24点下架所有商品
     * @return
     */
    @Override
    public Integer offTheProduct() {
        ComsProductExample example = new ComsProductExample();
        example.or().andStatusEqualTo(1);
        List<ComsProduct> comsProducts = productMapper.selectByExample(example);
        for (ComsProduct comsProduct : comsProducts) {
            comsProduct.setStatus(0);
            productService.update(comsProduct);
        }
        return 1;
    }

    //更改status逻辑取消今日报价的商品
    @Override
    public Integer cancelTodayPriceStatus(String[] pidList) {
        return productMapper.cancelTodayPriceStatus(pidList);
    }

    @Override
    public List<ComsProduct> queryProductByNameAndId(String name, Integer id) {

        ComsSupCategoryExample supCategoryExample = new ComsSupCategoryExample();
        supCategoryExample.or().andSupIdEqualTo(id);
        List<ComsSupCategory> comsSupCategories = supCategoryMapper.selectByExample(supCategoryExample);
        List<ComsProduct> comsProducts = new ArrayList<ComsProduct>();
        for (ComsSupCategory supCategory : comsSupCategories) {
            ComsProductExample productExample = new ComsProductExample();
            productExample.createCriteria().andNameLike('%' + name + '%').andSupCateIdEqualTo(supCategory.getId());
            List<ComsProduct> comsProducts_temp = productMapper.selectByExample(productExample);
            comsProducts.addAll(comsProducts_temp);
        }


        return comsProducts;
    }
}
