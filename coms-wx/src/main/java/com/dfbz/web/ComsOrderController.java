package com.dfbz.web;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dfbz.domain.*;
import com.dfbz.exception.OutOfStackException;
import com.dfbz.service.*;
import com.dfbz.util.ResponseUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/wx/order")
public class ComsOrderController {

    @Resource
    private ComsOrderService comsOrderService;

    @Resource
    private ComsOrderItemService comsOrderItemService;

    @Resource
    private ComsProductService productService;

    @Resource
    private ComsCartItemService cartItemService;

    @Resource
    private ComsSupCateService supCateService;

    @Resource
    private ComsSupplierService supplierService;

    @Resource
    private ComsStallService comsStallService;




    /**
     * 增加订单
     *
     * @param map
     * @return
     */
    @Transactional
    @PostMapping(value = "/insert")
    public Object insert(@RequestBody Map<String, Object> map) {

        String pTypeJson = JSON.toJSONString(map.get("pType"));
        boolean pType = JSON.parseObject(pTypeJson, boolean.class);
        //购物车
        String cartItemsJson = null;
        //商品
        String productJson = null;

        if (pType) {
            cartItemsJson = new JSONObject().toJSONString(map.get("cartItems"));
        } else {
            productJson = new JSONObject().toJSONString(map.get("product"));
        }

        //档口信息
        String stallJson = new JSONObject().toJSONString(map.get("stall"));
        ComsStall stall = new JSONObject().parseObject(stallJson, ComsStall.class);

        String totalJson = new JSONObject().toJSONString(map.get("amountTotal"));
        BigDecimal total = new JSONObject().parseObject(totalJson, BigDecimal.class);

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        List<ComsOrderItem> orderItems = new ArrayList<>();
        List<ComsCartItem> cartItems = new ArrayList<>();
        List<ComsProduct> products = new ArrayList<>();
        System.out.println("cartItemsJson" + cartItemsJson);
        Map<Integer, ComsOrder> orders = new LinkedHashMap<>();
        if (cartItemsJson != null) {
            HashSet supSet = new HashSet();
            List<JSONObject> lists = JSONObject.parseObject(cartItemsJson, List.class);
            //存储供应商信息 开头为供应商id
            Integer i = 0;
            for (JSONObject list : lists) {
                int supCateId = productService.findById(list.getInteger("prodId")).getSupCateId();
                System.out.println("cupCateId:" + supCateId);
                //供应商的iD
                int currentSupId = supCateService.findById(supCateId).getSupId();
                //需要的话 加上
                ComsSupplier curSup = supplierService.findById(currentSupId);
                ComsOrder order = null;
                if (!supSet.contains(currentSupId)) {
                    order = new ComsOrder();
                    order.setStallId(stall.getId());
                    order.setAmountTotal(new BigDecimal(0));
                    order.setStatus(1);
                    order.setSupName(curSup.getName());
                    order.setSupPhone(curSup.getPhone());
                    order.setCreateTime(LocalDateTime.now());
                    order.setUpdateTime(LocalDateTime.now());
                    order.setSupId(currentSupId);
                    System.out.println(order);
                    orders.put(currentSupId, order);
                    comsOrderService.insert(order);
                    supSet.add(currentSupId);
                }
                ComsCartItem item = new ComsCartItem();
                item.setId(list.getInteger("id"));
                item.setName(list.getString("name"));
                item.setNum(list.getInteger("num"));
                item.setCartId(list.getInteger("cartId"));
                item.setProdId(list.getInteger("prodId"));
                item.setPicUrl(list.getString("picUrl"));
//                order
//                System.out.println("-----------------------------"+list.getString("picUrl"));
                System.out.println(list.getString("addTime"));
//                item.setAddTime(LocalDateTime.parse(list.getString("addTime"), df));
                item.setPrice(list.getBigDecimal("price"));
                item.setChecked(list.getBoolean("checked"));
                cartItems.add(item);

            }
            for (ComsCartItem cartItem : cartItems) {
                int supCateId = productService.findById(cartItem.getProdId()).getSupCateId();
                //供应商的iD
                int currentSupId = supCateService.findById(supCateId).getSupId();
                ComsOrder order = orders.get(currentSupId);
                ComsOrderItem item = new ComsOrderItem();
                item.setProdId(cartItem.getProdId());
                item.setCurrentMoney(cartItem.getPrice());
                item.setNum(cartItem.getNum());

                item.setCreateTime(LocalDateTime.now());
                item.setUpdateTime(LocalDateTime.now());
                item.setOrderId(order.getId());
                order.setAmountTotal(order.getAmountTotal().add(item.getCurrentMoney().multiply(new BigDecimal(item.getNum()))));
                orders.put(currentSupId, order);
                comsOrderService.updateOrder(order);
                item.setProdName(cartItem.getName());
//                System.out.println("--------------"+cartItem.getPicUrl());
                item.setProdPicUrl(cartItem.getPicUrl());
                ComsProduct product = productService.findById(cartItem.getProdId());
                //价格不一致
                int result = product.getPrice().compareTo(item.getCurrentMoney());
//                System.out.println(result);
                boolean flag = result == 0 ? false : true;
                if (flag) {
                    for (Integer key : orders.keySet()) {
                        comsOrderService.delete(orders.get(key).getId());
                    }
//                    Map<String,Object> prodMap = new LinkedHashMap<>();
//                    prodMap.put("prodId",product.getId());
//                    prodMap.put("prodName",product.getName());
                    return ResponseUtil.serious();
                }
                //下架状态
                if (product.getStatus() != 1) {
                    for (Integer key : orders.keySet()) {
                        comsOrderService.delete(orders.get(key).getId());
                    }
/*                    Map<String, Object> prodMap = new LinkedHashMap<>();
                    prodMap.put("prodId", product.getId());
                    prodMap.put("prodName", );*/
                    return ResponseUtil.soldOut1(item.getProdName());
                }
                //库存不够
                if (product.getStock() - item.getNum() >= 0) {
                    product.setStock(product.getStock() - item.getNum());
                    products.add(product);
                } else {
                    //删除所有order 返回错误信息
                    for (Integer key : orders.keySet()) {
                        comsOrderService.delete(orders.get(key).getId());
                    }
/*                    Map<String, Object> prodMap = new LinkedHashMap<>();
                    prodMap.put("prodId", product.getId());
                    prodMap.put("prodName", item.getProdName());*/
                    return ResponseUtil.outOfStock1(item.getProdName());
//                    throw new OutOfStackException("库存不足");
                }

                orderItems.add(item);
            }


        } else if (productJson != null) {

            ComsProduct product = new JSONObject().parseObject(productJson, ComsProduct.class);
            int currentSupId = supCateService.findById(product.getSupCateId()).getSupId();
            //需要的话 加上
            ComsSupplier curSup = supplierService.findById(currentSupId);
            ComsOrder order = new ComsOrder();
            order.setStallId(stall.getId());
            order.setAmountTotal(total);
            order.setStatus(1);
            order.setSupName(curSup.getName());
            order.setSupPhone(curSup.getPhone());
            comsOrderService.insert(order);
            ComsOrderItem item = new ComsOrderItem();
            item.setProdId(product.getId());
            item.setCurrentMoney(product.getPrice());
            item.setNum(1);
            item.setOrderId(order.getId());
            item.setProdName(product.getName());
            item.setProdPicUrl(product.getPicUrl());
            if (product.getStock() - item.getNum() >= 0) {
                product.setStock(product.getStock() - item.getNum());
                products.add(product);
            } else {
                throw new OutOfStackException("库存不足");
            }
            orderItems.add(item);
        } else {
            return null;
        }

        int result = comsOrderItemService.addOrderItems(orderItems);
        if (result > 0) {
            for (ComsCartItem cartItem : cartItems) {
                cartItemService.purchaseByProdId(stall, cartItem.getId());
            }
            for (ComsProduct product : products) {
                productService.update(product);
            }
        }
        return ResponseUtil.ok(orderItems);
    }

