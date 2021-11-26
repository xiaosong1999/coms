package com.dfbz.service.impl;

import com.dfbz.dao.ComsOrderItemMapper;
import com.dfbz.dao.ComsOrderMapper;
import com.dfbz.dao.ComsProductMapper;
import com.dfbz.dao.ComsStallMapper;
import com.dfbz.domain.*;
import com.dfbz.service.ComsOrderService;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Service
public class ComsOrderServiceImpl implements ComsOrderService {

    @Resource
    private ComsOrderMapper comsOrderMapper;

    @Resource
    private ComsOrderItemMapper comsOrderItemMapper;
    @Resource
    private ComsProductMapper comsProductMapper;

    @Resource
    private ComsStallMapper comsStallMapper;

    @Transactional
    @Override
    public int insert(ComsOrder comsOrder) {
        return comsOrderMapper.insert(comsOrder);
    }

    @Override
    public int insertSelective(ComsOrder comsOrder) {
        return comsOrderMapper.insertSelective(comsOrder);
    }

    /**
     * 查询所有订单
     *
     * @param example
     * @return
     */
    @Override
    public List<ComsOrder> selectByExample(ComsOrderExample example) {
        return comsOrderMapper.selectByExample(example);
    }

    @Override
    public List<ComsOrder> selectByPrimaryStallId(Integer stallId) {
        return comsOrderMapper.selectByPrimaryStallId(stallId);
    }

    /**
     * 生成订单
     *
     * @param comsCart
     * @param stallId
     * @return
     */
    @Transactional
    @Override
    public Integer createOrder(ComsCart comsCart, Integer stallId) {
        //订单号===唯一性
        String orderId = System.currentTimeMillis() + "" + stallId;
        //创建一个订单对象
        ComsOrder comsOrder = new ComsOrder();
        //保存订单
        comsOrder.setId(comsCart.getId());
        comsOrder.setStallId(stallId);
        comsOrder.setAmountTotal(comsCart.getAmountTotal());
        comsOrderMapper.insert(comsOrder);

        //遍历购物车中的每一个商品转换成为订单项保存到数据库

//        for(Map.Entry<String, ComsCartItem >entry : ComsCart.getItems().entrySet()){
//            //获取每一个购物车中的商品项
//            ComsCartItem comsCartItem = entry.getValue();
//            //转换为订单项
//            ComsOrderItem comsOrderItem = new ComsOrderItem(null,stallId,comsCartItem.getPrice(),comsCartItem.getNum(),null,null,comsCartItem.getAddTime(),null);
//            //保存订单到数据库
//            comsOrderItem.setProdId(null);
//            comsOrderItemMapper.insert(comsOrderItem);
//
//            //更新库存
//            ComsProduct comsProduct = comsProductMapper.selectByPrimaryKey(comsCartItem.getProdId());
//            comsProduct.setStock(comsProduct.getStock() - comsCartItem.getNum());
//            comsProductMapper.updateByPrimaryKey(comsProduct);
//        }
        //清空购物车
        return stallId;
    }

    /***
     * 按id更新订单
     * @param orderId
     * @return
     */
    @Override
    public int updateOrder(Integer orderId) {
        ComsOrder comsOrder = comsOrderMapper.selectByPrimaryKey(orderId);
        ComsOrderItemExample example = new ComsOrderItemExample();
        example.or().andOrderIdEqualTo(orderId);
        List<ComsOrderItem> orderItems = comsOrderItemMapper.selectByExampleSelective(example);
        LocalDateTime nowTime = LocalDateTime.now();

        for (ComsOrderItem orderItem : orderItems) {
            orderItem.setUpdateTime(nowTime);
            comsOrderItemMapper.updateByPrimaryKey(orderItem);
        }
        comsOrder.setUpdateTime(nowTime);
        comsOrder.setStatus(-1);

        return comsOrderMapper.updateByPrimaryKeySelective(comsOrder);
    }


