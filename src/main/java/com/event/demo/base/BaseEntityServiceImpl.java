/*
package com.event.demo.base;

import com.event.demo.common.EntityUtils;
import com.event.demo.common.Page;
import com.event.demo.common.PageQuery;
import com.yisi.stiku.db.service.impl.BaseServiceImpl;

import com.yisi.stiku.web.util.LoginSesionUtil;
import org.springframework.data.domain.PageImpl;

import java.util.Date;

public abstract class BaseEntityServiceImpl<T extends BaseUUIDEntity> extends BaseServiceImpl<String, T> {


    public String saveEntity(T t) {
        if(t.getId()==null||t.getId().equals("")){
            t.setId(EntityUtils.genUUID());
            this.insert(t);
            return t.getId();
        }else{
            this.update(t);
            return t.getId();
        }
    }

    public Page<T> findByPageHelper(PageQuery pageQuery, T t, String orderSql){
        Page<T> basePageHelper = new Page<>();
        t.setDr(0);
        if(null==orderSql||"".equals(orderSql)) {
            orderSql = " order by created_dt desc";
        }
        PageImpl<T> page = this.findByPage(t, pageQuery.current, pageQuery.size, orderSql);
        basePageHelper.setCurrentPage(pageQuery.current);
        basePageHelper.setSize(pageQuery.size);
        basePageHelper.setTotalElements(page.getTotalElements());
        basePageHelper.setList(page.getContent());
        return basePageHelper;
    }

    @Override
    public void insert(T t) {
        t.setCreatedBy(LoginSesionUtil.getUserName());
        t.setCreatedDt(new Date());
        t.setUpdatedBy(LoginSesionUtil.getUserName());
        t.setUpdatedDt(new Date());
        t.setDr(0);
        super.insert(t);
    }

    @Override
    public boolean update(T t) {
        t.setUpdatedBy(LoginSesionUtil.getUserName());
        t.setUpdatedDt(new Date());
        return super.update(t);
    }

    public  void delete(String id){
        T t = this.findById(id);
        t.setDr(1);
        t.setUpdatedDt(new Date());
        t.setUpdatedBy(LoginSesionUtil.getUserName());
        this.update(t);
    }

    public T get(String id){
        return this.findById(id);
    }

}
*/
