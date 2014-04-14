package org.masque.qq.demo.vo;

public class User extends BaseVo implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7886983936678906986L;

	private String userName;
	
	private String passWord;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}