    /***
     * 按order更新order
     * @param order
     * @return
     */
    @Override
    public int updateOrder(ComsOrder order) {
        ComsOrderExample example = new ComsOrderExample();
        return comsOrderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public List<ComsOrder> queryOrdersInWeek() {
        LocalDate startDate = LocalDate.now().minusDays(7);
//        LocalDateTime beforeWeekDate = LocalDateTime.of(nowDate.minusDays(7).toLocalDate(), LocalTime.MIN);
        ComsOrderExample example = new ComsOrderExample();
        example.or().andCreateTimeGreaterThan(startDate.atStartOfDay());
        List<ComsOrder> orders = comsOrderMapper.selectByExample(example);
        return orders;
    }

    /***
     * 按年筛选订单
     * @return
     */
    @Override
    public List<ComsOrder> queryOrdersInYear() {
        int startMonth = LocalDate.now().getMonthValue() + 1 > 12 ? 1 : LocalDate.now().getMonthValue() + 1;
        LocalDate date = null;
        if (startMonth == 1) {
            date = LocalDate.of(LocalDate.now().getYear(), startMonth, 1);
        } else {
            date = LocalDate.of(LocalDate.now().getYear() - 1, startMonth, 1);
        }
        ComsOrderExample example = new ComsOrderExample();
        example.or().andCreateTimeGreaterThan(date.atStartOfDay());
        List<ComsOrder> orders = comsOrderMapper.selectByExample(example);
        return orders;
    }

    /***
     * 显示所有订单
     * @return
     */
    @Override
    public List<ComsOrder> listAll() {
        ComsOrderExample example = new ComsOrderExample();
        example.or().andIdIsNotNull();
        example.setOrderByClause("create_time desc");
        List<ComsOrder> orders = comsOrderMapper.selectByExample(example);
        return orders;
    }


    /***
     * 按orderId查购买档口id
     */
    @Override
    public ComsStall findStallByOrder(Integer id) {
        ComsOrderExample example = new ComsOrderExample();
        example.or().andIdEqualTo(id);
        ComsOrder order = comsOrderMapper.selectOneByExample(example);
        ComsStallExample example1 = new ComsStallExample();
        example1.or().andIdEqualTo(order.getStallId());
        ComsStall stall = comsStallMapper.selectOneByExample(example1);
        return stall;
    }

    /***
     * 关键字模糊搜索订单
     * @param keyWord
     * @return
     */
    @Override
    public List<ComsOrder> findOrderBySup(String keyWord) {
        ComsOrderExample example=new ComsOrderExample();
//        example.or(example.createCriteria().andSupNameLike("%"+keyWord+"%"));
        example.or().andSupNameLike("%"+keyWord+"%");
        example.setOrderByClause("create_time desc");
        return comsOrderMapper.selectByExample(example);
    }


    /**
     * 第二版
     */
    @Override
    public int delete(int id) {
        return comsOrderMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据Id查询supplier订单
     * @param supplierId
     * @return
     */
    @Override
    public List<ComsOrder> selectByPrimarySupplierId(Integer supplierId) {
        return comsOrderMapper.selectByPrimarySupplierId(supplierId);
    }




    //ComsOrderServiceImpl第三版
    @Override
    public List<ComsOrder> findErrorOrdersByStallId(Integer stallId) {
        ComsOrderExample example = new ComsOrderExample();

        example.or().andStallIdEqualTo(stallId).andStatusEqualTo(-1);
        ComsOrderExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("update_time desc");
        return comsOrderMapper.selectByExample(example);
    }

    @Override
    public List<ComsOrder> findNormalOrdersByStallId(Integer stallId) {
        ComsOrderExample example = new ComsOrderExample();
        example.or().andStallIdEqualTo(stallId).andStatusEqualTo(1).andCreateTimeLessThan(LocalDateTime.of(LocalDate.now(), LocalTime.MIN));
        ComsOrderExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("update_time desc");
        return comsOrderMapper.selectByExample(example);
    }

    @Override
    public List<ComsOrder> findTodayOrdersByStallId(Integer stallId) {
        ComsOrderExample example = new ComsOrderExample();
        example.or().andStallIdEqualTo(stallId).andStatusEqualTo(1).andCreateTimeGreaterThan(LocalDateTime.of(LocalDate.now(), LocalTime.MIN));
        ComsOrderExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("update_time desc");
        return comsOrderMapper.selectByExample(example);
    }

}
