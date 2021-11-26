package com.dfbz.service.impl;

import com.dfbz.dao.ComsOrderMapper;
import com.dfbz.dao.ComsProductMapper;
import com.dfbz.domain.ComsOrder;
import com.dfbz.domain.ComsOrderExample;
import com.dfbz.domain.ComsProduct;
import com.dfbz.domain.ComsProductExample;
import com.dfbz.dto.AppointmentDto;
import com.dfbz.service.SearchService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Resource
    private ComsProductMapper productMapper;

    @Resource
    private ComsOrderMapper orderMapper;

    @Override
    public List<ComsProduct> searchProByDate(AppointmentDto appointmentDto) {
        List<ComsProduct> products = null;
        if(appointmentDto.getStartDate() != null || appointmentDto.getEndDate() != null){
            ComsProductExample productExample = new ComsProductExample();
            productExample.setOrderByClause("update_time DESC");
            productExample.or().andUpdateTimeBetween(appointmentDto.getStartDate(),
                                                     appointmentDto.getEndDate());

            products = productMapper.selectByExample(productExample);

        }
        return  products;
    }

    @Override
    public List<ComsOrder> searchOrdersByDate(AppointmentDto appointmentDto) {
        List<ComsOrder> comsOrders = null;
        if(appointmentDto.getStartDate() != null || appointmentDto.getEndDate() != null){
            ComsOrderExample example = new ComsOrderExample();
            example.setOrderByClause("update_time DESC");
            example.or().andUpdateTimeBetween(appointmentDto.getStartDate(),
                    appointmentDto.getEndDate());

            comsOrders = orderMapper.selectByExample(example);

        }
        return  comsOrders;
    }


}
