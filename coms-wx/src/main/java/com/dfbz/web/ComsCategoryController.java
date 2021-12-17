package com.dfbz.web;

import com.dfbz.domain.ComsCategory;
import com.dfbz.domain.ComsSupCategory;
import com.dfbz.domain.ComsSupplier;
import com.dfbz.service.ComsCategoryService;
import com.dfbz.service.ComsSupCateService;
import com.dfbz.service.ComsSupplierService;
import com.dfbz.util.HomeCacheManager;
import com.dfbz.util.ResponseUtil;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/wx/category")
public class ComsCategoryController {

    @Resource
    private ComsCategoryService categoryService;


    @Resource
    ComsSupCateService supCateService;
    /**
     * 分类详情
     *
     * @param id 分类类目ID
     *           如果分类类目ID是空，则选择第一个分类类目。
     * @return 分类详情
     */
    @RequestMapping("/index")
    public Object index(@RequestParam(value = "id", required = false) Integer id) {
        //所有一级分类目录
        List<ComsCategory> l1CateList = categoryService.queryL1();
        System.out.println(l1CateList.size());
        //当前类别
        ComsCategory currenCategory = null;
        if (id != null) {
            currenCategory = categoryService.findById(id);
        } else {
            //默认显示一级目录一下的类别
            currenCategory = l1CateList.get(0);
        }

        //当前以及分类目录对应的二级分类目录
        List<ComsCategory> currentSubCategory = null;
        if (null != currenCategory) {
            currentSubCategory = categoryService.queryByParentId(currenCategory.getId());
        }
        Map<String, Object> data = new HashMap<>();
        data.put("categoryList", l1CateList);
        data.put("currenCategory", currenCategory);
        data.put("currentSubCategory", currentSubCategory);
        return ResponseUtil.ok(data);
    }

    /**
     * 所有分类数据
     *
     * @return 所有分类数据
     */
    @GetMapping("/all")
    public Object queryAll() {
        if (HomeCacheManager.hasData(HomeCacheManager.CATALOG)) {
            return ResponseUtil.ok(HomeCacheManager.getCacheData(HomeCacheManager.CATALOG));
        }
        //所有一级分类
        List<ComsCategory> l1CateList = categoryService.queryL1();
        System.out.println("----------------------------------------------------" + l1CateList.isEmpty());
        //所有子分类列表
        Map<Integer, List<ComsCategory>> allList = new HashMap<>();
        List<ComsCategory> sub;
        for (ComsCategory category : l1CateList) {
            sub = categoryService.queryByParentId(category.getId());
            allList.put(category.getId(), sub);
        }

        //当前一级分类目录
        ComsCategory currentCategory = l1CateList.get(0);
//        System.out.println(currentComsCategory);

        //当前一级分类目录对应的二级分类目录
        List<ComsCategory> currentSubCategory = null;
        if (null != currentCategory) {
            currentSubCategory = categoryService.queryByParentId(currentCategory.getId());
        }

        Map<String, Object> data = new HashMap<>();
        data.put("categoryList", l1CateList);
        data.put("allList", allList);
        data.put("currentCategory", currentCategory);
        data.put("currentSubCategory", currentSubCategory);

        //缓存数据
        HomeCacheManager.loadData(HomeCacheManager.CATALOG, data);
        return ResponseUtil.ok(data);
    }

    /**
     * 当前分类栏目
     *
     * @param id 分类类目ID
     * @return 根据当前分类栏目
     */
    @GetMapping("/current")
    public Object current(@NotNull Integer id) {
        // 当前分类
        ComsCategory currentCategory = categoryService.findById(id);
        List<ComsCategory> currentSubCategory = categoryService.queryByParentId(currentCategory.getId());
        Map<String, Object> data = new HashMap<>();
        data.put("currentCategory", currentCategory);
        data.put("currentSubCategory", currentSubCategory);
        return ResponseUtil.ok(data);
    }



    @PostMapping("/findL2BySupId")
    public Object findL2BySupId(@RequestParam("supId") Integer supId){
        List<ComsSupCategory> supCates = (List<ComsSupCategory>) supCateService.findCateBySup(supId);
        List<ComsCategory> l2Cates = new ArrayList<>();
        for(ComsSupCategory supCate:supCates){
            List<ComsCategory> categories = categoryService.queryByParentId(supCate.getCateId());
            for(ComsCategory category:categories){
                l2Cates.add(category);
            }
        }
        return ResponseUtil.ok(l2Cates);
    }

    /**
     * 数据库设计错误 导致只能使用这个方法 上面的方法是正常 将来会用上面的
     * @param supId
     * @return
     */
    @GetMapping("/findL2BySupId2")
    public Object findL2BySupId2(@RequestParam("supId")Integer supId){
        List<ComsSupCategory> supCates = (List<ComsSupCategory>) supCateService.findCateBySup(supId);
        List<ComsCategory> cates = new ArrayList<>();
        for(ComsSupCategory supCate:supCates){
            cates.add(categoryService.findById(supCate.getCateId()));
        }
        Map<String,Object> data = new LinkedHashMap<>();
        data.put("supCates",supCates);
        data.put("cates",cates);
        return ResponseUtil.ok(data);
    }
}
