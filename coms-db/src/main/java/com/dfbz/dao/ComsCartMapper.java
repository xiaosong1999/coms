package com.dfbz.dao;

import com.dfbz.domain.ComsCart;
import com.dfbz.domain.ComsCartExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ComsCartMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_cart
     *
     * @mbg.generated
     */
    long countByExample(ComsCartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_cart
     *
     * @mbg.generated
     */
    int deleteByExample(ComsCartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_cart
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_cart
     *
     * @mbg.generated
     */
    int insert(ComsCart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_cart
     *
     * @mbg.generated
     */
    int insertSelective(ComsCart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_cart
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    ComsCart selectOneByExample(ComsCartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_cart
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    ComsCart selectOneByExampleSelective(@Param("example") ComsCartExample example, @Param("selective") ComsCart.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_cart
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<ComsCart> selectByExampleSelective(@Param("example") ComsCartExample example, @Param("selective") ComsCart.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_cart
     *
     * @mbg.generated
     */
    List<ComsCart> selectByExample(ComsCartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_cart
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    ComsCart selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") ComsCart.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_cart
     *
     * @mbg.generated
     */
    ComsCart selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_cart
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ComsCart record, @Param("example") ComsCartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_cart
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ComsCart record, @Param("example") ComsCartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_cart
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ComsCart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_cart
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ComsCart record);
}