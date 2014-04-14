package org.masque.qq.demo.base;

import java.io.Serializable;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
public abstract class BaseDao<T> extends HibernateDaoSupport {

	private static final Log logger = LogFactory.getLog(BaseDao.class);

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		logger.debug("setSuperSessionFactory");
		super.setSessionFactory(sessionFactory);
	}
	
	public void save(T entity){
		save(entity);
	}
	
	public void saveOrUpdate(T entity){
		saveOrUpdate(entity);
	} 
	
	public void findById(Class<T> clazz,Serializable id){
		findById(clazz, id);
	}
}
