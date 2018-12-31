package com.event.demo.util;

import java.util.*;
import java.util.Map.Entry;

public class MapUtil {

	public static Map<Long, Float> sortMap_Long_Float_ByValue(Map<Long, Float> oriMap) {  
	    Map<Long, Float> sortedMap = new LinkedHashMap<Long, Float>();  
	    if (oriMap != null && !oriMap.isEmpty()) {  
	        List<Entry<Long, Float>> entryList = new ArrayList<Entry<Long, Float>>(oriMap.entrySet());
	        Collections.sort(entryList,
	                new Comparator<Entry<Long, Float>>() {
	                    public int compare(Entry<Long, Float> entry1,
	                            Entry<Long, Float> entry2) {
	                        float value1 = 0, value2 = 0;
	                        value1 = entry2.getValue();
	                        value2 = entry1.getValue();
	                         if(value2 - value1 > 0){
	                        	 return 1;
	                         }else if(value2 - value1 < 0){
	                        	 return -1;
	                         }else{
	                        	 return 0;
	                         }
	                    }
	                });
	        Iterator<Entry<Long, Float>> iter = entryList.iterator();
	        Entry<Long, Float> tmpEntry = null;
	        while (iter.hasNext()) {  
	            tmpEntry = iter.next();  
	            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());  
	        }  
	    }  
	    return sortedMap;  
	}


	/**
	 * 使用 Map按key进行排序
	 * @param map
	 * @return
	 */
	public static <V> Map<String, List<V>> sortMapByKey(Map<String, List<V>> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String, List<V>> sortMap = new TreeMap<String, List<V>>(
				(new MapKeyComparator()));
		sortMap.putAll(map);
		return sortMap;
	}
	
}
