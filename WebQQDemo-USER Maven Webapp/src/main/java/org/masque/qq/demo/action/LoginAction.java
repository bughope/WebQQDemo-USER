package org.masque.qq.demo.action;

import javax.annotation.Resource;

import org.masque.qq.demo.base.BaseAction;
import org.masque.qq.demo.service.UserService;

/**
 * 
 * <p>Project: Masque's Base</p>
 * <p>Description: 登陆的action</p>
 * <p>Copyright (c) 2014 Masque.All Rights Reserved.</p>
 * @author <a href="masque.java@gmail.com">Masque</a>
 */
public class LoginAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name="userService")
	private UserService userService;
	
	private String userName;
	
	private String passWord;
	
	@Override
	public String execute() {
		System.out.println("userName:"+userName);
		System.out.println("passWord:"+passWord);
		System.out.println(userService.rightLogin(userName, passWord));
		return null;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
}
