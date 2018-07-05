/**   
 * <p>Title: ControllerInterceptor.java</p>
 * @Package com.hello.boot1.filter 
 * <p>Description: TODO(用一句话描述该文件做什么)</p> 
 * <p>Company:上海策道科技信息服务有限公司</p>
 * @author guminglei
 * @since 2017年10月31日 下午1:24:42 
 * @version V1.0   
 */
package com.cdtech.vclass.filter;

import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/** 
 * <p>Description: 拦截器：记录用户操作日志</p> 
 * <p>Company:上海策道科技信息服务有限公司</p>
 * @author guminglei
 * @version V1.0 
 */
@Aspect
@Component
public class ControllerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);
	
	 /** 
     * 定义拦截规则：拦截com.cdtech.vclass.controller包下面的所有类中，有@RequestMapping注解的方法。 
     */  
    @Pointcut("execution(* com.cdtech.vclass.controller..*(..)) and @annotation(org.springframework.web.bind.annotation.RequestMapping)")  
    public void controllerMethodPointcut(){} 
    
    /** 
     * 拦截器具体实现 
     * @param pjp 
     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。） 
     */  
    @Around("controllerMethodPointcut()") //指定拦截器规则；也可以直接把“execution(* com.xjj.........)”写进这里  
    public Object Interceptor(ProceedingJoinPoint pjp){
    	long beginTime = System.currentTimeMillis();
    	MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod(); //获取被拦截的方法
		String methodName = method.getName(); //获取被拦截的方法名
		
		Set<Object> allParams = new LinkedHashSet<>(); //保存所有请求参数，用于输出到日志中
		logger.info("请求开始，方法：{}", methodName);
		
		Object result = null;

		Object[] args = pjp.getArgs(); //返回目标方法的参数
		for(Object arg : args){
			logger.debug("arg: {}", arg);
			if (arg instanceof Map<?, ?>) {
				//提取方法中的MAP参数，用于记录进日志中
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) arg;
				allParams.add(map);
			}
		}
			
		try {
				// 一切正常的情况下，继续执行被拦截的方法
				result = pjp.proceed();
		} catch (Throwable e) {
			logger.info("exception: ", e);
		}finally{
			long costMs = System.currentTimeMillis() - beginTime;
			logger.info("{}请求结束，耗时：{}ms", methodName, costMs);
		}
		
		return result;
	}
    	
}
