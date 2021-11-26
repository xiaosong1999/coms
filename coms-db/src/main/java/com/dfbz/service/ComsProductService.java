package com.dfbz.service;

import com.dfbz.domain.ComsProduct;

import java.util.List;

public interface ComsProductService {

    /**
     * 获取某个分类的商品
     *
     * @param cateId
     * @param offset
     * @param limit
     * @return
     */
    public List<ComsProduct> queryByCategory(Integer cateId, int offset, int limit);

    /**
     * 获取所有上架的商品
     * @return
     */
    public List<ComsProduct> findOnSale();

    /**
     * 获取某个商品信息，仅展示相关内容
     *
     * @param id
     * @return
     */
    public ComsProduct findById(Integer id);


    /**
     * 订单所有物品总数 ， 包括在售的和下架的， 但是
     *
     * @return
     */
    public int count();

    /***
     * 显示所有产品
     * @return
     */
    public List<ComsProduct> listAll();

    /***
     * 添加新产品
     * @param product
     * @return
     */
    public Integer add(ComsProduct product);

    /***
     * 修改商品详情
     * @param product
     * @return
     */
    public Integer update(ComsProduct product);

    /**
     * 根据name匹配模糊查询
     */
    public List<ComsProduct> queryProductByName(String name);

    /**
     * 根据分类Id查询
     *
     * @param cateId
     * @return
     */
    public List<ComsProduct> findByCateId(Integer cateId);

    /**
     * 获取总数
     * @return
     */
    public long getAcount();

    /**
     * 根据id删除商品
     * @param prodId
     * @return
     */
    public int removeById(Integer prodId);


    /**
     * 第二版 接口方法
     */
    /**
     * 关键字查询商品
     * @param name
     * @return
     */
    public List<ComsProduct> queryProductByKey(String name);


    public List<ComsProduct> queryByCategory(Integer cateId);

    //更改status逻辑删除商品仓库的商品
    public Integer deleteByUpdateStatus(String[] pidList);

    /**
     * 删除今日报价
     * @param prodId
     * @return
     */
    public Integer deleteTodayPrice(Integer prodId);


    /***
     * 24点后将所有上架商品下架
     */

    public Integer offTheProduct();

    /***
     * 更改status逻辑取消今日报价的商品
     */
    Integer cancelTodayPriceStatus(String[] pidList);




    /**
     * 根据name和id匹配模糊查询
     */
    public List<ComsProduct> queryProductByNameAndId(String name,Integer id);

}
