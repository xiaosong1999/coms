package com.dfbz.dao;

import com.dfbz.domain.ComsErrOrder;
import com.dfbz.domain.ComsOrderItem;
import com.dfbz.domain.ComsOrderItemExample;
import com.dfbz.dto.OrderItemView;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

public interface ComsOrderItemMapper {


    //根据order_id查询异常订单
    List<ComsErrOrder> selectErrOrderByOrderId();
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_order_item
     *
     * @mbg.generated
     */
    long countByExample(ComsOrderItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_order_item
     *
     * @mbg.generated
     */
    int deleteByExample(ComsOrderItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_order_item
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_order_item
     *
     * @mbg.generated
     */
    int insert(ComsOrderItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_order_item
     *
     * @mbg.generated
     */
    int insertSelective(ComsOrderItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_order_item
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    ComsOrderItem selectOneByExample(ComsOrderItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_order_item
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    ComsOrderItem selectOneByExampleSelective(@Param("example") ComsOrderItemExample example, @Param("selective") ComsOrderItem.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_order_item
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<ComsOrderItem> selectByExampleSelective(@Param("example") ComsOrderItemExample example, @Param("selective") ComsOrderItem.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_order_item
     *
     * @mbg.generated
     */
    List<ComsOrderItem> selectByExample(ComsOrderItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_order_item
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    ComsOrderItem selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") ComsOrderItem.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_order_item
     *
     * @mbg.generated
     */
    ComsOrderItem selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_order_item
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ComsOrderItem record, @Param("example") ComsOrderItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_order_item
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ComsOrderItem record, @Param("example") ComsOrderItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_order_item
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ComsOrderItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_order_item
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ComsOrderItem record);



    /**
     * 联合查询
     */
    @Select("select * from orderItemView where create_time between #{startDate} and #{endDate} " +
            "and sup_id=#{supId} ORDER BY create_time DESC")
    List<OrderItemView> selectOrderItemBySupIdAndCreateTime(LocalDateTime startDate
            , LocalDateTime endDate, Integer supId);
}