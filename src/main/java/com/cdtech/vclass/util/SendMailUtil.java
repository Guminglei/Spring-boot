/**   
 * <p>Title: SendMailUtil.java</p>
 * @Package com.hello.boot1.util 
 * <p>Description: TODO(用一句话描述该文件做什么)</p> 
 * <p>Company:上海策道科技信息服务有限公司</p>
 * @author guminglei
 * @since 2017年11月21日 上午10:32:59 
 * @version V1.0   
 */
package com.cdtech.vclass.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/** 
 * <p>Description: 发送邮件</p> 
 * <p>Company:上海策道科技信息服务有限公司</p>
 * @author guminglei
 * @version V1.0 
 */
public class SendMailUtil {
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String username;
	
	public void testSendHtml(String mailAdress,String userId) {
	    MimeMessage message = null;
	    try {
	        message = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        helper.setFrom(username);
	        helper.setTo(mailAdress);
	        helper.setSubject("标题：发送Html内容");

	        StringBuffer sb = new StringBuffer();
	        sb.append("<h1>大标题-h1</h1>")
	          .append("<p style='color:#F00'>红色字</p>")
	          .append("<p style='text-align:right'>右对齐</p>");
	        helper.setText(sb.toString(), true);
	    } catch (MessagingException e) {
	        e.printStackTrace();
	    }

	    javaMailSender.send(message);
	}
	
}
