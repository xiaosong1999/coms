package com.dfbz.web;

import com.alibaba.fastjson.JSONObject;
import com.dfbz.dao.ComsProductMapper;
import com.dfbz.domain.*;
import com.dfbz.service.*;
import com.dfbz.util.ResponseUtil;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.security.PrivateKey;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.dfbz.util.File.delFile;
import static com.dfbz.util.File.uploadFile;


@RestController
@RequestMapping("/wx/product")
public class ComsProductController {

    private static final String FILE_PATH = "static/uploadfiles/";

    @Resource
    ComsProductService productService;

    @Resource
    ComsPriceListService priceListService;

    @Resource
    ComsCategoryService categoryService;

    @Resource
    ComsSupCateService supCateService;

    @Resource
    ComsStallService stallService;

    @Resource
    ComsOrderService orderService;

    @Resource
    ComsOrderItemService orderItemService;

    @Resource
    ComsProductService productService1;

    /***
     * 定时下架商品
     */

    private static Timer timer = new Timer();

     {
        // 每天11：30执行数据库备份任务
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 24); // 控制时
        calendar.set(Calendar.MINUTE, 00);       // 控制分
        calendar.set(Calendar.SECOND, 00);       // 控制秒

        Date time = calendar.getTime();         // 得出执行任务的时间,此处为今天的24:00:00

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                productService1.offTheProduct();
            }
        }, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行

    }


    /***
     * 按id查商品
     * @param id
     * @return
     */
    @RequestMapping("findById")
    public ComsProduct findById(@RequestParam(name = "id") Integer id) {
        return productService.findById(id);
    }

    /***
     * 显示所有商品
     * @return
     */
    @RequestMapping("listAll")
    public List<ComsProduct> listAll() {
        return productService.listAll();
    }


    /***
     * 添加商品
     * @param
     * @return
     *//*
    @RequestMapping("add")
    public Object add(@RequestBody Map map) {
        String productJson = new JSONObject().toJSONString(map.get("product"));
        ComsProduct product = new JSONObject().parseObject(productJson, ComsProduct.class);

        DateTimeFormatter DateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime createTime = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        product.setCreateTime(createTime);
        productService.add(product);
        //新商品价格写入price_list
        ComsPriceList priceList = new ComsPriceList();
        priceList.setProdId(product.getId());
        priceList.setProdName(product.getName());
        priceList.setPrice(product.getPrice());
        priceList.setSupId(product.getSupCateId());

        //写入product表
        priceListService.addPrice(priceList);
        return ResponseUtil.ok();
    }

    @RequestMapping("update")
    public Object update(@RequestBody Map map) {
        String productJson = new JSONObject().toJSONString(map.get("product"));
        ComsProduct product = new JSONObject().parseObject(productJson, ComsProduct.class);
        //获取当前时间更新update
        DateTimeFormatter DateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime updateTime = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        product.setCreateTime(updateTime);
        //新价格写入price_list
        ComsPriceList priceList = new ComsPriceList();
        priceList.setProdId(product.getId());
        priceList.setProdName(product.getName());
        priceList.setPrice(product.getPrice());
        priceList.setSupId(product.getSupCateId());
        priceList.setCreateTime(updateTime);
        priceListService.addPrice(priceList);
        //修改price表
        return ResponseUtil.ok(productService.update(product));
    }*/
    @RequestMapping("findProByCate")
    public Object findProByCate(@RequestParam(value = "id") Integer id) {
        List<ComsSupCategory> supCates = supCateService.findByCategory(id);
        List<ComsProduct> products = new ArrayList<>();
        for (ComsSupCategory supCate : supCates) {
            List<ComsProduct> products1 = productService.queryByCategory(supCate.getId(), 0, 10);
            for (ComsProduct product : products1) {
                products.add(product);
            }
        }
        return ResponseUtil.ok(products);
    }

    /**
     * 查询所有价格列表
     *
     * @return
     */
    @RequestMapping(value = "/getAllPriceList")
    public Object getAllPriceList() {
        List<ComsPriceList> comsPriceLists = priceListService.queryAllPriceList();
        return ResponseUtil.ok(comsPriceLists);
    }


    /**
     * 根据产品id查询价格表
     *
     * @param prodId
     * @return
     */
    @GetMapping(value = "/queryPriceListByProId")
    public Object queryPriceListByProId(@RequestParam(value = "prodId") Integer prodId) {
        Map<String, Object> map = new LinkedHashMap<>();
        //根据proId查询价格列表
        List<ComsPriceList> comsPriceLists = priceListService.queryPriceListByProId(prodId);
        //查询某种商品的最新报价
        ComsPriceList latestPricePriceList = priceListService.queryLatestPrice(prodId);
        for (ComsPriceList comsPriceList : comsPriceLists) {
            ComsProduct product = productService.findById(comsPriceList.getProdId());
            map.put("product", product);
        }
        map.put("latestPricePriceList", latestPricePriceList);
        map.put("comsPriceLists", comsPriceLists);

        return ResponseUtil.ok(map);
    }

    /**
     * 根据产品id查询价格表
     *
     * @param prodId
     * @return
     */
    @GetMapping(value = "/queryPriceListByProIdandSupId")
    public Object queryPriceListByProIdandSupId(@RequestParam(value = "prodId") Integer prodId,
                                                @RequestParam(value = "supId") Integer supId) {
        Map<String, Object> map = new LinkedHashMap<>();
        //根据proId查询价格列表
        List<ComsPriceList> comsPriceLists = priceListService.queryPriceListByProIdAndSupId(prodId, supId);
        for (ComsPriceList comsPriceList : comsPriceLists) {
            ComsProduct product = productService.findById(comsPriceList.getProdId());
            map.put(String.valueOf(comsPriceList.getProdId()), product);
        }
        //查询某种商品的最新报价
        ComsPriceList latestPricePriceList = priceListService.queryLatestPrice(prodId);
        map.put("latestPricePriceList", latestPricePriceList);
        map.put("comsPriceLists", comsPriceLists);

        return ResponseUtil.ok(map);
    }

    /**
     * 根据供应商id查询价格表
     *
     * @param
     * @return
     */
    @GetMapping(value = "/queryPriceListBySupId")
    public Object queryPriceListBySupId(@RequestParam(value = "supId") Integer supId) {
        Map<String, Object> map = new LinkedHashMap<>();
        //根据supId查询价格列表
        List<ComsPriceList> comsPriceLists = priceListService.queryPriceListBySupId(supId);
/*        for (ComsPriceList comsPriceList : comsPriceLists) {
            ComsProduct product = productService.findById(comsPriceList.getProdId());
            map.put(String.valueOf(comsPriceList.getProdId()), product);
        }*/
        map.put("comsPriceLists", comsPriceLists);
        return ResponseUtil.ok(map);
    }

    /**
     * 根据产品名称模糊查询
     */
    @GetMapping(value = "/queryProductByName")
    public Object queryProductByName(@RequestParam(value = "name") String name) {
        List<ComsProduct> comsProducts = productService.queryProductByName(name);
        return ResponseUtil.ok(comsProducts);
    }


    /**
     * 根据产品名称模糊查询指定id供应商
     */
    @GetMapping(value = "/queryProductByNameAndId")
    public Object queryProductByName(@RequestParam(value = "name") String name,
                                     @RequestParam(value="id")Integer id) {
        List<ComsProduct> comsProducts = productService.queryProductByNameAndId(name,id);
        return ResponseUtil.ok(comsProducts);
    }





    /**
     * 根据供应商id查询价格表
     *
     * @param
     * @return
     */
    @GetMapping(value = "/queryStallBySupId")
    public Object queryStallBySupId(@RequestParam(value = "supId") Integer supId) {
        Map<String, Object> map = new LinkedHashMap<>();
        //根据supId查询分类
        List<ComsSupCategory> supCategories = (List<ComsSupCategory>) supCateService.findCateBySup(supId);
        List<List<ComsOrderItem>> ComsOrderItems = new ArrayList<>();
        for (ComsSupCategory comsCategory : supCategories) {
            List<ComsProduct> products = productService.findByCateId(comsCategory.getId());
            List<List<ComsPriceList>> ComsPriceLists = new ArrayList<>();

            for (ComsProduct product : products) {
                System.out.println(product);
                List<ComsPriceList> comsPriceList = priceListService.queryPriceListByProId(product.getId());
                ComsPriceLists.add(comsPriceList);
                List<ComsOrderItem> comsOrderItem = orderItemService.selectByProId(product.getId());
                System.out.println("ddddddddddds" + comsOrderItem.size());
                ComsOrderItems.add(comsOrderItem);
                List<ComsStall> stalls = new ArrayList<>();
                for (ComsOrderItem comsOrderIt : comsOrderItem) {
                    ComsOrderExample example = new ComsOrderExample();
                    example.or().andIdEqualTo(comsOrderIt.getOrderId());
                    List<ComsOrder> comsOrders = orderService.selectByExample(example);
                    for (ComsOrder comsOrder : comsOrders) {
                        ComsStall stall = stallService.findById(comsOrder.getStallId());
                        System.out.println("-----------------------------------------------" + stall);
                        stalls.add(stall);
                    }
                    map.put("stall", stalls);
                }
            }
            map.put("comsOrderItems", ComsOrderItems);
            map.put("priceList", ComsPriceLists);
            map.put("products", products);
        }
        return ResponseUtil.ok(map);
    }

    /***
     * 今日报价
     * @param map
     * @return
     */
    @RequestMapping("/todayPrice")
    public Object todayPrice(@RequestBody Map map) {
        String todayPriceJSON = new JSONObject().toJSONString(map.get("productList"));
        List<String> todayPriceId = new JSONObject().parseObject(todayPriceJSON, List.class);
        List<ComsProduct> productList = new ArrayList();
        for (String element : todayPriceId) {
            System.out.println(element);
            ComsProduct product = productService.findById(Integer.parseInt(element));
            productList.add(product);
        }
        return ResponseUtil.ok(productList);
    }


    /***
     * 修改上架下架状态
     */
    @RequestMapping("updateStatus")
    public Integer updateStatus(Integer id) {
        ComsProduct product = productService.findById(id);
        if (product.getStatus() == 0) {
            product.setStatus(1);
        } else {
            product.setStatus(0);
        }
        return productService.update(product);
    }

    /**
     * 根据供应商id查询价格表
     *
     * @param
     * @return
     */
    @GetMapping(value = "/queryStallAndOrderItemBySupId")
    public Object queryStallAndOrderItemBySupId(@RequestParam(value = "supId") Integer supId, @RequestParam(value = "supName") String supName) {

        List<ComsProduct> products = new ArrayList<>();

        Map<String, Map<String, Integer>> map = new HashMap<>();
        Map<String, Integer> detail = new HashMap<>();
        ComsOrderExample example = new ComsOrderExample();
        example.or().andSupNameEqualTo(supName).andUpdateTimeGreaterThan(LocalDateTime.of(LocalDate.now(), LocalTime.MIN));
        List<ComsOrder> orders = orderService.selectByExample(example);
        if (orders.size() == 0) {
            return ResponseUtil.ok("没有订单");
        }
        ComsStall stall1 = stallService.findById(orders.get(0).getStallId());
        ComsProduct product1 = productService.findById(orderItemService.selectByOrderId(orders.get(0).getId()).get(0).getProdId());
        products.add(product1);
        List<ComsOrderItem> orderItems1 = orderItemService.selectByOrderId(orders.get(0).getId());
        Map<String, Integer> map1 = new HashMap<>();
        map1.put(stall1.getName(), 0);
        map.put(product1.getName(), map1);
//        System.out.println(product1.getName()+"--------------"+stall.getName()+"----------"+map.get(product.getName()).get(stall.getName()));
        for (ComsOrder order : orders) {
            ComsStall stall = stallService.findById(order.getStallId());

            List<ComsOrderItem> orderItems = orderItemService.selectByOrderId(order.getId());
            for (ComsOrderItem orderItem : orderItems) {
                ComsProduct product = productService.findById(orderItem.getProdId());
                for (int i = 0; i < products.size(); i++) {
                    if (products.get(i).getId().equals(product.getId())) {
                        if (map.get(product.getName()).get(stall.getName()) != null) {
                            Integer tampNum = map.get(product.getName()).get(stall.getName());
                            Integer currentNum = tampNum + orderItem.getNum();
                            Map<String, Integer> map2 = new HashMap<>();
                            map2.put(stall.getName(), currentNum);
                            map.put(product.getName(), map2);
                            i = products.size();
//                            System.out.println(product.getName()+"--------------"+stall.getName()+"----------"+map.get(product.getName()).get(stall.getName()));

                        } else {
                            Map<String, Integer> map2 = new HashMap<>();
                            Map<String, Integer> stringIntegerMap = map.get(product.getName());
                            Set<String> strings = stringIntegerMap.keySet();
                            for (String str : strings) {
                                map2.put(str, stringIntegerMap.get(str));
                            }
                            map2.put(stall.getName(), orderItem.getNum());
                            map.put(product.getName(), map2);
                            i = products.size();
//                            System.out.println(product.getName()+"--------------"+stall.getName()+"----------"+map.get(product.getName()).get(stall.getName()));

                        }
                    } else {
                        if (i == products.size() - 1) {
                            products.add(product);
                            Map<String, Integer> map2 = new HashMap<>();
                            map2.put(stall.getName(), orderItem.getNum());
                            map.put(product.getName(), map2);
//                            System.out.println(product.getName()+"--------------"+stall.getName()+"----------"+map.get(product.getName()).get(stall.getName()));
                        }
                    }
                }
            }
        }
        Map<String,Map<String,String>> newMap = new HashMap<>();
        for (String s : map.keySet()) {
            Map<String,String> map2 = new HashMap<>();
            int total = 0;
            for (String s1 : map.get(s).keySet()) {
                map2.put(s1,String.valueOf(map.get(s).get(s1))+productService.queryProductByName(s).get(0).getMeasure());
            }
            newMap.put(s,map2);
        }
        return ResponseUtil.ok(newMap);
    }


    @RequestMapping("/upDatePrice/{supId}")
    public Object upDatePrice(@RequestBody Map map, @PathVariable Integer supId) {
        String comsProductsJson = new JSONObject().toJSONString(map.get("comsProducts"));
        List<JSONObject> objs = JSONObject.parseObject(comsProductsJson, List.class);
        List<ComsProduct> comsProducts = new ArrayList<>();
        for (JSONObject obj : objs) {
            ComsProduct comsProduct = new ComsProduct();
            comsProduct.setId(obj.getInteger("id"));
            comsProduct.setPrice(obj.getBigDecimal("price"));
            comsProduct.setStock(obj.getInteger("stock"));
            comsProduct.setStatus(obj.getInteger("status"));

            productService.update(comsProduct);
            //新商品价格写入price_list
            ComsPriceList priceList = new ComsPriceList();
            priceList.setProdId(obj.getInteger("id"));
            priceList.setProdName(obj.getString("name"));
            priceList.setPrice(obj.getBigDecimal("price"));
            priceList.setSupId(supId);
            //写入product表
            priceListService.addPrice(priceList);
        }
        return ResponseUtil.ok();
    }


