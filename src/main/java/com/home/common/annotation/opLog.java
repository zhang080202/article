package com.home.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用户操作日志
  * <p>Title: SysLog</p>  
  * <p>Description: </p>  
  * @author zhangyf 
  * @date 2018年11月15日
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface opLog {
	
	String value() default "";
}
