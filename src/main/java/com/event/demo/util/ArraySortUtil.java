package com.event.demo.util;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArraySortUtil {

	public static void setValue(List<Float> valueList,Float value){
		int index = getValueIndex(valueList,value);
		if(index == -2){
			return;
		}else{
			if(index != -1){
				valueList.add(index,value);
			}else{
				valueList.add(value);
			}
		}
	}
	
	private static int getValueIndex(List<Float> valueList,Float value){
		int index = -1;
		if(valueList.contains(value)){
			index = valueList.indexOf(value);
		}else{//增加 value 的值 按照由小到大 插入list
			if(valueList.isEmpty()){
				return index;
			}else{
				index = binarySearch(valueList,value);
			}
		}
		return index;
	}
	
	public static int getIndexAsc(List<Float> valueList,Float value){
		int index = -1;
		if(valueList.contains(value)){
			index = valueList.indexOf(value);
		}
		return index;
	}
	
	public static int getIndexDesc(List<Float> valueList,Float value){
		int index = -1;
		Collections.sort(valueList,Collections.reverseOrder());
		if(valueList.contains(value)){
			index = valueList.indexOf(value);
		}
		return index;
	}
	
	private static  int binarySearch(List<Float> valueList, Float desValue){   
        int low = 0;   
        int high = valueList.size()-1;   
        while(low <= high) {   
            int middle = (low + high)/2;   
            if(desValue < valueList.get(middle) ) { 
            	if(middle - 1 >= 0){
            		if( desValue > valueList.get(middle-1)){
                		return middle;    
                	}else if( desValue == valueList.get(middle-1)){
                		return -2;   
                	}else{
                		 high = middle - 1;   
                	}
            	}else{
            		return 0;
            	}
            }else if(desValue > valueList.get(middle)) {   
               // high = middle - 1;  
                low = middle + 1;   
            }else {   
            	return middle;  
            }  
        }  
        return -1;  
   }  
	
   public static void main(String[] args) {
	   
	List<Float> t_list = new ArrayList<Float>();
	
	ArraySortUtil.setValue(t_list, 2.12f);
	ArraySortUtil.setValue(t_list, 2.1f);
	ArraySortUtil.setValue(t_list, 1.0f);
	ArraySortUtil.setValue(t_list, 1.1f);
	ArraySortUtil.setValue(t_list, 3.12f);
	ArraySortUtil.setValue(t_list, 0f);
	
	
	System.out.println("==============");
	for(Float f_t : t_list){
		System.out.println(f_t);
	}
	System.out.println("==============");
	
	
	
	System.out.println(ArraySortUtil.getValueIndex(t_list, 0f));
	
	System.out.println(ArraySortUtil.getValueIndex(t_list, 3.12f));
	
	System.out.println(ArraySortUtil.getValueIndex(t_list, 1.0f));
   }
   
}
