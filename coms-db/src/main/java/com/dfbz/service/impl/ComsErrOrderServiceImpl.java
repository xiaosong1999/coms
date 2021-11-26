package com.dfbz.service.impl;

import com.dfbz.dao.ComsErrOrderMapper;
import com.dfbz.domain.ComsErrOrder;
import com.dfbz.domain.ComsErrOrderExample;
import com.dfbz.service.ComsErrOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ComsErrOrderServiceImpl implements ComsErrOrderService {

    @Resource
    private ComsErrOrderMapper comsErrOrderMapper;

    @Transactional
    @Override
    public int insertSelective(ComsErrOrder comsErrOrder) {
        return comsErrOrderMapper.insertSelective(comsErrOrder);
    }

    /**
     * 查询所有异常订单
     *
     * @return
     */
    @Override
    public List<ComsErrOrder> queryAllErrOrders() {
        return comsErrOrderMapper.selectByExample(null);
    }

    /**
     * @param orderId
     * @return
     */
    @Override
    public List<ComsErrOrder> queryByOrderId(Integer orderId) {
        ComsErrOrderExample example = new ComsErrOrderExample();
        example.or().andOrderIdEqualTo(orderId);
        return comsErrOrderMapper.selectByExample(example);
    }
}
