package com.dfbz.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dfbz.dao.ComsAdminMapper;
import com.dfbz.dao.ComsStallMapper;
import com.dfbz.dao.ComsSupplierMapper;
import com.dfbz.domain.*;
import com.dfbz.service.ComsAdminService;
import com.dfbz.service.ComsCategoryService;
import com.dfbz.service.ComsSupCateService;
import com.dfbz.service.MemberService;
import com.dfbz.util.ResponseUtil;
import com.dfbz.util.bcrypt.BCryptPasswordEncoder;

import com.dfbz.vo.RegisterAdminVo;
import com.dfbz.vo.RegisterStallVo;
import com.dfbz.vo.RegisterSupVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.xml.ws.Response;
import java.time.LocalDateTime;
import java.util.*;

import static com.dfbz.util.File.delFile;

@RestController
@RequestMapping("/admin/member")
public class AdminMemberController {

    private static final String FILE_PATH = "static/uploadfiles/";
    private static final String DEFAULT_PAGE_SIZE = "12";
    @Resource
    private MemberService memberService;
    @Resource
    private ComsCategoryService categoryService;
    @Resource
    private ComsSupCateService supCateService;
    @Resource
    private ComsAdminService adminService;
    @Resource
    private ComsSupplierMapper supplierMapper;
    @Resource
    private ComsStallMapper stallMapper;
    @Resource
    private ComsAdminMapper adminMapper;


