package com.dfbz.service;

import com.dfbz.domain.ComsCartItem;
import com.dfbz.domain.ComsProduct;
import com.dfbz.domain.ComsStall;

import java.util.List;
import java.util.Map;

public interface ComsCartItemService {

    /**
     * 查询购物车中商品list
     * @param stall
     * @return
     */
    public List<ComsCartItem> queryCartProdForList(ComsStall stall);

    /**
     * 查询购物车中商品map
     * @param stall
     * @return
     */
    public Map<String, ComsCartItem> queryCartProdForMap(ComsStall stall);


    /**
     * 购买购物车指定商品
     * @param stall
     * @param cartItemId
     * @return
     */
    public long purchaseByProdId(ComsStall stall,Integer cartItemId);

    /**
     * 删除购物车指定商品
     * @param stall
     * @param cartItemId
     * @return
     */
    public long deleteByProdId(ComsStall stall,Integer cartItemId);


    /**
     * 添加商品至购物车
     * @param stall
     * @param product
     * @return
     */
    public void addProduct(ComsStall stall,ComsProduct product,Integer num);

    /**
     * 更新购物车
     * @param stall
     * @param item
     */
    public void update(ComsStall stall,ComsCartItem item);

}
