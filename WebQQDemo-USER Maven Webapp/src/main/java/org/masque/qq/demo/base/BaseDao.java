package org.masque.qq.demo.base;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
public abstract class BaseDao extends HibernateDaoSupport {

	private static final Log logger = LogFactory.getLog(BaseDao.class);

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public void evict(Object instance){
		getSession().evict(instance);
		getSession().flush();
	}

	/**
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public Object findObjectById(Class entityClass,Serializable id) {
		return getHibernateTemplate().get(entityClass, id);
	}
		
	/**
	 * 
	 * @param entity
	 */
	public void save(Object entity) {
		getHibernateTemplate().save(entity);
	}

	/**
	 * 
	 * @param entity
	 */
	public void update(Object entity) {
		getHibernateTemplate().update(entity);
	}
	
	/**
	 * 批量执行sql
	 * @param sqlList
	 */
	public int updateBatchSqls(List<String> sqlList){
		int result = 0;
		for(String sql:sqlList){
			SQLQuery sqlQuery = getSession().createSQLQuery(sql);
			result+=sqlQuery.executeUpdate();
		}
		return result;
	}
	/**
	 * 批量执行sql
	 * @param sqlList
	 */
	public int updateBatchHqls(List<String> hqlList){
		int result = 0;
		for(String hql:hqlList){
			Query query = getSession().createQuery(hql);
			result+=query.executeUpdate();
		}
		return result;
	}
	/**
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(Object entity) {
	    getHibernateTemplate().saveOrUpdate(entity);
	}
	
	
	public void merge(Object entity){
		getHibernateTemplate().merge(entity);
	}
	/**
	 * 
	 * @param entity
	 */
	public void delete(Object entity) {
		getHibernateTemplate().delete(entity);
	}
	
	
	public List find(String hql) {
		return getHibernateTemplate().find(hql);
	}
	
	public void flush() {
	    getHibernateTemplate().flush();
	}
	
