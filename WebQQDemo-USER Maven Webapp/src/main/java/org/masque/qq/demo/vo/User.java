package org.masque.qq.demo.vo;

public class User extends BaseVo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7886983936678906986L;

	private String userName;

	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return com.google.common.base.Objects.toStringHelper(this)
				.add("userName", userName).add("password", password)
				.toString();
	}
}
