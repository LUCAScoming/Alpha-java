package com.event.demo.common.mapper;


import com.event.demo.base.BaseEntity;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.rowbounds.SelectByConditionRowBoundsMapper;

/**
 * 放在这里保证，tk.mapper扫描时不会扫描到这个包
 */
@RegisterMapper
public interface MyMapper<T extends BaseEntity> extends Mapper<T>,ConditionMapper<T>,InsertListMapper<T>,IdsMapper<T>, SelectByConditionRowBoundsMapper<T>,SelectBySqlMapper<T>{
}
