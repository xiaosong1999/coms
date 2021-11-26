package com.dfbz.web;

import com.alibaba.fastjson.JSONObject;
import com.dfbz.domain.ComsProduct;
import com.dfbz.domain.ComsSupplier;
import com.dfbz.service.ComsSupplierService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/supplier")
public class ComsSupplierController {

    @Resource
    ComsSupplierService supplierService;

    /***
     * 供应商登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("login")
    public Object login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        ComsSupplier supplier = supplierService.login(username, password);
        Map<String, Object> data = new HashMap<>();
        if (supplier != null) {
            data.put("msg", "1");
            data.put("data", supplier);
        } else {
            data.put("msg", "0");
        }

        return data;
    }

    /***
     * 供应商修改状态
     * @param id
     * @param statusId
     * @return
     */
    @RequestMapping("editStatus")
    public Object login(@RequestParam(value = "id") Integer id, @RequestParam(value = "status") Integer statusId) {
        Integer supplier = supplierService.editStatus(id, statusId);
        Map<String, Object> data = new HashMap<>();
        if (supplier == 1) {
            data.put("msg", "修改成功");
        } else {
            data.put("msg", "修改失败");
        }
        return data;
    }

    /***
     * 显示所有供应商
     * @return
     */
    @RequestMapping("listAll")
    @ResponseBody
    public Object listAllSupplier() {
        return supplierService.listAllSupplier();
    }

    /***
     * 按id查供应商
     * @param id
     * @return
     */
    @RequestMapping("findById")
    public ComsSupplier findById(Integer id) {
        return supplierService.findById(id);
    }

    /***
     * 添加供应商
     * @param map
     * @return
     */
    @RequestMapping("add")
    public Integer add(@RequestBody Map map) {
        String supplierJson = new JSONObject().toJSONString(map.get("supplier"));
        ComsSupplier supplier = new JSONObject().parseObject(supplierJson, ComsSupplier.class);
        LocalDateTime createTime = LocalDateTime.now();
        supplier.setCreateTime(createTime);
        supplier.setUpdateTime(createTime);
        return supplierService.add(supplier);
    }

    /***
     * 修改供应商
     * @param map
     * @return
     */
    @RequestMapping("update")
    public Integer update(@RequestBody Map map) {

        String supplierJson = new JSONObject().toJSONString(map.get("supplier"));
        ComsSupplier supplier = new JSONObject().parseObject(supplierJson, ComsSupplier.class);
        supplierService.findById(supplier.getId());
        LocalDateTime createTime = LocalDateTime.now();
        supplier.setUpdateTime(createTime);
        supplier.setCreateTime(supplierService.findById(supplier.getId()).getCreateTime());
        return supplierService.update(supplier);
    }
}
