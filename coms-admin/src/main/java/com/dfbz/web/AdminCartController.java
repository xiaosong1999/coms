package com.dfbz.web;

import com.dfbz.domain.ComsAdmin;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/admin")
public class AdminCartController {
    @GetMapping("/cart/userCart")
    public Object getUserCart(){
        ModelAndView mv = new ModelAndView("user_cart");
        ComsAdmin admin = (ComsAdmin) SecurityUtils.getSubject().getPrincipal();
        mv.addObject("admin",admin);
        return mv;
    }
    @GetMapping("/cart/orderCart")
    public Object getOrderCart(){
        ModelAndView mv = new ModelAndView("order_cart");
        ComsAdmin admin = (ComsAdmin) SecurityUtils.getSubject().getPrincipal();
        mv.addObject("admin",admin);
        return mv;
    }
    @GetMapping("/cart/productCart")
    public Object getProductCart(){
        ModelAndView mv = new ModelAndView("product_cart");
        ComsAdmin admin = (ComsAdmin) SecurityUtils.getSubject().getPrincipal();
        mv.addObject("admin",admin);
        return mv;
    }
}
