/**   
 * <p>Title: ResultCodeEnum.java</p>
 * @Package com.hello.boot1.enums 
 * <p>Description: 结果编码</p> 
 * <p>Company:上海策道科技信息服务有限公司</p>
 * @author guminglei
 * @since 2017年10月31日 下午2:29:48 
 * @version V1.0   
 */
package com.cdtech.vclass.enums;

/** 
 * <p>Description: TODO(用一句话描述该文件做什么)</p> 
 * <p>Company:上海策道科技信息服务有限公司</p>
 * @author guminglei
 * @version V1.0 
 */
public enum ResultCodeEnum {
	SUCCESS("0000","请求成功"),
    FAIL("0001","系统繁忙，请稍候再试");
	
	/** 
	 * <p>Title: </p> 
	 * <p>Description: </p> 
	 * @param code
	 * @param msg 
	 */
	private ResultCodeEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	private String code;
	private String msg;
	/** 
	 * @return code 
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 屏蔽java编译中的一些警告信息。unused这个参数是屏蔽：定义的变量在代码中并未使用且无法访问。
	 * java在编译的时候会出现这样的警告，加上这个注解之后就是告诉编译器，忽略这些警告，编译的过程中将不会出现这种类型的警告
	 */
	@SuppressWarnings("unused")
	public void setCode(String code) {
		this.code = code;
	}
	/** 
	 * @return msg 
	 */
	public String getMsg() {
		return msg;
	}
	@SuppressWarnings("unused")
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
