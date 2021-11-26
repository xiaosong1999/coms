package com.dfbz.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class OrderItemView implements Serializable {

    private String prod_name;
    private Integer num;
    private LocalDate pd;
    private Integer fd;
    private String brand;
    private String sup_name;
    private Integer sup_id;
    private LocalDateTime create_time;
}
