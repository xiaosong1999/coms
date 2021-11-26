package com.dfbz.service;

import com.dfbz.domain.ComsPriceList;

import java.util.List;

public interface ComsPriceListService {

    /**
     * 添加报价
     *
     * @param priceList
     * @return
     */
    public int addPrice(ComsPriceList priceList);

    /**
     * 查询商品的最新价格
     *
     * @param prodId
     * @param
     * @return
     */
    public ComsPriceList queryLatestPrice(Integer prodId);

    /**
     * 查询所有价格清单
     *
     * @return
     */
    public List<ComsPriceList> queryAllPriceList();

    /**
     * 根据proID和supId查询价格列表
     *
     * @param prodId
     * @param
     * @return
     */
    public List<ComsPriceList> queryPriceListByProIdAndSupId(Integer prodId, Integer supId);

    /**
     * 根据供应商id查询列表
     *
     * @param
     * @param
     * @return
     */
    public List<ComsPriceList> queryPriceListBySupId(Integer supId);

    /**
     * 查询特定商品的价格表
     */
    public List<ComsPriceList> queryPriceListByProId(Integer prodId);
}
