package com.event.demo.base;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author wu
 * @date 2018/08/15
 * @Description:
 */
public class BaseDateEntity extends BaseUUIDEntity {

    @Override
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreatedDt() {
        return createdDt;
    }

    @Override
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getUpdatedDt() {
        return updatedDt;
    }
}
