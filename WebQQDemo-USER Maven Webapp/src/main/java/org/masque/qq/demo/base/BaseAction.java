package org.masque.qq.demo.base;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * <p>Project: Masque's Base</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Masque.All Rights Reserved.</p>
 * @author <a href="masque.java@gmail.com">Masque</a>
 */
public class BaseAction extends ActionSupport implements ServletRequestAware,ServletResponseAware,SessionAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected HttpServletRequest request;
	
	protected HttpServletResponse response;
	
	protected Map<String, Object> session;

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	@Override
	public String execute() throws Exception {
        return SUCCESS;
    }
}
