package com.event.demo.base;

import com.event.demo.common.AjaxJson;

import com.event.demo.common.Page;
import com.event.demo.common.PageQuery;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



import com.yisi.stiku.web.util.WebUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.ParameterizedType;

public abstract class BaseController <T extends BaseUUIDEntity> {

    public Gson gson = new GsonBuilder().serializeNulls().create();

    public abstract BaseService getService();
    public Class getEntityClass(){
        return (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     * 获取实体
     */
    @RequestMapping("get")
    public void get(HttpServletRequest request, HttpServletResponse response, String id){
        WebUtils.writeJson(AjaxJson.success(this.getService().selectById(id)),request,response);
    }
    /**
     * 保存
     */
    @RequestMapping("save")
    public void save(HttpServletRequest request, HttpServletResponse response, String json){
        T entity = (T) gson.fromJson(json,this.getEntityClass());
        this.getService().save(entity);
        WebUtils.writeJson(AjaxJson.success(entity),request,response);

    }

    /**
     * 分页查询
     * @param filterJson
     * @param pageQueryJson
     */
    @RequestMapping("page")
    public void page(HttpServletRequest request, HttpServletResponse response, String filterJson, String pageQueryJson){
        T t = (T)gson.fromJson(filterJson, this.getEntityClass());
        PageQuery pageQuery = gson.fromJson(pageQueryJson, PageQuery.class);
        Page<T> page = this.getService().findByPageByEntity(t, pageQuery.current, pageQuery.size);
        WebUtils.writeJson(AjaxJson.success(page), request, response);
    }

    /**
     * 删除
     * @param id
     */
    @RequestMapping("delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, String id){
        this.getService().deleteById(id);
        WebUtils.writeJson(AjaxJson.success("删除成功"), request, response);
    }

}