	/**
	 * 截取第一个匹配记录的结果，没有返回null
	 * @param hql
	 * @return
	 */
	public Object getFirstElement(String hql) {
	    List<Object> list = getHibernateTemplate().find(hql);
	    return list.size() > 0 ? list.get(0) : null;
	}
	/**
	 * 截取第一个匹配记录的结果，没有返回null
	 * @param hql
	 * @return
	 */
	public Object getFirstElement(Query query) {
		query.setMaxResults(1);
	    List<Object> list = query.list();
	    return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * 截取第一个匹配记录的结果，没有返回null
	 * @param hql hql中的参数用?占位符，不要用:field，否则会报错
	 * @param values
	 * @return
	 */
	public Object getFirstElement(String hql,Object... values) {
	    List<Object> list = getHibernateTemplate().find(hql, values);
	    return list.size() > 0 ? list.get(0) : null;
	}
	/**
	 * @param hql
	 * @return
	 */
	public Object uniqueElement(String hql) {
	    List<Object> list = getHibernateTemplate().find(hql);
	    return list.size() == 1 ? list.get(0) : null;
	}
	/**
	 * @param hql
	 * @return
	 */
	public Object uniqueElement(String hql,Object... values) {
	    List<Object> list = getHibernateTemplate().find(hql,values);
	    return list.size() == 1 ? list.get(0) : null;
	}

	
	/**
	 * @param hql
	 * @return
	 */
	public Object uniqueElement(Query query) {
		List<Object> list = query.list();
		return list.size() == 1 ? list.get(0) : null;
	}
	
	/**
	 * @param param
	 * @param querys
	 */
	protected void setQueryParameters(Map<String, Object> param, Query query) { 
		if(param == null || param.size()==0 || query == null)
			return;
		Iterator<Entry<String, Object>> it = param.entrySet().iterator();
		String hql = query.getQueryString();
		while(it.hasNext()) {
			Entry<String, Object> entry = it.next();
			if(!hql.contains(":"+entry.getKey()))
				continue;
			if(entry.getValue() instanceof Object[]) {
				query.setParameterList(entry.getKey(), (Object[]) entry.getValue());
			}else if(entry.getValue() instanceof Collection) {
				query.setParameterList(entry.getKey(), (Collection) entry.getValue());
			}else
				query.setParameter(entry.getKey(), entry.getValue());
		}
	}
	/**
	 * @param session
	 */
	protected void setFilter(Map<String, Map<String, Object>> filterParam, Session session) {
	    if (filterParam == null || filterParam.size()==0 || session == null)
	        return;
	    Iterator<Entry<String, Map<String, Object>>> it = filterParam.entrySet().iterator();
	    while (it.hasNext()) {
	        Entry<String, Map<String, Object>> entry = it.next();
	        String key = entry.getKey();
	        Filter filter  = session.enableFilter(key);
	        Map<String, Object> map = entry.getValue();
	        Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
	        while (iterator.hasNext()) {
	            Entry<String, Object> e = iterator.next();
	            filter.setParameter(e.getKey(), e.getValue());
	        }
	    }
	}
	
	protected int countQueryByHql(String hql) {
		return countQueryByHql(hql, null);
	}
	
	/**
	 * 总记录数
	 * @param hql
	 * @return
	 */
	protected int countQueryByHql(String hql, Map<String, Object> param) {
	    return countQueryByHqlWithFilter(hql, param, null);
	}
	
	protected int countQueryByHqlWithFilter(String hql, Map<String, Object> param, Map<String, Map<String, Object>> filterParam) {
	    int i = -1;
	    if ((i = hql.toUpperCase().indexOf("FROM")) != -1) {
	        hql = hql.substring(i);
	    }
	    i = -1;
	    if ((i = hql.toUpperCase().indexOf("ORDER BY")) != -1) {
	        hql = hql.substring(0, i);
	    }
	    i = -1;
	    hql = hql.replace("fetch", " ");
	    hql = hql.replace("FETCH", " ");
	    String counthql = "SELECT COUNT(*) " + hql;
	    try {
	        Session session = getSession();
	        setFilter(filterParam, session);
	        Query query = session.createQuery(counthql);
	        setQueryParameters(param, query);
	        Object o = null;
	        if ((i = hql.toUpperCase().indexOf("GROUP BY")) != -1){
	        	o = query.list().size();
	        }else {
	        	o= query.uniqueResult();
	        }
	        return Integer.valueOf(o.toString());
	    } catch (Exception e) {
	        throw new RuntimeException("解析hql语句出错: " + e.getMessage()+ "\n\n"  + counthql);
	    }
	}
	

	/**
	 * hql分页实现
	 * @param hql
	 * @param pageNo  不需要分页传-1
	 * @param pageSize 不需要分页传-1	
	 * @return
	 */
	protected List getHQLList(String hql,int start,int end) {
		return getHQLList(hql, null, start, end);
	}
	
	public List getHQLList(String hql, Map<String, Object> param) {
	    return getHQLListWithFilter(hql, param,  null, -1, -1);
	}
	
	public List getHQLList(String hql, Map<String, Object> param, int start,
			int pageSize) {
		return getHQLListWithFilter(hql, param,  null, start, pageSize);
	}
	
	public List getHQLListWithFilter(String hql, Map<String, Object> param, Map<String, Map<String, Object>> filterParam, int start,
	        int pageSize) {
	    Session session = getSession();
	    setFilter(filterParam, session);
	    Query query = session.createQuery(hql);
	    setQueryParameters(param, query);
	    if (start > -1) {                      
	        query.setFirstResult(start);					
	    }
	    if (pageSize > -1) {
	        query.setMaxResults(pageSize);		  	
	    }
	    return query.list();
	}
	
	protected Query createQuery(String hql, Map<String, Object> param) {
	    Query query = getSession().createQuery(hql);
	    setQueryParameters(param, query);
	    return query;
    }
	
	/**
	 * @param hql
	 * @param param
	 * @param filterParam
	 * @return
	 */
	protected int countQueryByHqlWithFilterSpecial(String hql, Map<String, Object> param, Map<String, Map<String, Object>> filterParam) {
	    int i = -1;
	    if ((i = hql.toUpperCase().indexOf("FROM")) != -1) {
	        hql = hql.substring(i);
	    }
	    i = -1;
	    if ((i = hql.toUpperCase().indexOf("ORDER BY")) != -1) {
	        hql = hql.substring(0, i);
	    }
	    i = -1;
	    hql = hql.replace("fetch", " ");
	    hql = hql.replace("FETCH", " ");
	    String counthql = "SELECT COUNT(distinct ib) " + hql;
	    try {
	        Session session = getSession();
	        setFilter(filterParam, session);
	        Query query = session.createQuery(counthql);
	        setQueryParameters(param, query);
	        Object o = null;
	        o= query.uniqueResult();
	        return Integer.valueOf(o.toString());
	    } catch (Exception e) {
	        throw new RuntimeException("解析hql语句出错: " + e.getMessage()+ "\n\n"  + counthql);
	    }
	}
	/**
	 * 总记录数
	 * @param sql
	 * @return
	 */
	protected int countQueryBySql(String sql){
	    return countQueryBySql(sql, null);
	}
	/**
	 * 总记录数
	 * @param sql
	 * @return
	 */
	protected int countQueryBySql(String sql,Map<String, Object> param){ 
	    int i = -1;
	    if ((i = sql.toLowerCase().indexOf("order by")) != -1) {
	        sql = sql.substring(0, i);
	    }
	    String countSql = "SELECT COUNT(*) FROM ( " + sql + ")";
	    try {
	        Query query = getSession().createSQLQuery(countSql);
	        setQueryParameters(param, query);
	        Object o= query.uniqueResult();
	        return Integer.valueOf(o.toString());
	    } catch (Exception e) {
            throw new RuntimeException("解析hql语句出错: " + e  + countSql);
	    }
	}
	

	
	/**
	 * sql分页实现
	 * @param sql
	 * @param pageNo  不需要分页传-1
	 * @param pageSize 不需要分页传-1	
	 * @return
	 */
	protected List getSQLList(final String sql,final int start,final int end) {
		return getSQLList(sql, null, start, end);
	}
	
	/**
	 * sql分页实现
	 * @param sql
	 * @param pageNo  不需要分页传-1
	 * @param pageSize 不需要分页传-1	
	 * @return
	 */
	protected List getSQLList(final String sql, final Map<String, Object> param, final int start, final int end) {
	    return this.getHibernateTemplate().executeFind(new HibernateCallback(){ 
	        public Object doInHibernate(Session session)throws HibernateException, SQLException {
	        	Query query = session.createSQLQuery(sql);
	            setQueryParameters(param, query);
	            if (start > -1) {                      
	                query.setFirstResult(start);					
	            }
	            if (end > -1) {
	                query.setMaxResults(end);		  	
	            }
	            return query.list();
	        }
	    });
	}
	
	/**
	 * sql分页实现
	 * @param <T>
	 * @param sql
	 * @param pageNo  不需要分页传-1
	 * @param pageSize 不需要分页传-1	
	 * @return
	 */
	protected <T> List getSQLList(final String sql, final Map<String, Object> param, final int start, final int end,final Class<T> entityClass) {
	    return this.getHibernateTemplate().executeFind(new HibernateCallback(){ 
	        public Object doInHibernate(Session session)throws HibernateException, SQLException {
	        	SQLQuery query = session.createSQLQuery(sql);
	            setQueryParameters(param, query);
	           
	            if(entityClass != null)
	            	query.addEntity(entityClass);
	            if (start > -1) {                      
	                query.setFirstResult(start);					
	            }
	            if (end > -1) {
	                query.setMaxResults(end);		  	
	            }
	            return query.list();
	        }
	    });
	}
	
	/**
	 * 根据sql查询，无参数
	 * @param sql
	 * @return
	 */
	public List getListBySql(String sql){
		return getSQLList(sql, null, -1, -1);
	}
	
	/**
	 * 当查询结果只有一条记录时适用
	 * @param sql
	 * @return
	 */
	public Object[] getUniqueBySql(String sql){
		List  list = getSQLList(sql, null, -1, -1);
		if(list==null || list.size()==0){
			return null;
		}
		return (Object[])list.get(0);
	}
	/**
	 * 当查询结果只有一条记录,且结果只有一列时适用
	 * @param sql
	 * @return
	 */
	public Object getUniqueColBySql(String sql){
		List  list = getSQLList(sql, null, -1, -1);
		if(list==null || list.size()==0){
			return null;
		}
		return (Object)list.get(0);
	}
}
