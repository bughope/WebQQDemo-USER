package org.masque.qq.demo.base;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;


/**
 * 
 * <p>Project: Masque's Base</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Masque.All Rights Reserved.</p>
 * @author <a href="masque.java@gmail.com">Masque</a>
 */
public class BaseDao extends HibernateDaoSupport{

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

}