    /**
     * 查询所有订单
     *
     * @return
     */
    @GetMapping(value = "/getAllOrders")
    public Object getAllOrders(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
            , @RequestParam(value = "pageSize", defaultValue = "2") Integer pageSize) {
        Page<ComsOrder> page = PageHelper.startPage(pageNum, pageSize);
        List<ComsOrder> comsOrders = comsOrderService.selectByExample(null);
        PageInfo<ComsOrder> pageInfo = new PageInfo<>(comsOrders);
        return ResponseUtil.ok(pageInfo);
    }

    /**
     * 根据用户id查询所有订单
     *
     * @param map
     * @return
     */
    @PostMapping(value = "/getOrdersByStallId")
    public Object getOrdersByStallId(@RequestBody Map map) {
        String stallJson = new JSONObject().toJSONString(map.get("stall"));
        ComsStall stall = new JSONObject().parseObject(stallJson, ComsStall.class);
        List<ComsOrder> comsOrdersByStallId = comsOrderService.selectByPrimaryStallId(stall.getId());
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("orders", comsOrdersByStallId);
        for (ComsOrder order : comsOrdersByStallId) {
            List<ComsOrderItem> orderItems = comsOrderItemService.selectByOrderId(order.getId());
            data.put(String.valueOf(order.getId()), orderItems);
        }
        return ResponseUtil.ok(data);
    }

