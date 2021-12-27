package com.dfbz.web;

import com.dfbz.domain.ComsOrder;
import com.dfbz.domain.ComsOrderItem;
import com.dfbz.service.IndexService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/index")
@CrossOrigin()
public class AdminIndexController {

    @Resource
    private IndexService indexService;

    @RequestMapping(value = "/data",method = RequestMethod.GET)
    public Map<String,Object> getData(){
        List<ComsOrderItem> orderItems = indexService.getTodayData();
        List<ComsOrder> orders = indexService.getWeekData();
        List<ComsOrder> yearOrders = indexService.getYearData();
        int numCount = 0;
        BigDecimal priceCount = new BigDecimal(0);
        LocalDate nowDate = LocalDate.now();
        for(ComsOrderItem orderItem:orderItems){
            if(orderItem.getCreateTime().toLocalDate().equals(nowDate)){
                numCount+=orderItem.getNum();
                priceCount = priceCount.add(orderItem.getCurrentMoney().multiply(new BigDecimal(orderItem.getNum())));

            }
        }
        BigDecimal[] weekPriceTotal = {new BigDecimal(0),new BigDecimal(0),new BigDecimal(0),
                new BigDecimal(0),new BigDecimal(0),new BigDecimal(0),new BigDecimal(0)};
        BigDecimal[] yearPriceTotal = {new BigDecimal(0),new BigDecimal(0),new BigDecimal(0),
                new BigDecimal(0),new BigDecimal(0),new BigDecimal(0),new BigDecimal(0)
                ,new BigDecimal(0),new BigDecimal(0),new BigDecimal(0),new BigDecimal(0),
                new BigDecimal(0)};

        for(int i = 0 ;i<7;i++){
            for(ComsOrder order:orders) {
                if (order.getCreateTime().toLocalDate().equals(nowDate.minusDays(i))) {
                    System.out.println(order.getAmountTotal());
                    weekPriceTotal[i] = weekPriceTotal[i].add(order.getAmountTotal());
                }
            }
        }
        for(int i = 0 ;i<12;i++){
            for(ComsOrder order:yearOrders) {
                int compareValue = nowDate.getMonthValue()-i>0?nowDate.getMonthValue()-i:12-nowDate.getMonthValue()-i;
                if (order.getCreateTime().getMonthValue() == compareValue) {
                    System.out.println(order.getAmountTotal());
                    yearPriceTotal[i] = yearPriceTotal[i].add(order.getAmountTotal());
                }
            }
        }

        Map<String,Object> data = new LinkedHashMap<>();
        data.put("numCount",numCount);
        data.put("priceCount",priceCount);
        data.put("weekPriceTotal",weekPriceTotal);
        data.put("yearPriceTotal",yearPriceTotal);
        data.put("userCount",indexService.getUserAccount());
        data.put("productCount",indexService.getProductAccount());
        return data;
    }

}
