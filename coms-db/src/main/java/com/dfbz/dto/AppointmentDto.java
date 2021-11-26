package com.dfbz.dto;


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
@Accessors(chain = true)
public class AppointmentDto implements Serializable {

    /**
     * 开始时间
     */

    private LocalDateTime startDate;

    /**
     * 结束时间
     */

    private LocalDateTime endDate;





    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        String[] split = startDate.split("-");
        split[2] = split[2].length() == 1 ? "0" + split[2] : split[2];
        split[1] = split[1].length() == 1 ? "0" + split[1] : split[1];
        String startDateStr = split[0] + "-" + split[1] + "-" + split[2] + " 00:00:00";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDateTime = LocalDateTime.parse(startDateStr, dateTimeFormatter);

        this.startDate = startDateTime;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        String[] split = endDate.split("-");
        split[2] = split[2].length() == 1 ? "0" + split[2] : split[2];
        split[1] = split[1].length() == 1 ? "0" + split[1] : split[1];
        String endDateStr = split[0] + "-" + split[1] + "-" + split[2] + " 00:00:00";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime endDateTime = LocalDateTime.parse(endDateStr, dateTimeFormatter);
        this.endDate = endDateTime;
    }
}
