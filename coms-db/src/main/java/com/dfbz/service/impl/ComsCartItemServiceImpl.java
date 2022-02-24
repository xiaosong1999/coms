package com.dfbz.service.impl;

import com.dfbz.domain.ComsCartItem;
import com.dfbz.domain.ComsProduct;
import com.dfbz.domain.ComsStall;
import com.dfbz.service.ComsOrderItemService;
import com.dfbz.service.ComsOrderService;
import com.dfbz.util.RedisClient;
import org.springframework.stereotype.Service;
import com.dfbz.service.ComsCartItemService;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

import static com.dfbz.util.CodeUtil.*;

@Service
public class ComsCartItemServiceImpl implements ComsCartItemService {

    @Resource
    private RedisClient redisClient;

    /**
     * 查询购物车中商品
     *
     * @param stall
     * @return
     */
    @Override
    public List<ComsCartItem> queryCartProdForList(ComsStall stall) {
        Map<String, ComsCartItem> maps = queryCartProdForMap(stall);
        Set<String> sets = maps.keySet();
        List<ComsCartItem> items = new ArrayList<>();
        for(String set:sets){
            items.add(maps.get(set));
        }
        return items;
    }

    /**
     * 查询购物车中商品结果集Map
     * @param stall
     * @return
     */
    @Override
    public Map<String, ComsCartItem> queryCartProdForMap(ComsStall stall){
        Map<Object, Object> entries = redisClient.entries(fixToCartCode(stall));
        Map<String,ComsCartItem> map = new HashMap<>();
        Set<Object> sets = entries.keySet();
        for(Object set:sets){
            System.out.println(entries.get(set));
            map.put((String) set, (ComsCartItem) entries.get(set));
        }
        return map;
    }


    /**
     * 购买购物车指定商品
     * @param stall
     * @param cartItemId
     * @return
     */
    @Override
    public long purchaseByProdId(ComsStall stall,Integer cartItemId) {
        return redisClient.delete(fixToCartCode(stall),String.valueOf(cartItemId));
    }

    /**
     * 删除购物车指定商品
     * @param stall
     * @param cartItemId
     * @return
     */
    @Override
    public long deleteByProdId(ComsStall stall,Integer cartItemId) {
        return redisClient.delete(fixToCartCode(stall),String.valueOf(cartItemId));
    }

    /**
     * 添加商品至购物车
     *
     * @param product
     * @return
     */
    @Override
    public void addProduct(ComsStall stall,ComsProduct product,Integer num) {

        ComsCartItem cartItem = new ComsCartItem();
        cartItem.setAddTime(LocalDateTime.now());
        cartItem.setNum(num);

        cartItem.setId((int)new Date().getTime());
//        System.out.println(fixToCartItemCode(new Date()));
        cartItem.setPicUrl(product.getPicUrl());
        cartItem.setPrice(product.getPrice());
        cartItem.setProdId(product.getId());
        cartItem.setName(product.getName());
//        System.out.println(cartItem.getName());
        System.out.println(fixToCartCode(stall));
//        System.out.println("asdf");
//        redisClient.put(fixToCartCode(stall),cartItem.getId(),null);
        redisClient.put(fixToCartCode(stall),String.valueOf(cartItem.getId()),cartItem);
    }

    /**
     * 更新购物车
     *
     * @param stall
     * @param cartItem
     */
    @Override
    public void update(ComsStall stall, ComsCartItem cartItem) {
        redisClient.put(fixToCartCode(stall),String.valueOf(cartItem.getId()),cartItem);
    }


}
