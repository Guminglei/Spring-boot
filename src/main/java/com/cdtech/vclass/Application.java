package com.cdtech.vclass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication申明让spring boot自动给程序进行必要的配置，
 *等价于以默认属性使用@Configuration，@EnableAutoConfiguration和@ComponentScan
 */

@SpringBootApplication
public class Application 
{
    public static void main( String[] args )
    {
    	// 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(Application.class, args);
    }
}
