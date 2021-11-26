package com.dfbz.service;

import com.dfbz.domain.ComsOrder;
import com.dfbz.domain.ComsProduct;
import com.dfbz.dto.AppointmentDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchService {

    List<ComsProduct> searchProByDate(AppointmentDto appointmentDto);

    List<ComsOrder> searchOrdersByDate(AppointmentDto appointmentDto);


}
