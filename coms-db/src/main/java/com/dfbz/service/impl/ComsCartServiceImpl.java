package com.dfbz.service.impl;

import com.dfbz.domain.ComsCart;
import com.dfbz.domain.ComsCartItem;
import com.dfbz.domain.ComsStall;
import com.dfbz.service.ComsCartItemService;
import com.dfbz.service.ComsCartService;
import com.dfbz.util.RedisClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import static com.dfbz.util.CodeUtil.*;

@Service
public class ComsCartServiceImpl implements ComsCartService {

    @Resource
    private RedisClient redisClient;


    /**
     * 判断用户的购物车是否存在 并返回
     *
     * @param stall
     * @return
     */
    @Override
    public ComsCart queryExist(ComsStall stall) {
        ComsCart cart = (ComsCart)redisClient.get(fixToCartCode(stall));
        return cart;
    }



    /**
     * 删除购物车(清空)
     *
     * @param stall
     * @return
     */
    @Override
    public boolean clear(ComsStall stall) {

        return redisClient.clear(fixToCartCode(stall));
    }

}
