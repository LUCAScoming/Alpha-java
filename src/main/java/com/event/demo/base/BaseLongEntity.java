package com.event.demo.base;


import lombok.Data;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author wu
 * @date 2018/08/30
 * @Description:
 */
@NameStyle(Style.camelhumpAndLowercase)
@Data
public class BaseLongEntity extends BaseEntity<Long> {
    @Id
    @GeneratedValue(generator = "JDBC")
    protected Long id;

    protected String createdBy;

    protected Date createdDt;

    protected String updatedBy;

    protected Date updatedDt;

    protected Integer dr;

    @Override
    public Long getPK() {
        return this.id;
    }
}
