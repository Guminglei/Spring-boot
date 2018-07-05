package com.cdtech.vclass.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * 
 * <p>Description: 处理静态资源（自定义资源映射</p> 
 * <p>Company:上海策道科技信息服务有限公司</p>
 * @author guminglei
 * @version V1.0
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{
	
	 @Override
	 public void addResourceHandlers(ResourceHandlerRegistry registry){ 
		 registry.addResourceHandler("/ui/**").addResourceLocations("classpath:/ui/");
	 }
	 
	 /**
      * <p>Description:设置默认首页</p>
      *
      * @param 
      * @return  
      * @author lirenyi
      * @date 17/8/11
      */
	 @Override
	 public void addViewControllers(ViewControllerRegistry registry) {
		 registry.addViewController("/").setViewName("redirect:/login/");
		 registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		 super.addViewControllers(registry);
	 }
}