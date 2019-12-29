package io.inouty.jswdb.dtos;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Request {

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date endDate;

    public Request() {
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
