package com.examplesqlfour.demo.service;

import com.examplesqlfour.demo.common.Mymapper;
import com.examplesqlfour.demo.dao.UserDao;
import com.examplesqlfour.demo.dao.UserMpper;
import com.examplesqlfour.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserMpper userMpper;

    public List<User>query (Integer sno){
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("Sno",sno);
        return  userMpper.selectByExample(example);
    }

    public boolean addService(Integer Sno,String Cname,String Sname,Integer Score){
        boolean is_save=false;
        List<User> checkList = userDao.getUserList(Sno);
        if (checkList.isEmpty()){
            userDao.insert(Sno,Cname,Sname,Score);
            is_save=true;
        }
        return is_save;
    }

    public boolean updateservice(int Sno,String Cname,String Sname,Integer Score,Integer Sno_pre){
        List<User>  checkPre = userDao.getUserList(Sno_pre);
        if(!checkPre.isEmpty()){
            if (userDao.update(Sno,Cname,Sname,Score,Sno_pre)==1) return  true;
        }
        return false;
    }


}
