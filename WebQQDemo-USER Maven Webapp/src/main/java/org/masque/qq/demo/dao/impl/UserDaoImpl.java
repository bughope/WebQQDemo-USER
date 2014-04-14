package org.masque.qq.demo.dao.impl;
import org.masque.qq.demo.base.BaseDao;
import org.masque.qq.demo.dao.UserDao;
import org.springframework.stereotype.Repository;
@Repository("userDao")
public class UserDaoImpl extends BaseDao implements UserDao{

	public boolean rightLogin(String userName, String passWord) {
		String hql = "from User where";
		return false;
	}

}
