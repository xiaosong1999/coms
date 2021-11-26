package com.dfbz.service;

import com.dfbz.domain.*;

import java.util.List;

public interface ComsOrderService {

    /**
     * 添加订单
     */
    int insert(ComsOrder comsOrder);

    /**
     * 添加订单
     * @param comsOrder
     * @return
     */
    int insertSelective(ComsOrder comsOrder);

    /**
     * 查询所有订单
     */
    List<ComsOrder> selectByExample(ComsOrderExample example);

    /**
     * 查询特定用户订单
     */
    List<ComsOrder> selectByPrimaryStallId(Integer stallId);

    /**
     * 根据supplierId查询用户订单
     * @param supplierId
     * @return
     */
    List<ComsOrder> selectByPrimarySupplierId(Integer supplierId);


    /**
     * 创建订单
     * @param comsCart
     * @param stallId
     * @return
     */
    public Integer createOrder(ComsCart comsCart,Integer stallId);

    /***
     * 更新订单
     * @param orderId
     * @return
     */
    int updateOrder(Integer orderId);

    /***
     * 更新订单
     * @param order
     * @return
     */
    int updateOrder(ComsOrder order);

    /**
     * 查询一周的订单
     */
    List<ComsOrder> queryOrdersInWeek();

    /**
     * 查询一年的订单
     */
    List<ComsOrder> queryOrdersInYear();


    /***
     * 列出所有订单
     */
    List<ComsOrder> listAll();

    /***
     * 按orderId查购买档口id
     */
    ComsStall findStallByOrder(Integer id);


    /***
     * 按供应商或档口模糊搜索
     */
    List<ComsOrder> findOrderBySup(String keyWord);

    /**
     * 2版
     */
    public int delete(int id);


    //ComsOrderService第三版
    List<ComsOrder> findErrorOrdersByStallId(Integer stallId);

    List<ComsOrder> findNormalOrdersByStallId(Integer stallId);

    List<ComsOrder> findTodayOrdersByStallId(Integer stallId);


}
