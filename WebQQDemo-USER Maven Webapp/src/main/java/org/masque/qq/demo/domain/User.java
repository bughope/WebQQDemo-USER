package org.masque.qq.demo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "USER")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String name;

	private String password;

	private java.util.Date createTime;

	private String createMan;

	private String email;

	private Integer activeStatus;

	private Integer lockStatus;

	private java.util.Date lastLoginTime;

	private String lastLoginIp;

	private String describe;

	private String remark;

	public User(){}
	
	public User(String id) {
		this.id = id;
	}
	
	@Id
	@Column(name="USER_ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 

	@Column(name="PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="CREATE_TIME")
	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	@Column(name="CREATE_MAN")
	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="ACTIVE_STATUS")
	public Integer getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(Integer activeStatus) {
		this.activeStatus = activeStatus;
	}

	@Column(name="LOCK_STATUS")
	public Integer getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(Integer lockStatus) {
		this.lockStatus = lockStatus;
	}

	@Column(name="LAST_LOGIN_TIME")
	public java.util.Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(java.util.Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Override
	public String toString() {
		return com.google.common.base.Objects.toStringHelper(this)
				.add("id", id)
				.add("name", name)
				.add("password", password)
				.add("createMan", createMan)
				.add("email", email)
				.add("activeStatus", activeStatus)
				.add("lockStatus", lockStatus)
				.add("lastLoginTime", lastLoginTime)
				.add("lastLoginIp", lastLoginIp)
				.add("describe", describe)
				.add("remark", remark)
				.toString();
	}
}