    /**
     * 根据供应商id查询所有订单
     *
     * @param supplier
     * @return
     */
    @RequestMapping(value = "/getOrdersBySupplierId")
    public Object getOrdersBySupplierId(@RequestParam(value = "supplier") String supplier) {
        //String supplierlJson = new JSONObject().toJSONString(map.get("supplier"));
        Integer supplierId = Integer.parseInt(supplier);
        //ComsSupplier supplier = new JSONObject().parseObject(supplier, ComsSupplier.class);
        List<ComsOrder> comsOrdersBySupplierId = comsOrderService.selectByPrimarySupplierId(supplierId);
        System.out.println(comsOrdersBySupplierId);
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("orders", comsOrdersBySupplierId);
        for (ComsOrder order : comsOrdersBySupplierId) {
            List<ComsOrderItem> orderItems = comsOrderItemService.selectByOrderId(order.getId());
            data.put(String.valueOf(order.getId()), orderItems);
        }
        return ResponseUtil.ok(data);
    }


    /**
     * 创建订单
     *
     * @param comsCart
     * @param comsStall
     * @return
     */
    @PostMapping(value = "/createOrder")
    public Object createOrder(@RequestBody ComsCart comsCart, @RequestBody ComsStall comsStall) {
        //获取用户id
        Integer stallId = comsStall.getId();
        //调用方法生成订单
        Integer order = comsOrderService.createOrder(comsCart, stallId);
        return ResponseUtil.ok("操作成功", order);
    }

    @GetMapping(value = "/selectErrOrderByOrderId")
    public Object selectErrOrderByOrderId() {
        List<ComsErrOrder> comsErrOrders = comsOrderItemService.selectErrOrderByOrderId();
        return ResponseUtil.ok(comsErrOrders);
    }

    /**
     * 根据用户id查询所有订单项
     *
     * @param stallId
     * @return
     */
    @GetMapping(value = "/getOrderItem")
    public Object orderItem(Integer stallId) {
        Object orderItem = comsOrderItemService.orderItem(stallId);
        return ResponseUtil.ok(orderItem);
    }

    /**
     * 根据proId查询所有订单
     *
     * @return
     */
    @GetMapping(value = "/getAllOrdersByProdId")
    public Object getAllOrdersByProdId(@RequestBody Map map) {
        String orderJson = new JSONObject().toJSONString(map.get("order"));
        ComsOrder order = new JSONObject().parseObject(orderJson, ComsOrder.class);
        ComsOrderExample comsOrderExample = new ComsOrderExample();
        comsOrderExample.or().andSupNameEqualTo(order.getSupName());
        List<ComsOrder> comsOrders = comsOrderService.selectByExample(comsOrderExample);
        Map<String, Object> data = new LinkedHashMap<>();
        for (ComsOrder order1 : comsOrders) {
            List<ComsOrderItem> comsOrderItems = comsOrderItemService.selectByOrderId(order1.getId());
            data.put(String.valueOf(order1.getId()), comsOrderItems);
        }
        return ResponseUtil.ok(data);
    }

