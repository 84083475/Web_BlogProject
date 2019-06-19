package com.util;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class Conversion {
	public static void conver(Object obj,HttpServletRequest request){
		//通过反射得到model类
		Class cla = obj.getClass();
		//得到该类下面的所有属性
		Field [] fields = cla.getDeclaredFields();
		//便利得到每一个属性
		for(Field f:fields){
			//通过model里面的属性名字得到表单提交的参数  ！！！注意  model类里面的属性名和表单参数名字必须一致
			String parameter = request.getParameter(f.getName());
			//给每一个属性给权限可以使用
			f.setAccessible(true);
			//得到属性的类型
			Class cls = f.getType();
			//判断属性是否为数组
			if(!cls.isArray()){
				//获取属性类型对应的字符串
				String type = cls.getName();
				//判断属性进行转换赋值 	
				if("int".equals(type)||"java.lang.Integer".equals(type)){
					int par = Integer.parseInt(parameter);
					//给属性赋值
					try {
						f.set(obj, par);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if("double".equals(type)||"java.lang.Double".equals(type)){
					double par = Double.parseDouble(parameter);
					//给属性赋值
					try {
						f.set(obj, par);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if("java.util.Date".equals(type)){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					try {
						f.set(obj, sdf.parse(parameter));
					} catch (IllegalArgumentException | IllegalAccessException | ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if("java.lang.String".equals(type)){
					try {
						f.set(obj, parameter);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}else{
				String [] arr = request.getParameterValues(parameter);
				
				if(int[].class==cls||Integer[].class==cls){
					
					int [] arr_int = new int[arr.length];
					
					for(int i=0;i<arr.length;i++){
						arr_int [i]=Integer.parseInt(arr[i]);
					}
					
					try {
						f.set(obj, arr_int);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else if(double[].class==cls||Double[].class==cls){
					
					double [] arr_double = new double[arr.length];
					
					for(int i=0;i<arr.length;i++){
						arr_double [i]=Double.parseDouble(arr[i]);
					}
					
					try {
						f.set(obj, arr_double);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else if(Date[].class==cls){
					Date [] arr_date = new Date[arr.length];
					
					for(int i=0;i<arr.length;i++){
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						try {
							arr_date[i]=sdf.parse(arr[i]);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					try {
						f.set(obj, arr_date);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(String [].class==cls){
					try {
						f.set(obj, arr);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
