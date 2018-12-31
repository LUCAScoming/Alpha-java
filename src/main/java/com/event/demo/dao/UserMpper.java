package com.event.demo.dao;

import com.event.demo.model.User;
import com.event.demo.common.Mymapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMpper extends Mymapper<User> {
}
