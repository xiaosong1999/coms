package com.dfbz.util;

import com.dfbz.domain.ComsStall;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CodeUtil {
    //生成购物车key
    public static String fixToCartCode(ComsStall stall){
       return stall.getName()+"-"+stall.getId();
    }

    //随机生成购物车商品项的id
    public static String fixToCartItemCode(Date date) {
        //生成格式化日期为"yymmdd"
        DateFormat format = new SimpleDateFormat("yyMMdd");
        if(null == date){
            date = new Date();
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(format.format(date));
        String code = buffer.toString();

        //随机生成四位数
        String timeMillis = System.currentTimeMillis() + "";
        String randomNum = timeMillis.substring(timeMillis.length() - 4);

        //生成订单编号
        String orderCode = code + randomNum;
//        System.out.println("1"+orderCode);
        return orderCode;
    }
}
