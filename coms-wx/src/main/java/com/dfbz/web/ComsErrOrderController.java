package com.dfbz.web;


import com.alibaba.fastjson.JSON;
import com.dfbz.dao.ComsOrderItemMapper;
import com.dfbz.domain.ComsErrOrder;
import com.dfbz.domain.ComsOrder;
import com.dfbz.domain.ComsOrderExample;
import com.dfbz.service.ComsErrOrderService;
import com.dfbz.service.ComsOrderService;
import com.dfbz.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dfbz.util.File.uploadFile;

@RestController
@RequestMapping(value = "/wx/errorder")
public class ComsErrOrderController {
    private static final String FILE_PATH = "static/uploadfiles/";
    @Resource
    private ComsErrOrderService comsErrOrderService;

    @Resource
    private ComsOrderService orderService;

    @Resource
    private ComsOrderItemMapper orderItemMapper;
    @Resource
    private ComsErrOrderService errOrderService;

    @PostMapping(value = "/insert")
    public Object ComsErrOrder(@RequestBody Map<String, Object> map) {
//        comsErrOrder.setErrMsg("商品破损");
//        comsErrOrder.setOrderId(4);
        String orderItemIdJson = JSON.toJSONString(map.get("orderItemId"));
        Integer orderItemId = JSON.parseObject(orderItemIdJson, Integer.class);
        String errOrderJson = JSON.toJSONString(map.get("errOrder"));
        System.out.println(map.get("errOrder"));
        System.out.println(map.get("orderItemId"));
        ComsErrOrder errOrder = JSON.parseObject(errOrderJson, ComsErrOrder.class);
        System.out.println(orderItemMapper.selectByPrimaryKey(orderItemId));
        errOrder.setOrderId(orderItemMapper.selectByPrimaryKey(orderItemId).getOrderId());
        int i = comsErrOrderService.insertSelective(errOrder);


        //订单状态设置为-1
        orderService.updateOrder(errOrder.getOrderId());
        return ResponseUtil.ok("添加成功", i);
    }

    @GetMapping("/findAll")
    public Object findAllErrOrder() {
        List<ComsErrOrder> allErrOrders = comsErrOrderService.queryAllErrOrders();
        return ResponseUtil.ok(allErrOrders);
    }

    @PostMapping("/findByOrderId")
    public Object findByOrderId(@RequestBody Integer orderId) {
        return ResponseUtil.ok(comsErrOrderService.queryByOrderId(orderId));
    }

    @PostMapping(value = "/findByOrderItemId")
    public Object findByOrderItemId(@RequestBody Map<String, Object> map) {

        String orderItemIdJson = JSON.toJSONString(map.get("orderItemId"));
        Integer orderItemId = JSON.parseObject(orderItemIdJson, Integer.class);

        Integer orderId = orderItemMapper.selectByPrimaryKey(orderItemId).getOrderId();
        return ResponseUtil.ok(comsErrOrderService.queryByOrderId(orderId));
    }

    /**
     * 第二版 接口方法
     */
    @ResponseBody
    @RequestMapping("/upload")
    public Object upload(MultipartFile content, String errMsg, String errImg, Integer orderId, Integer orderItemId, Integer stallId) throws Exception {
        ComsErrOrder errOrder = new ComsErrOrder();
        errOrder.setOrderId(orderId);
        errOrder.setErrMsg(errMsg);
        errOrder.setErrImg(errImg);
        errOrder.setStallId(stallId);
        System.out.println(errOrder);
        if (!content.isEmpty()) {
            String fileName = content.getOriginalFilename();
            String type = null;
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (type != null) {
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                    String filePath = ResourceUtils.getURL("classpath:").getPath().substring(1) + FILE_PATH;
                    uploadFile(content.getBytes(), filePath, errOrder.getErrImg());
                } else {
                    return ResponseUtil.errImgType();
                }
            } else {
                return ResponseUtil.errImgType();
            }
        }
        comsErrOrderService.insertSelective(errOrder);
        orderService.updateOrder(errOrder.getOrderId());
        return ResponseUtil.ok();
    }

    @ResponseBody
    @RequestMapping("/uploadWithoutImg")
    public Object uploadWithoutImg(@RequestBody ComsErrOrder errOrder) throws Exception {
        System.out.println(errOrder);
        comsErrOrderService.insertSelective(errOrder);
        orderService.updateOrder(errOrder.getOrderId());
        return ResponseUtil.ok();
    }

    //ComsErrOrderCOntroller第三版
    @RequestMapping("getByOrderId")
    public Object getByOrderId(Integer orderId) {
        ComsOrderExample example = new ComsOrderExample();
        example.or().andIdEqualTo(orderId);
        List<ComsOrder> comsOrders = orderService.selectByExample(example);
        Map<String, Object> map = new HashMap<>();
        map.put("order", comsOrders.get(0));
        map.put("errOrder", errOrderService.queryByOrderId(orderId));
        return ResponseUtil.ok(map);
    }


}
