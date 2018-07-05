
package com.cdtech.vclass.util;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
/**
 * <p> Description:urlhelp 工具类</p>;
 * <p> Company:上海策道科技信息服务有限公司</p>;
 *
 * @author lirenyi;
 * @version V1.0   ;
 **/
public class UrlHelper {
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(UrlHelper.class);
    public static final ResourceUrlProvider resourceUrlProvider=new ResourceUrlProvider() ;

    public UrlHelper() {
    }

      /**
           * <p>Description: 获取url encode  的地址</p>
           *
           * @param 
           * @return  
           * @author lirenyi
           * @date 17/8/28
           */
    public static String encoder(String url, String charset) {
        try {
            if (null == url) {
                return url;
            }

            if (!StringUtils.isEmpty(url)) {
                return URLEncoder.encode(url, charset);
            }
        } catch (Exception e) {
            logger.error("UrlHelper encoder 出错",e);
        }
        return url;
    }
    /**
     * <p>Description: 获取url encode  的地址</p>
     *
     * @param
     * @return
     * @author lirenyi
     * @date 17/8/28
     */
    public static String encoder(String url) {
        return encoder(url, "utf-8");
    }

    public static String decoder(String url, String charset) {
        try {
            if (!StringUtils.isEmpty(url)) {
                return URLDecoder.decode(url, charset);
            }
        } catch (Exception e) {
            logger.error("UrlHelper decoder 出错",e);
        }

        return null;
    }


    /**
     * <p>Description: 获取url 解码  的地址</p>
     *
     * @param
     * @return
     * @author lirenyi
     * @date 17/8/28
     */
    public static String decoder(String url) {
        return decoder(url, "utf-8");
    }


    public static String clearParameter(String url, String name) {
        url = url.replaceAll(name + "=[^&]+&?", "");
        if (url.endsWith("?")) {
            url = url.substring(0, url.length() - 1);
        }

        if (url.endsWith("&")) {
            url = url.substring(0, url.length() - 1);
        }

        return url;
    }

    /**
     * <p>Description: 获取url 是否是全路径</p>
     *
     * @param
     * @return
     * @author lirenyi
     * @date 17/8/28
     */
    public static boolean isFullUrl(String link) {
        if (link == null) {
            return false;
        } else {
            link = link.trim().toLowerCase();
            return link.startsWith("http://") || link.startsWith("https://") || link.startsWith("file://");
        }
    }

      /**
           * <p>Description:获取链接全路径</p>
           *
           * @param 
           * @return  
           * @author lirenyi
           * @date 17/8/28
           */
    public static String getFullUrl(String pageUrl, String link) {
        if (isFullUrl(link)) {
            return link;
        } else {
            int slashIndex;
            if (link != null && link.startsWith("?")) {
                int isLinkAbsolute1 = pageUrl.indexOf(63);
                slashIndex = pageUrl.length();
                return isLinkAbsolute1 < 0 ? pageUrl + link : (slashIndex > isLinkAbsolute1 + 1 ? pageUrl.substring(0, isLinkAbsolute1) + link : (isLinkAbsolute1 == slashIndex - 1 ? pageUrl.substring(0, slashIndex - 1) + link : pageUrl + "&" + link.substring(1)));
            } else {
                if(link==null){
                    return null;
                }
                boolean isLinkAbsolute = link.startsWith("/");
                if (!isFullUrl(pageUrl)) {
                    pageUrl = "http://" + pageUrl;
                }

                slashIndex = isLinkAbsolute ? pageUrl.indexOf("/", 8) : pageUrl.lastIndexOf("/");
                if (slashIndex <= 8) {
                    pageUrl = pageUrl + "/";
                } else {
                    pageUrl = pageUrl.substring(0, slashIndex + 1);
                }

                return isLinkAbsolute ? pageUrl + link.substring(1) : pageUrl + link;
            }
        }
    }

      /**
           * <p>Description:获取 上下文地址 并拼接 path</p>
           *
           * @param 
           * @return  
           * @author lirenyi
           * @date 17/8/28
           */
    public static String getContextPath(HttpServletRequest request, String path) {
        return StringUtils.isEmpty(path) ? request.getContextPath() : request.getContextPath() + path;
    }
    /**
     * <p>Description:获取 上下文地址 并拼接 path</p>
     *
     * @param
     * @return
     * @author lirenyi
     * @date 17/8/28
     */
    public static String getContextPath(String path) {
        return getContextPath(RequestHelper.getRequest(), path);
    }

    public static String getServerUrl(HttpServletRequest request, String... postfixUrl) {
        if (null == request) {
            throw new RuntimeException("参数异常：request=" + request);
        } else {
            StringBuffer url = new StringBuffer(request.getScheme());
            url.append("://");
            url.append(request.getServerName());
            if (80 != request.getServerPort()) {
                url.append(":").append(request.getServerPort());
            }

            url.append(request.getContextPath());
            if (null != postfixUrl && 0 != postfixUrl.length) {
                url.append(postfixUrl[0]);
            }

            return url.toString();
        }
    }
    /**
     * <p>Description:获取 上下文地址 并拼接 path</p>
     *
     * @param
     * @return
     * @author lirenyi
     * @date 17/8/28
     */
    public static String getContextPath() {
        return getContextPath(RequestHelper.getRequest(), (String) null);
    }
    /**
     * <p>Description:获取 上下文地址 并拼接 path</p>
     *
     * @param
     * @return
     * @author lirenyi
     * @date 17/8/28
     */
    public static String getForLookupPath(String url) {
        return getContextPath(url);
    }

    public static String getRealPath(String path) {
        return RequestHelper.getRequest().getServletContext().getRealPath(path);
    }

}
