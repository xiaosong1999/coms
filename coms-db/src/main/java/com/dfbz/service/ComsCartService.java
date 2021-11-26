package com.dfbz.service;

import com.dfbz.domain.ComsCart;
import com.dfbz.domain.ComsProduct;
import com.dfbz.domain.ComsStall;


import java.util.List;

/**
 * 购物车业务层接口
 */
public interface ComsCartService {

    /**
     * 判断用户的购物车是否存在
     * @param stall
     * @return
     */
    public ComsCart queryExist(ComsStall stall);


    /**
     * 删除购物车(清空)
     * @param stall
     * @return
     */
    public boolean clear(ComsStall stall);


}
