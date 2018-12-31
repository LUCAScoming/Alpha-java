package com.event.demo.util;

import ;
import com.event.demo.common.Page;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: DELL
 * Date: 2018/8/8
 * Time: 11:18
 * To change this template use File | Settings | File Templates.
 */
public class PageUtil {

    public static <T> List<T> page(JSONObject pageJson, List<T> list) {
        if (ArrayUtils.isEmpty(list)) {
            return list;
        }
        int size = pageJson.getInt("size");
        int current = pageJson.getInt("current");
        current = current == 0 ? 1 : current;
//        自己写一个分页
        int startObj = (size - 1) * (current - 1);
        int afterObj = startObj + size;
        afterObj = afterObj > list.size() ? list.size() : afterObj;
        List<T> retList = list.subList(startObj, afterObj);
        return retList;
    }

    public static Map<String, Object> getPageMap(int totalElements, int current, int size, Object json) {
        Map<String, Object> retMap = new HashMap<>();
        Page page = new Page();
        page.setTotalElements(totalElements);
        page.setSize(size);
        page.setCurrentPage(current);
        retMap.put("page", page);
        retMap.put("json", json);
        return retMap;
    }
    public static Map<String, Object> getPageMap(long totalElements, int current, int size, Object json) {
        Map<String, Object> retMap = new HashMap<>();
        Page page = new Page();
        page.setTotalElements(totalElements);
        page.setSize(size);
        page.setCurrentPage(current);
        retMap.put("page", page);
        retMap.put("json", json);
        return retMap;
    }
}
