package com.event.demo.util;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ArrayUtils {
	
	/**
	 * 将 字符串 转为 list 对象
	 * @param str 字符串
	 * @param split 字符串 分隔符
	 * @return
	 */
	public static List<String> StringToList(String str,String split){
		List<String> rtnList = new ArrayList<String>();
		if(!str.isEmpty()){
			String[] strArray = str.split(",");
			for(String strTemp : strArray){
				rtnList.add(strTemp);
			}
		}
		return rtnList;
	}
	
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> toMap(Collection<V> list, String field) {
        if (null == list) {
            return null;
        }

        Map<K, V> map = new HashMap<>();
        if (list.isEmpty()) {
            return map;
        }

        try {
            for (V t : list) {
                map.put((K) PropertyUtils.getProperty(t, field), t);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return map;

    }
	
    @SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> toMap(Collection<?> list, String keyField, String valueField) {
        if (null == list) {
            return null;
        }

        Map<K, V> map = new HashMap<>();
        if (list.isEmpty()) {
            return map;
        }

        try {
            for (Object t : list) {
                map.put((K) PropertyUtils.getProperty(t, keyField), (V)PropertyUtils.getProperty(t, valueField));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return map;

    }

    @SuppressWarnings("unchecked")
	public static <T> List<T> toList(Collection<?> list, String field) {
        if (null == list) {
            return null;
        }

        List<T> resultList = new ArrayList<>();

        try {
            for (Object t : list) {
                resultList.add((T)PropertyUtils.getProperty(t, field));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return resultList;

    }
    
	/**
	 * list转Map
	 * @param list
	 * @param fieldName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> list2Map(List<V> list, String fieldName){
		Map<K, V> map = new HashMap<K, V>();
		if(!isEmpty(list)){
			try{
				for(V value : list){
					K k = (K) PropertyUtils.getProperty(value, fieldName);
					map.put(k, value);
				}
			}catch(Exception e){
				throw new IllegalArgumentException("field can't match the key");
			}
		}
		return map;
	}
	
	/**
	 * list转Map
	 * @param list
	 * @param fieldName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, List<V>> list2MapList(List<V> list, String fieldName){
		Map<K, List<V>> map = new HashMap<K, List<V>>();
		if(!isEmpty(list)){
			try{
				for(V value : list){
					K k = (K) PropertyUtils.getProperty(value, fieldName);
					if(map.containsKey(k)){
						map.get(k).add(value);
					}else{
						List<V> valueList = new ArrayList<V>();
						valueList.add(value);
						map.put(k, valueList);
					}
				}
			}catch(Exception e){
				throw new IllegalArgumentException("field can't match the key");
			}
		}
		return map;
	}

	/**
	 * 判定list是否为空
	 * @param list
	 * @return
	 */
	public static boolean isEmpty(List<?> list) {
		if (list == null)
			return true;
		return list.isEmpty();
	}
	
}
