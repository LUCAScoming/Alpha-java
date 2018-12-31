package com.examplesqlfour.demo.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;


public interface Mymapper <T> extends Mapper<T>, MySqlMapper<T> {

        }
