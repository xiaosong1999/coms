package com.dfbz.web;

import com.dfbz.dao.ComsOrderMapper;
import com.dfbz.domain.*;
import com.dfbz.dto.AppointmentDto;
import com.dfbz.dto.OrderItemView;
import com.dfbz.service.ComsOrderItemService;
import com.dfbz.service.impl.SearchServiceImpl;
import com.dfbz.util.ResponseUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@RequestMapping("/admin/search")
public class AdminSearchController {

    @Resource
    private SearchServiceImpl searchService;

    @Resource
    private ComsOrderMapper orderMapper;

    @Resource
    private ComsOrderItemService orderItemService;

    private static final String DEFAULT_PAGE_SIZE = "12";

    @RequestMapping("/product")
    public Object searchByProDate(@RequestBody AppointmentDto appointmentDto) {
        List<ComsProduct> products =
                searchService.searchProByDate(appointmentDto);
        for (ComsProduct product : products) {
            System.out.println(product);
        }
        return ResponseUtil.ok(products);
    }

    @RequestMapping("/orders")
    public Object searchByOrdersDate(@RequestBody AppointmentDto appointmentDto) {

        List<ComsOrder> comsOrders = searchService.searchOrdersByDate(appointmentDto);
        for (ComsOrder comsOrder : comsOrders) {
            System.out.println(comsOrder);
        }
        return ResponseUtil.ok(comsOrders);
    }


    @RequestMapping("/queryOrderItem")
    public Object queryOrderItem(@RequestParam(value = "startDate") String startDate,
                                 @RequestParam(value = "endDate") String endDate,
                                 @RequestParam(value = "supId") Integer supId,
                                 @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize) {

        String[] split = startDate.split("-");
        String[] split1 = endDate.split("-");

        split[2] = split[2].length()==1?"0"+split[2]:split[2];
        split[1] = split[1].length()==1?"0"+split[1]:split[1];
        split1[2] = split1[2].length()==1?"0"+split1[2]:split1[2];
        split1[1] = split1[1].length()==1?"0"+split1[1]:split1[1];

        startDate = split[0]+"-"+split[1]+"-"+split[2]+" 00:00:00";
        endDate = split1[0]+"-"+split1[1]+"-"+split1[2]+" 00:00:00";

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDateTime = LocalDateTime.parse(startDate, dateTimeFormatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate, dateTimeFormatter);



        List<ComsOrderItem> comsOrderItems = orderItemService.queryOrderItems(startDateTime, endDateTime, supId);

        PageInfo pageInfo = new PageInfo(comsOrderItems);

        ModelAndView mv = new ModelAndView("allOrderItem_list");
        ComsAdmin admin = (ComsAdmin) SecurityUtils.getSubject().getPrincipal();
        mv.addObject("admin", admin);
        mv.addObject("pageInfo", pageInfo);
        return mv;
    }



    @RequestMapping("/queryOrderItemBySupIdAndCreateTime")
    public Object queryOrderItemBySupIdAndCreateTime(@RequestParam(value = "startDate") String startDate,
                                                     @RequestParam(value = "endDate") String endDate,
                                                     @RequestParam(value = "supId") Integer supId,
                                                     @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                     @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize){
        String[] split = startDate.split("-");
        String[] split1 = endDate.split("-");

        split[2] = split[2].length()==1?"0"+split[2]:split[2];
        split[1] = split[1].length()==1?"0"+split[1]:split[1];
        split1[2] = split1[2].length()==1?"0"+split1[2]:split1[2];
        split1[1] = split1[1].length()==1?"0"+split1[1]:split1[1];

        startDate = split[0]+"-"+split[1]+"-"+split[2]+" 00:00:00";
        endDate = split1[0]+"-"+split1[1]+"-"+split1[2]+" 00:00:00";

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDateTime = LocalDateTime.parse(startDate, dateTimeFormatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate, dateTimeFormatter);
        PageHelper.startPage(pageNum,pageSize);
        List<OrderItemView> orderItemViews = orderItemService.selectOrderItemBySupIdAndCreateTime(startDateTime,endDateTime,supId);
        PageInfo pageInfo = new PageInfo(orderItemViews);
        ModelAndView mv = new ModelAndView("queryorderItem_list");
        ComsAdmin admin = (ComsAdmin) SecurityUtils.getSubject().getPrincipal();
        mv.addObject("admin", admin);
        mv.addObject("pageInfo", pageInfo);
        return mv;
    }
}
