package com.home.common.aop;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.home.common.annotation.opLog;
import com.home.module.log.service.ISysLogService;

/**
 * 系统日志切面类
  * <p>Title: SysLogAspect</p>  
  * <p>Description: </p>  
  * @author zhangyf 
  * @date 2018年11月15日
 */
@Component
@Aspect
public class SysLogAspect {
	
	@Autowired
	private ISysLogService sysLogService;
	
	@Pointcut("execution(* com.home.module.*.service.impl.*.*(..)) && @annotation(com.home.common.annotation.opLog)")
	public void SysLog() {}
	
	@Around("SysLog()")
	public Object advice(ProceedingJoinPoint pjp) {
		Instant start = Instant.now();
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();
		opLog log = method.getAnnotation(opLog.class);
		
		com.home.model.SysLog syslog = new com.home.model.SysLog();
		syslog.setOperation(log.value());
		syslog.setMethod(method.getName());
		syslog.setParams(signature.getParameterNames().toString());
		
		Object result = null;
		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		Instant end = Instant.now();
		Duration dr = Duration.between(start, end);
		
		syslog.setTime(dr.getSeconds() * 1000);
		syslog.setCreateDate(LocalDateTime.now());
		sysLogService.save(syslog);
		return result;
	}
}