    /**
     * 根据用户id查询今日订单
     *
     * @param map
     * @return
     */
    @PostMapping(value = "/getPayOrdersByStallId")
    public Object getPayOrdersByStallId(@RequestBody Map map) {
        String stallJson = new JSONObject().toJSONString(map.get("stall"));
        ComsStall stall = new JSONObject().parseObject(stallJson, ComsStall.class);
        String idsJson = new JSONObject().toJSONString(map.get("ids"));
        idsJson = idsJson.replaceAll("[\"\\[\\]]", "");
        String[] ids = idsJson.split(",");
        Map<String, Object> data = new LinkedHashMap<>();
        List<String> idss = new LinkedList<>();
        for (String id : ids) {
            idss.add(id);
            data.put(id, comsOrderItemService.selectByOrderId(Integer.parseInt(id)));
        }
        data.put("idss", idss);
        return ResponseUtil.ok(data);
    }


    /**
     * 根据商品id查询用户
     *
     * @param orderId
     * @return
     */
    @GetMapping(value = "/getStallByorderId/{orderId}")
    public Object getStallByorderId(@PathVariable Integer orderId) {
        ComsOrderExample orderExample = new ComsOrderExample();
        orderExample.or().andIdEqualTo(orderId);
        List<ComsOrder> comsOrders = comsOrderService.selectByExample(orderExample);
        Integer stallId = 0;
        for (ComsOrder co : comsOrders) {
            stallId = co.getStallId();
        }
        ComsStall stall = comsStallService.findById(stallId);
        return ResponseUtil.ok(stall);
    }

    //COmsOrderController第三版

    /**
     * 订单
     */
    @RequestMapping("/getOrdersById")
    public Object getOrders(Integer stallId) {
        List<ComsOrder> errOrders = comsOrderService.findErrorOrdersByStallId(stallId);
        List<ComsOrder> normalOrders = comsOrderService.findNormalOrdersByStallId(stallId);
        List<ComsOrder> todayOrders = comsOrderService.findTodayOrdersByStallId(stallId);
        Map<String, Object> map = new LinkedHashMap<>();
        Map<String, Object> orders = new LinkedHashMap<>();
        Map<String, Object> orderItems = new LinkedHashMap<>();
        orders.put("errOrders", errOrders);
        orders.put("normalOrders", normalOrders);
        orders.put("todayOrders", todayOrders);
        Map<Integer, Object> errOrderItems = new LinkedHashMap<>();
        Map<Integer, Object> normalOrderItems = new LinkedHashMap<>();
        Map<Integer, Object> todayOrderItems = new LinkedHashMap<>();
        for (ComsOrder errOrder : errOrders) {
            errOrderItems.put(errOrder.getId(), comsOrderItemService.selectByOrderId(errOrder.getId()));
        }
        for (ComsOrder normalOrder : normalOrders) {
            normalOrderItems.put(normalOrder.getId(), comsOrderItemService.selectByOrderId(normalOrder.getId()));
        }
        for (ComsOrder todayOrder : todayOrders) {
            todayOrderItems.put(todayOrder.getId(), comsOrderItemService.selectByOrderId(todayOrder.getId()));
        }
        orderItems.put("errOrderItems", errOrderItems);
        orderItems.put("normalOrderItems", normalOrderItems);
        orderItems.put("todayOrderItems", todayOrderItems);
        map.put("orders", orders);
        map.put("orderItems", orderItems);
        return map;
    }

    @RequestMapping("/getOrderItemsByOrderId")
    public Object getOrderItemsByOrderId(Integer orderId){
        return comsOrderItemService.selectByOrderId(orderId);
    }


}
