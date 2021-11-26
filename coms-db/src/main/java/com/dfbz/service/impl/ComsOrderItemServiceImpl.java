package com.dfbz.service.impl;

import com.dfbz.dao.*;
import com.dfbz.domain.*;
import com.dfbz.dto.AppointmentDto;
import com.dfbz.dto.OrderItemView;
import com.dfbz.service.ComsCartItemService;
import com.dfbz.service.ComsOrderItemService;
import com.dfbz.service.ComsSupCateService;
import com.dfbz.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ComsOrderItemServiceImpl implements ComsOrderItemService {

    @Resource
    private ComsOrderItemMapper comsOrderItemMapper;

    @Resource
    private ComsOrderMapper comsOrderMapper;

    @Resource
    private ComsOrderItemMapper orderItemMapper;

    @Resource
    private ComsProductMapper productMapper;

    @Resource
    private ComsSupCategoryMapper supCategoryMapper;

    @Resource
    private ComsSupplierMapper supplierMapper;

    @Resource
    private ComsSupCateService supCateService;

    @Override
    public List<ComsErrOrder> selectErrOrderByOrderId() {
        return comsOrderItemMapper.selectErrOrderByOrderId();
    }

    @Override
    public List<ComsOrderItem> selectByOrderId(Integer orderId) {
        ComsOrderItemExample example = new ComsOrderItemExample();
        example.or().andOrderIdEqualTo(orderId);
        return comsOrderItemMapper.selectByExample(example);
    }

    /**
     * 查询档口的所有订单详情
     */
    public Object orderItem(Integer stallId) {
        //登录权限验证
//        if(userId == null){
//            return ResponseUtil.unlogin();
//        }
        List<ComsOrder> comsOrders = comsOrderMapper.selectByPrimaryStallId(stallId);
        Map<String, Object> result = new HashMap<>();
        for (ComsOrder comsOrder : comsOrders) {
            ComsOrderItemExample example = new ComsOrderItemExample();
            example.or().andOrderIdEqualTo(comsOrder.getId());
            List<ComsOrderItem> orderItems = comsOrderItemMapper.selectByExample(example);
            result.put(String.valueOf(comsOrder.getId()), orderItems);
        }

        return ResponseUtil.ok(result);
    }

    /**
     * 根据用户id查询订单项
     *
     * @param orderItemId
     * @return
     */
    @Override
    public Object orderItemById(Integer orderItemId) {
        return comsOrderItemMapper.selectByPrimaryKey(orderItemId);
    }

    @Override
    public int insertSelective(ComsOrderItem comsOrderItem) {
        return comsOrderItemMapper.insertSelective(comsOrderItem);
    }

    /**
     * 为订单添加订单项
     *
     * @param orderItems
     * @return
     */
    @Override
    public int addOrderItems(List<ComsOrderItem> orderItems) {
        int itemNum = 0;
        for (ComsOrderItem orderItem : orderItems) {
            itemNum += comsOrderItemMapper.insert(orderItem);
        }
        return itemNum;
    }

    /**
     * 根据用户id查询所有订单项
     *
     * @param
     * @return
     */
    @Override
    public List<ComsOrderItem> selectByProId(Integer proId) {
        ComsOrderItemExample example = new ComsOrderItemExample();
        example.or().andProdIdEqualTo(proId);
        return comsOrderItemMapper.selectByExample(example);
    }

    /**
     * 周交易总量
     *
     * @return
     */
    @Override
    public List<ComsOrderItem> totalVolumeOfNowday() {
        LocalDate nowDate = LocalDate.now();
//        LocalDateTime beforeWeekDate = LocalDateTime.of(nowDate.minusDays(7).toLocalDate(), LocalTime.MIN);
        ComsOrderItemExample example = new ComsOrderItemExample();
        example.or().andCreateTimeGreaterThan(nowDate.atStartOfDay());
        List<ComsOrderItem> orderItems = comsOrderItemMapper.selectByExample(example);
        return orderItems;
    }

    /**
     * 模糊查询orderItem里的商品
     * @param name
     * @return
     */
    @Override
    public List<ComsOrderItem> queryItemByName(String name,Integer id) {
        ComsOrderItemExample example = new ComsOrderItemExample();
        example.or(example.createCriteria().andProdNameLike("%"+name+"%").andOrderIdEqualTo(id));
        return comsOrderItemMapper.selectByExample(example);
    }


    /***
     * 查询订单项
     * @return
     */

    @Override
    public List<ComsOrderItem> selectOrderItems() {
        ComsOrderItemExample example=new ComsOrderItemExample();
        example.setOrderByClause("create_time DESC");
        List<ComsOrderItem> comsOrderItems = orderItemMapper.selectByExample(example);
        for (ComsOrderItem comsOrderItem : comsOrderItems) {
            //查产品
            ComsProduct comsProduct = productMapper.selectByPrimaryKey(comsOrderItem.getProdId());
            comsOrderItem.setProduct(comsProduct);
            //查供应商
            ComsSupCategory comsSupCategory = supCategoryMapper.selectByPrimaryKey(comsProduct.getSupCateId());
            ComsSupplier comsSupplier = supplierMapper.selectByPrimaryKey(comsSupCategory.getSupId());
            comsOrderItem.setComsSupplier(comsSupplier);
        }
        return comsOrderItems;
    }


    /***
     * 查所有订单内项目
     * @param startDate
     * @param endDate
     * @param supId
     * @return
     */
    @Override
    public List<ComsOrderItem> queryOrderItems(LocalDateTime startDate,LocalDateTime endDate,Integer supId) {

        ComsOrderItemExample example = new ComsOrderItemExample();
        example.setOrderByClause("create_time DESC");
        example.or().andCreateTimeBetween(startDate,endDate);

        List<ComsOrderItem> comsOrderItems = orderItemMapper.selectByExample(example);

        List<ComsOrderItem> list = new ArrayList<>();

        for (ComsOrderItem comsOrderItem : comsOrderItems) {
            ComsOrder comsOrder = comsOrderMapper.selectByPrimaryKey(comsOrderItem.getOrderId());
            if(comsOrder.getSupId() == supId){
                list.add(comsOrderItem);
            }
        }
        for (ComsOrderItem comsOrderItem : list) {
            //查产品
            ComsProduct comsProduct = productMapper.selectByPrimaryKey(comsOrderItem.getProdId());
            comsOrderItem.setProduct(comsProduct);
            //查供应商
            ComsSupCategory comsSupCategory = supCategoryMapper.selectByPrimaryKey(comsProduct.getSupCateId());
            ComsSupplier comsSupplier = supplierMapper.selectByPrimaryKey(comsSupCategory.getSupId());
            comsOrderItem.setComsSupplier(comsSupplier);

        }
        return list;
    }


    /***
     *时间段供应商查询所有订单项
     * @param startDate
     * @param endDate
     * @param supId
     * @return
     */
    @Override
    public List<OrderItemView> selectOrderItemBySupIdAndCreateTime(LocalDateTime startDate
            , LocalDateTime endDate, Integer supId) {
        return orderItemMapper.selectOrderItemBySupIdAndCreateTime(startDate,endDate,supId);
    }

}
