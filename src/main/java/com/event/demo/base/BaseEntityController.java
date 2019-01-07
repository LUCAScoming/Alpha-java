/*
package com.event.demo.base;

import com.event.demo.common.AjaxJson;

import com.event.demo.common.Page;
import com.event.demo.common.PageQuery;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import com.yisi.stiku.web.util.WebUtils;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;

public abstract class BaseEntityController<T extends BaseUUIDEntity> {

    public Gson gson = new GsonBuilder().serializeNulls().create();

    public abstract BaseEntityServiceImpl getService();
    public abstract String name();
    public Class getEntityClass() {
        return (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    */
/**
     * 列表页面
     * @return
     *//*

    @RequestMapping("goIndex")
    public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/html/views/mg/"+name()+"/"+name()+"-manager.html").forward(request, response);
    }
    */
/**
     * 编辑页面
     * @return
     *//*

    @RequestMapping("goEdit")
    public void chapterEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/html/views/mg/"+name()+"/"+name()+"-edit.html").forward(request, response);

    }

    */
/**
     * 获取实体
     *//*

    @RequestMapping("get")
    public void get(HttpServletRequest request, HttpServletResponse response, String id){
        WebUtils.writeJson(AjaxJson.success(this.getService().get(id)),request,response);

    }
    */
/**
     * 保存
     *//*

    @RequestMapping("save")
    public void save(HttpServletRequest request, HttpServletResponse response, String json){
        T entity = (T) gson.fromJson(json,this.getEntityClass());
        this.getService().saveEntity(entity);
        WebUtils.writeJson(AjaxJson.success(entity),request,response);

    }

    */
/**
     * 分页查询
     * @param filterJson
     * @param pageQueryJson
     *//*

    @RequestMapping("page")
    public void page(HttpServletRequest request, HttpServletResponse response, String filterJson, String pageQueryJson){
        T t = (T)gson.fromJson(filterJson, this.getEntityClass());
        PageQuery pageQuery = gson.fromJson(pageQueryJson, PageQuery.class);
        Page<T> page = this.getService().findByPageHelper(pageQuery, t, null);
        WebUtils.writeJson(AjaxJson.success(page), request, response);
    }

    */
/**
     * 删除
     * @param id
     *//*

    @RequestMapping("delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, String id){
        this.getService().delete(id);
        WebUtils.writeJson(AjaxJson.success("删除成功"), request, response);
    }

}
*/
