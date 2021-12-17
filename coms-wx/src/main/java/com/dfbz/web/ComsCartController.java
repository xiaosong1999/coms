package com.dfbz.web;

import com.alibaba.fastjson.JSONObject;
import com.dfbz.domain.*;
import com.dfbz.service.*;
import com.dfbz.util.ResponseUtil;
import static com.dfbz.util.WxResponseCode.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/wx/cart")
public class ComsCartController {

    @Resource
    private ComsCartItemService cartItemService;



    @Resource
    private ComsProductService productService;

    @RequestMapping("/index")
    public Object index(@RequestBody Map<String,Object> map){
        String stallJson = new JSONObject().toJSONString(map.get("stall"));
        ComsStall stall = new JSONObject().parseObject(stallJson, ComsStall.class);
        System.out.println(stall);
        List<ComsCartItem> items = cartItemService.queryCartProdForList(stall);
        if(items==null){
            return ResponseUtil.ok();
        }
        //所有商品数
        Integer allCheckedProdCount = 0;
        //选中的商品数
        Integer checkedProdCount = 0;
        //所有选中商品总价
        BigDecimal allCheckedProdAmount =new BigDecimal(0.00);
        //选中商品总价
        BigDecimal allProdAmount = new BigDecimal(0.00);
        //指定商品的数量
        Integer prodNum = 0;
        for(ComsCartItem item:items){
            allProdAmount = allProdAmount.add(item.getPrice().multiply(new BigDecimal(item.getNum())));
            allCheckedProdCount+=1;
            if(item.getChecked()){
                checkedProdCount+=1;
                allCheckedProdAmount =allCheckedProdAmount.add(item.getPrice().multiply(new BigDecimal(item.getNum())));
            }
        }
        Map<String,Object> result = new HashMap<>();
        result.put("cartItems",items);
        result.put("allCheckedProdCount",allCheckedProdCount);
        result.put("checkedProdCount",checkedProdCount);
        result.put("allCheckedProdAmount",allCheckedProdAmount);
        result.put("allProdAmount",allProdAmount);

        return ResponseUtil.ok(result);
    }

    /**
     * 添加商品
     * @param map  获取前端body中的值
     * @return
     */
    @RequestMapping("/add")
    public Object add(@RequestBody Map<String,Object> map){
        String prodJson = new JSONObject().toJSONString(map.get("product"));
        ComsProduct product = new JSONObject().parseObject(prodJson, ComsProduct.class);
        String stallJson = new JSONObject().toJSONString(map.get("stall"));
        ComsStall stall = new JSONObject().parseObject(stallJson, ComsStall.class);
        String numJson = new JSONObject().toJSONString(map.get("num"));
        Integer num = new JSONObject().parseObject(numJson, Integer.class);

        ComsProduct product1 = productService.findById(product.getId());
        if (product1 == null) {
            return ResponseUtil.fail(PROD_UNKNOWN,"没有该商品");
        }
        if (product1.getStatus()==0){
            return ResponseUtil.fail(PROD_UNSHELVE,"商品已下架");
        }
        if (product1.getStock()==0||num>product1.getStock()){
            return ResponseUtil.fail(PROD_NO_STOCK,"商品库存不足");
        }
//        int result = product.getPrice().compareTo(product1.getPrice());
//                System.out.println(result);
//        boolean flag = result==0?false:true;
//        if (flag){
//            product.setPrice(product1.getPrice());
//        }

        List<ComsCartItem> cartItems = cartItemService.queryCartProdForList(stall);
//        int index = 1;
        for(ComsCartItem cartItem:cartItems){
//            System.out.println(index+++"-prod:"+product.getId()+" -item"+
//                    cartItem.getProdId()+"==="+(cartItem.getCartId()==product.getId())+"--equals:"+
//                    cartItem.getProdId().equals(product.getId()));
            if(cartItem.getProdId().equals(product.getId())){
                cartItem.setNum(cartItem.getNum()+num);
                cartItemService.update(stall,cartItem);
                return ResponseUtil.ok();
            }
        }

        cartItemService.addProduct(stall,product1,num);
        return ResponseUtil.ok();

    }


    /**
     * 删除商品
     * @param map
     * @return
     */
    @RequestMapping("/delete")
    public Object delete(@RequestBody Map<String,Object> map){
        String cartItemsJson = new JSONObject().toJSONString(map.get("cartItems"));
        List<JSONObject> objs = JSONObject.parseObject(cartItemsJson, List.class);
        List<ComsCartItem> cartItems = new ArrayList<>();
        for(JSONObject obj : objs){
            ComsCartItem cartItem = new ComsCartItem();
            cartItem.setId(obj.getInteger("id"));
//            cartItem.setChecked(obj.getBoolean("checked"));
//            cartItem.setProdId(obj.getInteger("prodId"));
//            cartItem.setPrice(obj.getBigDecimal("price"));
//            cartItem.setNum(obj.getInteger("num"));
//            cartItem.setName(obj.getString("name"));
//            cartItem.setPicUrl(obj.getString("picUrl"));
            cartItems.add(cartItem);
        }
        String stallJson = new JSONObject().toJSONString(map.get("stall"));
        ComsStall stall = new JSONObject().parseObject(stallJson, ComsStall.class);
        for(ComsCartItem cartItem:cartItems){
            cartItemService.deleteByProdId(stall,cartItem.getId());
        }
        return ResponseUtil.ok("删除成功");
    }

    @RequestMapping("/change")
    public Object change(@RequestBody Map<String,Object> map){
        String stallJson = new JSONObject().toJSONString(map.get("stall"));
        ComsStall stall = JSONObject.parseObject(stallJson,ComsStall.class);
        String cartItemJson = new JSONObject().toJSONString(map.get("cartItem"));
        ComsCartItem cartItem = JSONObject.parseObject(cartItemJson,ComsCartItem.class);
        String flagJson = new JSONObject().toJSONString(map.get("changeFlag"));
        Integer flag = JSONObject.parseObject(flagJson,Integer.class);
        if(flag==1){
            cartItem.setNum(cartItem.getNum()+1);
        }else if(flag == -1){
            cartItem.setNum(cartItem.getNum()-1);
        }else {
            if (cartItem.getNum() != flag) {
                cartItem.setNum(flag);
            }
        }
        cartItemService.update(stall,cartItem);
        return ResponseUtil.ok();
    }




}
