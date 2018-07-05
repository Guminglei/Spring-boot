/**   
 * <p>Title: DruidStatFilter.java</p>
 * @Package com.hello.boot1.filter 
 * <p>Description: TODO(用一句话描述该文件做什么)</p> 
 * <p>Company:上海策道科技信息服务有限公司</p>
 * @author guminglei
 * @since 2017年10月30日 下午3:19:08 
 * @version V1.0   
 */
package com.cdtech.vclass.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;

/** 
 * <p>Description: druid过滤器</p> 
 * <p>Company:上海策道科技信息服务有限公司</p>
 * @author guminglei
 * @version V1.0 
 */
@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",
initParams={
         @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
 }
)
public class DruidStatFilter extends WebStatFilter{
}
