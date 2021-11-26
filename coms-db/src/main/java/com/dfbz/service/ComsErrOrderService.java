package com.dfbz.service;

import com.dfbz.domain.ComsErrOrder;

import java.util.List;

public interface ComsErrOrderService {

    /**
     * 添加异常订单
     * @param comsErrOrder
     * @return
     */
    int insertSelective(ComsErrOrder comsErrOrder);

    /**
     * 查询所有异常订单
     * @return
     */
    List<ComsErrOrder> queryAllErrOrders();

    /**
     *
     * @param orderId
     * @return
     */
    List<ComsErrOrder> queryByOrderId(Integer orderId);




}
