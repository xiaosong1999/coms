package com.dfbz.service;

import com.dfbz.domain.ComsErrOrder;
import com.dfbz.domain.ComsOrderItem;
import com.dfbz.domain.ComsProduct;
import com.dfbz.dto.AppointmentDto;
import com.dfbz.dto.OrderItemView;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ComsOrderItemService {

    /**
     * 根据order_id查询异常订单信息
     */

    List<ComsErrOrder> selectErrOrderByOrderId();

    /**
     * 根据用户id查询所有订单项
     *
     * @param orderId
     * @return
     */
    List<ComsOrderItem> selectByOrderId(Integer orderId);

    /**
     * 根据用户id查询订单项
     *
     * @param stallId
     * @return
     */
    public Object orderItem(Integer stallId);

    /**
     * 根据用户id查询订单项
     *
     * @param orderItemId
     * @return
     */
    public Object orderItemById(Integer orderItemId);

    /**
     * 添加订单项
     *
     * @param comsOrderItem
     * @return
     */
    int insertSelective(ComsOrderItem comsOrderItem);

    /**
     * 为订单添加订单项
     *
     * @param orderItems
     * @return
     */
    int addOrderItems(List<ComsOrderItem> orderItems);


    /**
     * 根据用户id查询所有订单项
     *
     * @param
     * @return
     */
    List<ComsOrderItem> selectByProId(Integer proId);

    /**
     * 今天交易总量
     * @return
     */
    List<ComsOrderItem> totalVolumeOfNowday();



    /***
     * 关键字模糊查询orderItem里的商品
     */
    List<ComsOrderItem> queryItemByName(String name,Integer id);

    /***
     * 查询订单项
     * @return
     */
    List<ComsOrderItem> selectOrderItems();


    /***
     * 查所有订单内项目
     * @param startDate
     * @param endDate
     * @param supId
     * @return
     */
    public List<ComsOrderItem> queryOrderItems(LocalDateTime startDate, LocalDateTime endDate, Integer supId);

    /***
     * 时间段供应商查询所有订单项
     * @param startDate
     * @param endDate
     * @param supId
     * @return
     */
    List<OrderItemView> selectOrderItemBySupIdAndCreateTime(LocalDateTime startDate
            , LocalDateTime endDate, Integer supId);

}
