package com.event.demo.common.mapper;

import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

@RegisterMapper
public interface SelectBySqlMapper<T> {

    @SelectProvider(
            type = SelectBySqlProvider.class,
            method = "dynamicSQL"
    )
    List<T> selectBySqlAndCondition(T record);

}
