package com.event.demo.base;

import com.event.demo.common.AjaxJson;
import com.event.demo.common.Page;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.ParameterizedType;

public abstract class BaseRestController<T extends BaseUUIDEntity> {

    public Gson gson = new GsonBuilder().serializeNulls().create();

    public abstract BaseService<T> getService();

    public Class getEntityClass(){
        return (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public AjaxJson get(){
        T obj = null;
        try {
            obj = (T) getEntityClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return AjaxJson.success(getService().findAll(obj));

    }

    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public AjaxJson getOne(@PathVariable String id){
        return AjaxJson.success(getService().selectById(id));
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public AjaxJson savePOST(String json){
        T t = (T) gson.fromJson(json, getEntityClass());
        this.getService().save(t);
        return  AjaxJson.success();
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public AjaxJson save(String json){
        T t = (T) gson.fromJson(json, getEntityClass());
        this.getService().save(t);
        return  AjaxJson.success();
    }

    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public AjaxJson delete(@PathVariable String id){
        this.getService().deleteById(id);
        return  AjaxJson.success();
    }

    @ResponseBody
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public AjaxJson page(String pageQuery){
        Page page = pageQuery == null ?  new Page() :gson.fromJson(pageQuery, Page.class);
        T obj = null;
        try {
            obj = (T) getEntityClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return  AjaxJson.success(getService().findByPageByEntity(obj, page.getCurrentPage(), page.getSize()));
    }

}
