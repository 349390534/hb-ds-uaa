package com.howbuy.uaa.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortUtil<E> {
	/** 
     *  
     * @param list 要排序的集合 
     * @param method 要排序的实体的属性所对应的get方法 
     * @param sort desc 为正序   
     */  
    public void sort(List<E> list, final String method, final String sort) {  
        // 用内部类实现排序  
        Collections.sort(list, new Comparator<E>() {  
  
            public int compare(E a, E b) {  
                int ret = 0;  
                try {  
                    // 获取m1的方法名  
                    Method m1 = a.getClass().getMethod(method);  
                    // 获取m2的方法名  
                    Method m2 = b.getClass().getMethod(method);  
//                    Number o1 = (Number)m1.invoke(((E)a));
//                    Number o2 = (Number)m2.invoke(((E)b));
                  
                    if (sort != null && "desc".equals(sort)) {  
                    	if(m1.getReturnType() == String.class){
                    		ret = m2.invoke(((E)b)).toString().compareTo(m1.invoke(((E)a)).toString());
                    	}else{
                    		Number o1 = (Number)m1.invoke(((E)a));
                            Number o2 = (Number)m2.invoke(((E)b));
                    		if(o2.doubleValue()>o1.doubleValue()){
                        		ret = 1;
                        	}else if(o2.doubleValue() == o1.doubleValue()){
                        		ret = 0;
                        	}else{
                        		ret = -1;
                        	}
                    	}
                    	
//                        ret = m2.invoke(((E)b), null).toString().compareTo(m1.invoke(((E)a),null).toString());  
  
                    } else { 
                    	if(m1.getReturnType() == String.class){
                    		ret = m1.invoke(((E)a)).toString().compareTo(m2.invoke(((E)b)).toString());
                    	}else{
                    		Number o1 = (Number)m1.invoke(((E)a));
                            Number o2 = (Number)m2.invoke(((E)b));
                    		if(o1.doubleValue()>o2.doubleValue()){
                        		ret = 1;
                        	}else if(o1.doubleValue() == o2.doubleValue()){
                        		ret = 0;
                        	}else{
                        		ret = -1;
                        	}
                    	}
                    	
                        // 正序排序  
//                        ret = m1.invoke(((E)a), null).toString().compareTo(m2.invoke(((E)b), null).toString());  
                    }  
                } catch (NoSuchMethodException ne) {  
                   ne.printStackTrace();
                } catch (IllegalArgumentException e) {  
                    e.printStackTrace();  
                } catch (IllegalAccessException e) {                     
                    e.printStackTrace();  
                } catch (InvocationTargetException e) {  
                    e.printStackTrace();  
                }  
                return ret;  
            }  
        });  
    }  
}  

