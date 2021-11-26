package com.dfbz.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 响应操作结果
 * <p>
 * {
 * errno： 错误码，
 * errmsg：错误消息，
 * data：  响应数据
 * }
 * <p>
 * <p>
 * <p>
 * 错误码：
 * <p>
 * 0，成功；
 * 4xx，前端错误，说明前端开发者需要重新了解后端接口使用规范：
 * <p>
 * 401，参数错误，即前端没有传递后端需要的参数；
 * 402，参数值错误，即前端传递的参数值不符合后端接收范围。
 * <p>
 * 5xx，后端错误，除501外，说明后端开发者应该继续优化代码，尽量避免返回后端错误码：
 * <p>
 * 501，验证失败，即后端要求用户登录；
 * 502，系统内部错误，即没有合适命名的后端内部错误；
 * 503，业务不支持，即后端虽然定义了接口，但是还没有实现功能；
 * 504，更新数据失效，即后端采用了乐观锁更新，而并发更新时存在数据更新失效；
 * 505，更新数据失败，即后端数据库更新失败（正常情况应该更新成功）。
 * <p>
 * 6xx，小商城后端业务错误码，
 * 具体见mymall-admin-api模块的AdminResponseCode。
 * 7xx，管理后台后端业务错误码，
 * 具体见mymall-wx-api模块的WxResponseCode。
 */

public class ResponseUtil {
    public static Object ok() {
        Map<String, Object> obj = new HashMap<>();
        obj.put("errno", 0);
        obj.put("errmsg", "成功");
        return obj;
    }

    public static Object ok(String msg, int count) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("success", "成功");
        obj.put("msg", msg);
        obj.put("count", count);
        return obj;
    }

    public static Object ok(Object data) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", 0);
        obj.put("errmsg", "成功");
        obj.put("data", data);
        return obj;
    }

    public static Object ok(String errmsg, Object data) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", 0);
        obj.put("errmsg", errmsg);
        obj.put("data", data);
        return obj;
    }

    public static Object fail() {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", -1);
        obj.put("errmsg", "错误");
        return obj;
    }

    public static Object fail(int errno, String errmsg) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", errno);
        obj.put("errmsg", errmsg);
        return obj;
    }

    public static Object fail(Integer code, String message, String obj) {
        Map<String, Object> obj1 = new HashMap<String, Object>();
        obj1.put("errno", code);
        obj1.put("errmsg", message);
        obj1.put("prodName", obj);
//        obj1.put("prodId",((Map)obj).get("prodId"));
        return obj1;
    }

    public static Object badArgument() {
        return fail(401, "参数不对");
    }

    public static Object badArgumentValue() {
        return fail(402, "参数值不对");
    }

    public static Object unlogin() {
        return fail(501, "请登录");
    }

    public static Object serious() {
        return fail(502, "系统内部错误");
    }

    public static Object unsupport() {
        return fail(503, "业务不支持");
    }

    public static Object updatedDateExpired() {
        return fail(504, "更新数据已经失效");
    }

    public static Object updatedDataFailed() {
        return fail(505, "更新数据失败");
    }

    public static Object unauthz() {
        return fail(506, "无操作权限");
    }

    public static Object fail(String message) {
        return fail(506, message);
    }

    public static Object UNPROCESABLE_ENTITY() {
        return fail(507, "密码是6到20位字母数字_或手机号输入错误");
    }

    public static Object errImgType() {
        return fail(507, "图片信息错误");
    }

    public static Object outOfStock(String map) {
        return fail(508, "库存不足", map);
    }

    public static Object outOfStock1(String map) {
        return fail(508, "库存不足", map);
    }

    public static Object soldOut(String map) {
        return fail(509, "已下架", map);
    }
    public static Object soldOut1(String map) {
        return fail(509, "已下架", map);
    }

    public static Object errorPassword() {
        return fail(510, "密码错误");
    }

    public static Object freezeAccount() {
        return fail(512, "账户被冻结,请及时联系管理员。");
    }

    public static Object noAccount() {
        return fail(513, "没有该用户");
    }
}
