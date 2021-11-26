package com.dfbz.service;

import com.dfbz.domain.ComsOrder;
import com.dfbz.domain.ComsOrderItem;

import java.util.List;
import java.util.Map;

public interface IndexService {

    List<ComsOrderItem> getTodayData();

    List<ComsOrder> getWeekData();

    List<ComsOrder> getYearData();

    long getUserAccount();

    long getProductAccount();

}
