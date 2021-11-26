package com.dfbz.service.impl;

import com.dfbz.domain.ComsOrder;
import com.dfbz.domain.ComsOrderItem;
import com.dfbz.service.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexServiceImpl implements IndexService {

    @Resource
    private ComsOrderItemService orderItemService;

    @Resource
    private ComsOrderService orderService;

    @Resource
    private ComsProductService productService;

    @Resource
    private ComsAdminService adminService;

    @Resource
    private ComsSupplierService supplierService;

    @Resource
    private ComsStallService stallService;

    @Override
    public List<ComsOrderItem> getTodayData() {
        return orderItemService.totalVolumeOfNowday();
    }

    @Override
    public List<ComsOrder> getWeekData() {
        return orderService.queryOrdersInWeek();
    }

    @Override
    public List<ComsOrder> getYearData() {
        return orderService.queryOrdersInYear();
    }

    @Override
    public long getUserAccount() {
        return adminService.getAccount() + stallService.getAcount() + supplierService.getAcount();
    }

    @Override
    public long getProductAccount() {
        return productService.getAcount();
    }


}
