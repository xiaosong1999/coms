package com.dfbz.service.impl;

import com.dfbz.dao.ComsPriceListMapper;
import com.dfbz.domain.ComsPriceList;
import com.dfbz.domain.ComsPriceListExample;
import com.dfbz.service.ComsPriceListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ComsPriceListServiceImpl implements ComsPriceListService {

    @Resource
    private ComsPriceListMapper priceListMapper;

    /**
     * 添加报价
     *
     * @param priceList
     * @return
     */
    @Override
    public int addPrice(ComsPriceList priceList) {
        priceList.setCreateTime(LocalDateTime.now());
        return priceListMapper.insert(priceList);
    }

    /**
     * 查询商品的最新价格
     *
     * @param prodId
     * @param
     * @return
     */
    @Override
    public ComsPriceList queryLatestPrice(Integer prodId) {
        ComsPriceListExample example = new ComsPriceListExample();
        example.or().andProdIdEqualTo(prodId);
        List<ComsPriceList> priceLists = priceListMapper.selectByExample(example);
        List<Integer> ids = new ArrayList<>();
        for (ComsPriceList priceList : priceLists) {
            ids.add(priceList.getId());
        }
        System.out.println(Collections.max(ids));
        return priceListMapper.selectByPrimaryKey(Collections.max(ids));
    }

    /**
     * 查询所有价格清单
     *
     * @return
     */
    @Override
    public List<ComsPriceList> queryAllPriceList() {
        return priceListMapper.selectByExample(null);
    }

    /**
     * 查询特定商品的价格表
     */
    @Override
    public List<ComsPriceList> queryPriceListByProId(Integer prodId) {
        ComsPriceListExample example = new ComsPriceListExample();
        example.or().andProdIdEqualTo(prodId);
        return priceListMapper.selectByExample(example);
    }

    /**
     * 根据供应商id和产品名模糊查询
     * @param prodId
     * @param
     * @return
     */
    @Override
    public List<ComsPriceList> queryPriceListByProIdAndSupId(Integer prodId, Integer supId) {
        ComsPriceListExample example = new ComsPriceListExample();
        example.or().andProdIdEqualTo(prodId).andSupIdEqualTo(supId);
        return priceListMapper.selectByExample(example);
    }

    /**
     * 根据供应商id查询
     * @param supId
     * @return
     */
    @Override
    public List<ComsPriceList> queryPriceListBySupId(Integer supId) {
        ComsPriceListExample example = new ComsPriceListExample();
        example.or().andSupIdEqualTo(supId);
        return priceListMapper.selectByExample(example);
    }
}
