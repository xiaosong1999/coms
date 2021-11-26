package com.dfbz.web;

import com.dfbz.dao.ComsSupCategoryMapper;
import com.dfbz.domain.ComsCategory;
import com.dfbz.domain.ComsSupCategoryExample;
import com.dfbz.service.ComsCategoryService;
import com.dfbz.service.ComsSupCateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/wx/supcate")
public class ComsSupCateController {

    @Resource
    ComsSupCateService supCateService;

    @Resource
    ComsCategoryService categoryService;

    @Resource
    ComsSupCategoryMapper supCategoryMapper;


    /***
     * 产品id查分类和供应商
     * @param id
     * @return
     */
    @RequestMapping("findByProId")
    public Object findByProId(Integer id){
        return supCateService.findSupByProId(id);
    }

    @RequestMapping("findCateBySup")
    public Object findCateBySup(@RequestParam(value = "supId") Integer supId){
        return supCateService.findCateBySup(supId);
    }

    @RequestMapping(value = "/findSupCateBySupIdAndCateId")
    public Object findSupCateBySupIdAndCateId(@RequestParam(value = "supId") Integer supId, @RequestParam(value = "cateId") Integer cateId){
        ComsSupCategoryExample example = new ComsSupCategoryExample();
        example.or().andCateIdEqualTo(cateId).andSupIdEqualTo(supId);
        return supCategoryMapper.selectOneByExample(example);
    }

    /**
     * 第二版
     */
    @RequestMapping("/findOneByL1Cate")
    public ComsCategory findOneByL1Cate(@RequestParam("l1CateId") int l1CateId){
        return categoryService.queryByParentId(l1CateId).get(0);
    }

}