/**
 * 第二版 接口方法
 */

    /**
     * 添加商品方法
     */
    @ResponseBody
    @RequestMapping("add")
    public Object add(MultipartFile content, String name, BigDecimal price, String measure, String picUrl, String pd, Integer fd, Integer stock, Integer status, String brand, Integer supCateId) throws Exception {
        ComsProduct product = new ComsProduct();
        product.setName(name);
        product.setPrice(price);
        product.setMeasure(measure);
        product.setPicUrl(picUrl);
        product.setPd(LocalDate.parse(pd, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        product.setFd(fd);
        product.setStock(stock);
        product.setStatus(status);
        product.setBrand(brand);

        product.setSupCateId(supCateId);
        System.out.println(product);
        if (!content.isEmpty()) {
            String fileName = content.getOriginalFilename();
            String type = null;
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (type != null) {
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                    String filePath = ResourceUtils.getURL("classpath:").getPath().substring(1) + FILE_PATH;
                    uploadFile(content.getBytes(), filePath, product.getPicUrl());
                } else {
                    return ResponseUtil.errImgType();
                }
            } else {
                return ResponseUtil.errImgType();
            }
        }
        productService.add(product);
       /* ComsPriceList priceList = new ComsPriceList();
        priceList.setProdId(product.getId());
        priceList.setProdName(product.getName());
        priceList.setPrice(product.getPrice());
        priceList.setSupId(product.getSupCateId());

        //写入product表
        priceListService.addPrice(priceList);*/
        return ResponseUtil.ok();
    }

    /**
     * 修改商品方法(有图)
     *
     * @return
     */
    @RequestMapping("update")
    public Object update(MultipartFile content, Integer id, String name, BigDecimal price, String measure, String picUrl, String pd, Integer fd, Integer stock, Integer status, String brand, Integer supCateId) throws Exception {
        ComsProduct product = new ComsProduct();

        ComsProduct originalProduct = productService.findById(id);
        System.out.println(originalProduct.getPicUrl());
        if (originalProduct.getPicUrl().equals(picUrl)) {
            product.setId(id);
            product.setName(name);
            product.setPrice(price);
            product.setMeasure(measure);
            product.setPicUrl(picUrl);
            product.setPd(LocalDate.parse(pd, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            product.setFd(fd);
            product.setStock(stock);
            product.setStatus(status);
            product.setBrand(brand);
            product.setSupCateId(supCateId);
            System.out.println(product);
            productService.update(product);


        } else {
            String fileName = content.getOriginalFilename();
            String type = null;
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (type != null) {
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                    String filePath = ResourceUtils.getURL("classpath:").getPath().substring(1) + FILE_PATH;
                    product.setId(id);
                    product.setName(name);
                    product.setPrice(price);
                    product.setMeasure(measure);
                    product.setPicUrl(picUrl);
                    product.setPd(LocalDate.parse(pd, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    product.setFd(fd);
                    product.setStock(stock);
                    product.setStatus(status);
                    product.setBrand(brand);
                    product.setSupCateId(supCateId);
                    System.out.println(product);
                    productService.update(product);
                    uploadFile(content.getBytes(), filePath, product.getPicUrl());
                    delFile(filePath, originalProduct.getPicUrl());


                } else {
                    return ResponseUtil.errImgType();
                }
            } else {
                return ResponseUtil.errImgType();
            }
        }

        /*ComsPriceList priceList = new ComsPriceList();
        priceList.setProdId(product.getId());
        priceList.setProdName(product.getName());
        priceList.setPrice(product.getPrice());
        priceList.setSupId(product.getSupCateId());

        //写入product表
        priceListService.addPrice(priceList);*/

        return ResponseUtil.ok();
    }

    /**
     * 修改商品方法(无图)
     */
    @RequestMapping("updateWithoutImg")
    public Object updateWithoutImg(Integer id, String name, BigDecimal price, String measure, String picUrl, String pd, Integer fd, Integer stock, Integer status, String brand, Integer supCateId) throws Exception {
        ComsProduct product = new ComsProduct();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setMeasure(measure);
        product.setPicUrl(picUrl);
        product.setPd(LocalDate.parse(pd, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        product.setFd(fd);
        product.setStock(stock);
        product.setStatus(status);
        product.setBrand(brand);
        product.setSupCateId(supCateId);
        System.out.println(product);
        productService.update(product);
        /*ComsPriceList priceList = new ComsPriceList();
        priceList.setProdId(product.getId());
        priceList.setProdName(product.getName());
        priceList.setPrice(product.getPrice());
        priceList.setSupId(product.getSupCateId());

        //写入product表
        priceListService.addPrice(priceList);*/

        return ResponseUtil.ok();
    }


    /***
     * 显示所有商品
     * @return
     */
    @RequestMapping("listAllProduct")
    public List<ComsProduct> listAllProduct() {
        List list = productService.findOnSale();
        Collections.shuffle(list);
        return list;
    }

    /**
     * 关键字查商品
     *
     * @param map
     * @return
     */
    @PostMapping("findProductByKey")
    public List<ComsProduct> findProductByKey(@RequestBody Map map) {
        String keyword = (String) map.get("keyword");
        List list = productService.queryProductByKey(keyword);
        Collections.shuffle(list);
        return list;
    }


    @RequestMapping("uploading")
    public Object uploading(@RequestBody Map map) {
        System.out.println(map);
//        MultipartFile file = (MultipartFile) map.get("file");
        String picName = (String) map.get("images");
        System.out.println(picName);
//        String fileName = file.getOriginalFilename();
//        System.out.println(fileName);
        return null;

//        String filePath = null;
//        try {
//            filePath = ResourceUtils.getURL("classpath:").getPath().substring(1)+FILE_PATH;
//            uploadFile(file.getBytes(),filePath, picName);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("文件上传失败");
//            return ResponseUtil.fail();
//        }
//        System.out.println("文件上传成功");
//        return ResponseUtil.ok();
    }


    @RequestMapping("findProByCate2")
    public Object findProByCate2(@RequestParam(value = "id") Integer id) {
        List<ComsSupCategory> supCates = supCateService.findByCategory(id);
        List<ComsProduct> products = new ArrayList<>();
        for (ComsSupCategory supCate : supCates) {
            List<ComsProduct> products1 = productService.queryByCategory(supCate.getId());
            for (ComsProduct product : products1) {
                products.add(product);
            }
        }
        Collections.shuffle(products);
        return ResponseUtil.ok(products);
    }

    /***
     * 按供应商查询产品
     * @param id
     * @return
     */
    @RequestMapping("findProdBySup")
    public Object findProdBySup(@RequestParam Integer id) {
        List<ComsSupCategory> supCates = (List<ComsSupCategory>) supCateService.findCateBySup(id);
        List<ComsProduct> products = new ArrayList<>();
        for (ComsSupCategory supCate : supCates) {
            products.addAll(productService.findByCateId(supCate.getId()));
        }
        Collections.shuffle(products);
        return ResponseUtil.ok(products);
    }

    //更改status逻辑删除商品仓库的商品
    @RequestMapping("/deleteByUpdateStatus/{pidList}")
    public Integer deleteByUpdateStatus(@PathVariable(value = "pidList") String[] pidList) {
        return productService.deleteByUpdateStatus(pidList);
    }


    //删除今日报价
    @RequestMapping("deleteTodayPrice")
    public Object deleteTodayPrice(@RequestParam("productList") Integer[] prodId) {
        System.out.println(prodId);
        for (int i = 0; i < prodId.length; i++) {
            productService.deleteTodayPrice(prodId[i]);
        }

        return ResponseUtil.ok();
    }

    //更改status逻辑取消今日报价的商品
    @RequestMapping("/cancelTodayPriceStatus/{pidList}")
    public Integer cancelTodayPriceStatus(@PathVariable(value = "pidList") String[] pidList) {
        return productService.cancelTodayPriceStatus(pidList);
    }

}
