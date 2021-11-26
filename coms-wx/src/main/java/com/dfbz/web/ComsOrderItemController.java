package com.dfbz.web;


import com.dfbz.domain.ComsErrOrder;
import com.dfbz.domain.ComsOrder;
import com.dfbz.domain.ComsOrderExample;
import com.dfbz.service.ComsErrOrderService;
import com.dfbz.service.ComsOrderItemService;
import com.dfbz.service.ComsOrderService;
import com.dfbz.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/wx/orderItem")
public class ComsOrderItemController {

    @Autowired
    private ComsOrderItemService comsOrderItemService;




    @GetMapping(value = "/selectErrOrderByOrderId")
    public Object selectErrOrderByOrderId(){
        List<ComsErrOrder> comsErrOrders = comsOrderItemService.selectErrOrderByOrderId();
        return ResponseUtil.ok(comsErrOrders);
    }

    /**
     * 根据用户id查询所有订单项
     * @param stallId
     * @return
     */
    @GetMapping(value = "/getOrderItem")
    public Object orderItem(Integer stallId){
        Object orderItem = comsOrderItemService.orderItem(stallId);
        return ResponseUtil.ok(orderItem);
    }
    @GetMapping(value = "/getOrderItemById")
    public Object orderItemById(Integer orderItemId){
        Object orderItem = comsOrderItemService.orderItemById(orderItemId);
        return ResponseUtil.ok(orderItem);
    }


}
