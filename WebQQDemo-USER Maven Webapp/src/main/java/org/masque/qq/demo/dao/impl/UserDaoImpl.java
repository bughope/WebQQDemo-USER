package org.masque.qq.demo.dao.impl;
import java.util.List;

import org.hibernate.Query;
import org.masque.qq.demo.base.BaseDao;
import org.masque.qq.demo.dao.UserDao;
import org.masque.qq.demo.domain.User;
import org.springframework.stereotype.Repository;
@Repository("userDao")
public class UserDaoImpl extends BaseDao<User> implements UserDao{

	public boolean rightLogin(String userName, String passWord) {
		String hql = "from User u where u.name=:userName and u.password=:passWord";
		Query query = super.currentSession().createQuery(hql)
											.setString("userName", userName)
											.setString("passWord", passWord);
		List<User> l = query.list();
		return l!=null&&l.size()>0;
	}

}
