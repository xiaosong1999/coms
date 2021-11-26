package com.dfbz.web;

import com.dfbz.domain.ComsAdmin;
import com.dfbz.domain.ComsCategory;
import com.dfbz.service.ComsCategoryService;
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
@RequestMapping("/admin/category")
public class AdminCategoryController {
    private static final String FILE_PATH = "static/uploadfiles/";
    private static final String DEFAULT_PAGE_SIZE = "12";

    private static String picName = null;
    @Resource
    private ComsCategoryService categoryService;

    @GetMapping("/categoryList")
    public Object getProductList(@RequestParam(value = "pageNum", required = false, defaultValue = "1")Integer pageNum,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE )Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<ComsCategory> cates = categoryService.queryAll();
        List<ComsCategory> l1Cates = categoryService.queryL1();
        PageInfo<ComsCategory> pageInfo = new PageInfo<ComsCategory>(cates);
        ModelAndView mv = new ModelAndView("category_list");
        ComsAdmin admin = (ComsAdmin) SecurityUtils.getSubject().getPrincipal();
        mv.addObject("admin",admin);
        mv.addObject("l1Cates",l1Cates);
        mv.addObject("pageInfo",pageInfo);
        return mv;
    }

    /**
     * 根据输入内容进行模糊查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/queryByName")
    public Object queryByName(@RequestParam(value = "pageNum", required = false, defaultValue = "1")Integer pageNum,
                              @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE )Integer pageSize,
                              @RequestParam(value = "cateName", required = false, defaultValue = "")String cateName){
        PageHelper.startPage(pageNum,pageSize);
        List<ComsCategory> cates = null;
        List<ComsCategory> l1Cates = categoryService.queryL1();
        if(cateName.equals("")||cateName.trim().length()==0){
            cates = categoryService.queryAll();
        }else{
            cates = categoryService.findByName(cateName);
        }
        PageInfo<ComsCategory> pageInfo = new PageInfo<ComsCategory>(cates);
        ModelAndView mv = new ModelAndView("category_list");
        ComsAdmin admin = (ComsAdmin) SecurityUtils.getSubject().getPrincipal();
        mv.addObject("admin",admin);
        mv.addObject("l1Cates",l1Cates);
        mv.addObject("pageInfo",pageInfo);
        return mv;
    }

    @RequiresRoles("adminL1")
    @RequestMapping("/edit")
    public Object edit(@RequestBody ComsCategory category){
        String filePath = null;
        if(picName != null){
            category.setIcon(picName);
            picName = null;
            ComsCategory targetCategory = categoryService.findById(category.getId());
            if(targetCategory==null){
                return ResponseUtil.busy();
            }
            try {
                filePath = ResourceUtils.getURL("classpath:").getPath().substring(1)+FILE_PATH;
                delFile(filePath,targetCategory.getIcon());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println(category);
        Integer result = categoryService.update(category);
        if(result==0){
            return ResponseUtil.busy();
        }
        return ResponseUtil.ok();
    }

    @RequiresRoles("adminL1")
    @RequestMapping("/remove")
    public Object remove(@RequestParam(value = "cateId") Integer cateId){
        ComsCategory category = categoryService.findById(cateId);
        if(category==null){
            return ResponseUtil.busy();
        }
        int result = categoryService.removeById(cateId);
        String filePath = null;
        if(result<1){
            return ResponseUtil.busy();
        }
        try {
            filePath = ResourceUtils.getURL("classpath:").getPath().substring(1)+FILE_PATH;
            delFile(filePath,category.getIcon());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            return ResponseUtil.ok();
        }
    }
    @RequiresRoles("adminL1")
    @RequestMapping("/uploading")
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
    @RequestMapping("/add")
    public Object insert(@RequestBody ComsCategory category){
        category.setIcon(picName);
        if(category.getParentId()==null){
            category.setParentId(0);
        }
        System.out.println(category);
        picName = null;
        int result = categoryService.add(category);
        if(result==0){
            return ResponseUtil.busy();
        }
        return ResponseUtil.ok();
    }
}
