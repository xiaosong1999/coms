package com.dfbz.web;

import com.dfbz.domain.*;
import com.dfbz.service.ComsCategoryService;
import com.dfbz.service.ComsProductService;
import com.dfbz.service.ComsSupCateService;
import com.dfbz.service.ComsSupplierService;
import com.dfbz.util.ResponseUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

import static com.dfbz.util.File.delFile;
import static com.dfbz.util.File.uploadFile;

@RestController
@RequestMapping("/admin")
public class AdminProductController {

    private static final String FILE_PATH = "static/uploadfiles/";
    private static final String DEFAULT_PAGE_SIZE = "12";

    private static String picName = null;
    @Resource
    private ComsProductService productService;
    @Resource
    private ComsCategoryService categoryService;
    @Resource
    private ComsSupplierService supplierService;
    @Resource
    private ComsSupCateService supCateService;

    @GetMapping("/product/productList")
    public Object getProductList(@RequestParam(value = "pageNum", required = false, defaultValue = "1")Integer pageNum,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE )Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<ComsProduct> products = productService.listAll();
        PageInfo<ComsProduct> pageInfo = new PageInfo<ComsProduct>(products);
        ModelAndView mv = new ModelAndView("product_list");
        ComsAdmin admin = (ComsAdmin) SecurityUtils.getSubject().getPrincipal();
        mv.addObject("admin",admin);
        mv.addObject("pageInfo",pageInfo);
        return mv;
    }

    /**
     * 根据输入内容进行模糊查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/product/queryByName")
    public Object queryByName(@RequestParam(value = "pageNum", required = false, defaultValue = "1")Integer pageNum,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE )Integer pageSize,
                                    @RequestParam(value = "prodName", required = false, defaultValue = "")String prodName){
        PageHelper.startPage(pageNum,pageSize);
        List<ComsProduct> products = null;
        if(prodName.equals("")||prodName.trim().length()==0){
            products = productService.listAll();;
        }else{
            products = productService.queryProductByName(prodName);
        }
        PageInfo<ComsProduct> pageInfo = new PageInfo<ComsProduct>(products);
        ModelAndView mv = new ModelAndView("product_list");
        ComsAdmin admin = (ComsAdmin) SecurityUtils.getSubject().getPrincipal();
        mv.addObject("admin",admin);
        mv.addObject("pageInfo",pageInfo);
        return mv;
    }

    @RequiresRoles("adminL1")
    @RequestMapping("/product/editProduct")
    public Object editProduct(Integer prodId,Integer pageNum){
        ModelAndView mv = new ModelAndView("edit_product");
        ComsProduct product = productService.findById(prodId);
        System.out.println(product);
        ComsAdmin admin = (ComsAdmin) SecurityUtils.getSubject().getPrincipal();
        List<ComsCategory> l2Cates = categoryService.queryL2();
        List<ComsSupplier> sups = supplierService.listAllSupplier();
        List<ComsSupCategory> supCates = supCateService.findAll();
        mv.addObject("admin",admin);
        mv.addObject("l2Cates",l2Cates);
        mv.addObject("sups",sups);
        mv.addObject("pageNum",pageNum);
        mv.addObject("supCates",supCates);
        mv.addObject("product",product);
        return mv;
    }

    @RequiresRoles("adminL1")
    @RequestMapping("/product/edit")
    public Object edit(@RequestBody ComsProduct product){
        String filePath = null;
        if(picName != null){
            product.setPicUrl(picName);
            picName = null;
            ComsProduct targetProduct = productService.findById(product.getId());
            if(targetProduct==null){
                ResponseUtil.busy();
            }
            try {
                filePath = ResourceUtils.getURL("classpath:").getPath().substring(1)+FILE_PATH;
                delFile(filePath,targetProduct.getPicUrl());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        Integer result = productService.update(product);
        if(result==0){
            return ResponseUtil.busy();
        }
        return ResponseUtil.ok();
    }

    @RequiresRoles("adminL1")
    @RequestMapping("/product/remove")
    public Object remove(@RequestParam(value = "prodId") Integer prodId){
        ComsProduct product = productService.findById(prodId);
        if(product==null){
            ResponseUtil.busy();
        }
        int result = productService.removeById(prodId);
        String filePath = null;
        if(result<1){
            ResponseUtil.busy();
        }
        try {
            filePath = ResourceUtils.getURL("classpath:").getPath().substring(1)+FILE_PATH;
            delFile(filePath,product.getPicUrl());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            return ResponseUtil.ok();
        }
    }

    @GetMapping("/product/addProduct")
    public Object addProduct(){
        ModelAndView mv = new ModelAndView("add_product");
        ComsAdmin admin = (ComsAdmin) SecurityUtils.getSubject().getPrincipal();
        List<ComsCategory> l2Cates = categoryService.queryL2();
        List<ComsSupplier> sups = supplierService.listAllSupplier();
        List<ComsSupCategory> supCates = supCateService.findAll();
        mv.addObject("admin",admin);
        mv.addObject("l2Cates",l2Cates);
        mv.addObject("sups",sups);
        mv.addObject("supCates",supCates);
        return mv;
    }

    @RequiresRoles("adminL1")
    @RequestMapping("/product/uploading")
    public  Object uploading(@RequestBody MultipartFile file, HttpServletRequest request){

        String fileName = file.getOriginalFilename();
        picName = (UUID.randomUUID()+fileName.substring(fileName.indexOf('.')));
        String filePath = null;
        try {
            filePath = ResourceUtils.getURL("classpath:").getPath().substring(1)+FILE_PATH;
            uploadFile(file.getBytes(),filePath, picName);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("文件上传失败");
            return ResponseUtil.fail();
        }
        System.out.println("文件上传成功");
        return ResponseUtil.ok();
    }
    @RequiresRoles("adminL1")
    @RequestMapping("/product/add")
    public Object insert(@RequestBody ComsProduct product){
        product.setPicUrl(picName);
        picName = null;
//        System.out.println(product);
        Integer result = productService.add(product);
        if(result<0){
            return ResponseUtil.fail();
        }
        return ResponseUtil.ok();
    }




}
