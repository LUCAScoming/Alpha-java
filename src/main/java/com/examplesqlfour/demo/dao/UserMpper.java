package com.examplesqlfour.demo.dao;

import com.examplesqlfour.demo.common.Mymapper;
import com.examplesqlfour.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMpper extends Mymapper<User> {
}
