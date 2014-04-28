package org.masque.qq.demo.service.impl;

import javax.annotation.Resource;

import org.masque.qq.demo.dao.UserDao;
import org.masque.qq.demo.service.UserService;
import org.springframework.stereotype.Service;
@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	
	public boolean rightLogin(String userName, String passWord) {
		return userDao.rightLogin(userName, passWord);
	}

}