    @GetMapping("/stallList")
    public Object getStallList(@RequestParam(value = "pageNum", required = false, defaultValue = "1")Integer pageNum,
                               @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE )Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<ComsStall> stalls = memberService.getAllStall();
        PageInfo<ComsStall> pageInfo = new PageInfo<ComsStall>(stalls);
        ComsAdmin admin = (ComsAdmin) SecurityUtils.getSubject().getPrincipal();
        ModelAndView mv = new ModelAndView("stall_list");
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
    @GetMapping("/queryStallByName")
    public Object queryStallByName(@RequestParam(value = "pageNum", required = false, defaultValue = "1")Integer pageNum,
                              @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE )Integer pageSize,
                              @RequestParam(value = "stallName", required = false, defaultValue = "")String stallName){
        PageHelper.startPage(pageNum,pageSize);
        List<ComsStall> stalls = null;

        if(stallName.equals("")||stallName.trim().length()==0){
            stalls = memberService.getAllStall();
        }else{
            stalls = memberService.queryStallByName(stallName);
        }
        PageInfo<ComsStall> pageInfo = new PageInfo<ComsStall>(stalls);
        ModelAndView mv = new ModelAndView("stall_list");
        ComsAdmin admin = (ComsAdmin) SecurityUtils.getSubject().getPrincipal();
        mv.addObject("admin",admin);
        mv.addObject("pageInfo",pageInfo);
        return mv;
    }

    @RequiresRoles("adminL1")
    @RequestMapping("/addStall")
    public Object addStall(@RequestBody @Validated RegisterStallVo stallVo,
                           BindingResult result){
        if(result.hasErrors()){
            String message = result.getFieldError().getDefaultMessage();
            return ResponseUtil.UNPROCESABLE_ENTITY();
        }
        ComsStallExample example = new ComsStallExample();
        example.or().andPhoneEqualTo(stallVo.getPhone());
        List<ComsStall> stalls = stallMapper.selectByExample(example);
        for(ComsStall stall1 : stalls){
            if(stall1.getPhone().equals(stallVo.getPhone())){
                return ResponseUtil.fail(9,"该手机号已被注册");
            }
        }
        //去除添加档口的加密
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String password = encoder.encode(stallVo.getPassword());
//        System.out.println("++++++++++++++++++++"+password);
        ComsStall stall = new ComsStall()
                .setName(stallVo.getName())
                .setPassword(stallVo.getPassword())
                .setPhone(stallVo.getPhone())
                .setStatus(stallVo.getStatus())
                .setCreateTime(LocalDateTime.now());
        int count = memberService.addStall(stall);
        if(count==0){
            return ResponseUtil.busy();
        }
        return ResponseUtil.ok();
    }

    @RequiresRoles("adminL1")
    @RequestMapping("/editStall")
    public Object editStall(@RequestBody ComsStall stall){
        System.out.println(stall);
        List<ComsStall> stalls = memberService.getAllStall();
        Iterator<ComsStall> list = stalls.listIterator();
        int result = 0;
        boolean flag = false;
        while (list.hasNext()){
            ComsStall tamp = list.next();
            if(tamp.getId()!=stall.getId()){
                if(tamp.getPhone().equals(stall.getPhone())){
                    return ResponseUtil.fail(9,"该手机号已被注册");
                }
            }else{
                if(tamp.getPhone().equals(stall.getPhone())){
                    result = memberService.editStall(stall);
                    flag = true;
                    break;
                }
            }
        }
        if(result == 0 && flag==true){
            return ResponseUtil.busy();
        }
        result = memberService.editStall(stall);
        if(result == 0){
            return ResponseUtil.busy();
        }
        return ResponseUtil.ok();
    }

    @RequiresRoles("adminL1")
    @GetMapping("/removeStall")
    public Object removeStallById(@RequestParam(value = "stallId") Integer stallId){
        ComsStall stall = memberService.findStallById(stallId);
        if(stall==null){
            return ResponseUtil.busy();
        }
        int result = memberService.removeStallById(stallId);
        if(result<1){
            return ResponseUtil.busy();
        }
        return ResponseUtil.ok();
    }

    @GetMapping("/supList")
    public Object getSupList(@RequestParam(value = "pageNum", required = false, defaultValue = "1")Integer pageNum,
                             @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE )Integer pageSize){
        ModelAndView mv = new ModelAndView("supplier_list");
        PageHelper.startPage(pageNum,pageSize);
        List<ComsSupplier> sups = memberService.getAllSup();
        List<ComsCategory> cates = categoryService.queryL1();
        List<ComsSupCategory> supCates1 = supCateService.findAll();
        ComsAdmin admin = (ComsAdmin) SecurityUtils.getSubject().getPrincipal();
        List<ComsSupCategory> supCates = new ArrayList<>();
        supCates.add(supCates1.get(0));
        for(ComsSupCategory supCate1:supCates1){
            for(int i=0;i<supCates.size();i++){
                if(supCates.get(i).getSupId()==supCate1.getSupId()&&supCates.get(i).getCateL1Id()==supCate1.getCateL1Id()){
                    i = supCates.size();
                }else{
                    if(i==supCates.size()-1){
                        supCates.add(supCate1);
                    }
                }
            }
        }
        PageInfo<ComsSupplier> pageInfo = new PageInfo<>(sups);
//        System.out.println(JSON.toJSONString(supCates));
        mv.addObject("admin",admin);
        mv.addObject("supCates", supCates);
        mv.addObject("cates",cates);
        mv.addObject("pageInfo",pageInfo);
        return mv;
    }

    /**
     * 根据输入内容进行模糊查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/querySupByName")
    public Object querySupByName(@RequestParam(value = "pageNum", required = false, defaultValue = "1")Integer pageNum,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE )Integer pageSize,
                                   @RequestParam(value = "supName", required = false, defaultValue = "")String supName){
        PageHelper.startPage(pageNum,pageSize);
        List<ComsSupplier> sups = null;

        if(supName.equals("")||supName.trim().length()==0){
            sups = memberService.getAllSup();
        }else{
            sups = memberService.querySupByName(supName);
        }
        PageInfo<ComsSupplier> pageInfo = new PageInfo<ComsSupplier>(sups);
        ModelAndView mv = new ModelAndView("supplier_list");
        ComsAdmin admin = (ComsAdmin) SecurityUtils.getSubject().getPrincipal();
        List<ComsCategory> cates = categoryService.queryL1();
        List<ComsSupCategory> supCates1 = supCateService.findAll();
        List<ComsSupCategory> supCates = new ArrayList<>();
        supCates.add(supCates1.get(0));
        for(ComsSupCategory supCate1:supCates1){
            for(int i=0;i<supCates.size();i++){
                if(supCates.get(i).getSupId()==supCate1.getSupId()&&supCates.get(i).getCateL1Id()==supCate1.getCateL1Id()){
                    i = supCates.size();
                }else{
                    if(i==supCates.size()-1){
                        supCates.add(supCate1);
                    }
                }
            }
        }
        mv.addObject("admin",admin);
        mv.addObject("supCates", supCates);
        mv.addObject("cates",cates);
        mv.addObject("pageInfo",pageInfo);
        return mv;
    }

    @RequiresRoles("adminL1")
    @RequestMapping("/addSup")
    public Object addSup(@RequestBody @Validated RegisterSupVo supVo,
                         BindingResult result){
        System.out.println(supVo);
        if(result.hasErrors()){
            return ResponseUtil.UNPROCESABLE_ENTITY();
        }

        ComsSupplierExample example = new ComsSupplierExample();
        example.or().andPhoneEqualTo(supVo.getPhone());
        List<ComsSupplier> comsSuppliers = supplierMapper.selectByExample(example);
        if(comsSuppliers.size() > 0){
            return ResponseUtil.fail(507,"该手机号已被注册！");
        }
       //去除添加供应商密码的加密
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //String password = encoder.encode(supVo.getPassword());
        ComsSupplier supplier = new ComsSupplier()
                .setName(supVo.getName())
                .setPassword(supVo.getPassword())
                .setPhone(supVo.getPhone())
                .setAddress(supVo.getAddress())
                .setStatus(supVo.getStatus());
        try{
            int count = memberService.addSupplier(supplier);
        }catch (Exception e){
            e.printStackTrace();
        }
        List<String> cates = supVo.getCategory();
        for(String cate:cates){
            List<ComsCategory> categories = categoryService.queryByParentId(Integer.parseInt(cate));
            for(ComsCategory category:categories){
                ComsSupCategory supCategory = new ComsSupCategory();
                supCategory.setCateId(category.getId());
                supCategory.setSupId(supplier.getId());
                supCategory.setCateL1Id(category.getParentId());
                supCateService.add(supCategory);
            }
        }

        return ResponseUtil.ok();
    }

    @RequiresRoles("adminL1")
    @RequestMapping("/editSup")
    public Object editSup(@RequestBody Map objJson){
        ComsSupplier supplier = new ComsSupplier();
        supplier.setId(Integer.parseInt(objJson.get("id").toString()));
        supplier.setName((String) objJson.get("name"));
        supplier.setPassword((String) objJson.get("password"));
        supplier.setPhone((String) objJson.get("phone"));
        supplier.setAddress((String) objJson.get("address"));
        supplier.setStatus(Integer.parseInt(objJson.get("status").toString()));
        memberService.editSupplier(supplier);
        supCateService.removeBySupId(supplier.getId());
        System.out.println("==========================="+objJson.get("category"));
        List<String> cates = (List<String>) objJson.get("category");

//        for(String cate:cates){
//            System.out.println(cate);
//            System.out.println(Integer.parseInt(cate));
//        }
        for(String cate:cates){
            List<ComsCategory> categories = categoryService.queryByParentId(Integer.parseInt(cate));
            for(ComsCategory category:categories){
                ComsSupCategory supCategory = new ComsSupCategory();
                supCategory.setCateId(category.getId());
                supCategory.setSupId(supplier.getId());
                supCategory.setCateL1Id(category.getParentId());
                supCateService.add(supCategory);
            }
        }

        return ResponseUtil.ok();
    }

    @RequiresRoles("adminL1")
    @GetMapping("/removeSup")
    public Object removeSupById(@RequestParam(value = "supId") Integer supId){
        ComsSupplier supplier = memberService.findSupplierById(supId);
        if(supplier==null){
            return ResponseUtil.busy();
        }
        int result = memberService.removeSupplier(supplier);
        supCateService.removeBySupId(supId);
        if(result<1){
            return ResponseUtil.busy();
        }
        return ResponseUtil.ok();
    }

    @RequiresRoles("adminL1")
    @GetMapping("/adminList")
    public Object getAdminList(@RequestParam(value = "pageNum", required = false, defaultValue = "1")Integer pageNum,
                               @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE )Integer pageSize){
        ModelAndView mv = new ModelAndView("admin_list");
        PageHelper.startPage(pageNum,pageSize);
        List<ComsAdmin> admins = memberService.getAllAdmin();
        ComsAdmin admin = (ComsAdmin) SecurityUtils.getSubject().getPrincipal();
        PageInfo<ComsAdmin> pageInfo = new PageInfo<>(admins);
        mv.addObject("admin",admin);
        mv.addObject("pageInfo",pageInfo);
        return mv;
    }

    @RequiresRoles("adminL1")
    @RequestMapping("/addAdmin")
    public Object addAdmin(@RequestBody @Validated RegisterAdminVo adminVo,
                           BindingResult result){
        if(result.hasErrors()){
            return ResponseUtil.UNPROCESABLE_ENTITY();
        }
        ComsAdminExample example = new ComsAdminExample();
        example.or().andUsernameEqualTo(adminVo.getUsername());
        List<ComsAdmin> comsAdmins = adminMapper.selectByExample(example);
        if(comsAdmins.size() > 0){
            return ResponseUtil.fail(403,"该用户名已存在");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode(adminVo.getPassword());
        ComsAdmin admin = new ComsAdmin()
                .setUsername(adminVo.getUsername())
                .setPassword(password)
                .setName(adminVo.getName())
                .setType(adminVo.getType());
        //对数据库的添加操作
        int count = adminService.addAdmin(admin);
        if(count != 1){
            return ResponseUtil.busy();
        }
        return ResponseUtil.ok("添加成功",admin);
    }
    /**
     * 查询admin
     */

    @RequiresRoles("adminL1")
    @GetMapping("/queryAdminByName")
    public Object queryAdminByName(@RequestParam(value = "pageNum", required = false, defaultValue = "1")Integer pageNum,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE )Integer pageSize,
                                   @RequestParam(value = "name", required = false, defaultValue = "")String name) {
//        System.out.println("查询操作");
        PageHelper.startPage(pageNum,pageSize);
        List<ComsAdmin> admins = null;

        if(name == null || name.equals("") || name.length()==0){
            admins = adminService.listAllAdmin();
        }else{
            admins = adminService.findAdminByName(name);
        }

        PageInfo<ComsAdmin> pageInfo = new PageInfo<ComsAdmin>(admins);
        ModelAndView mv = new ModelAndView("admin_list");
        ComsAdmin admin = (ComsAdmin) SecurityUtils.getSubject().getPrincipal();
        mv.addObject("admin",admin);
        mv.addObject("pageInfo",pageInfo);
        return mv;
    }

    @RequiresRoles("adminL1")
    @GetMapping("/removeAdmin")
    public Object removeAdmin(Integer adminId){
        ComsAdmin admin = adminService.findAdmin(adminId);
        if(admin == null){
            return ResponseUtil.busy();
        }
        int count = adminService.removeAdminById(adminId);
        if(count != 1){
            return ResponseUtil.busy();
        }
        return ResponseUtil.ok();
    }

    /**
     * 编辑admin
     */

    @RequiresRoles("adminL1")
    @RequestMapping("/editAdmin")
    public Object editAdmin(@RequestBody ComsAdmin admin) {
        System.out.println(admin);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode(admin.getPassword());
        admin.setPassword(password);
        int count = adminService.updateById(admin);
        if(count != 1){
            return ResponseUtil.busy();
        }
        return ResponseUtil.ok();
    }

}
